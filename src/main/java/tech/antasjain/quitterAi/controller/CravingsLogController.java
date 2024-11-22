package tech.antasjain.quitterAi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.AddictionService;
import tech.antasjain.quitterAi.service.CravingsLogService;
import tech.antasjain.quitterAi.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CravingsLogController {

    private final CravingsLogService cravingsLogService;
    private final AddictionService addictionService;
    private final UserService userService;
    private Authentication getAuthData(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    @MutationMapping
    public CravingsLog logCravings(
            @Argument String timestamp,
            @Argument String notes,
            @Argument String cravingType,
            @Argument Integer intensity,
            @Argument Long addictionId
    ) {
        Authentication auth = getAuthData();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (currentUser == null) {
            throw new RuntimeException("Authenticated user not found.");
        }

        Addiction addiction = addictionService.getAddictionById(addictionId)
                .orElseThrow(() -> new IllegalArgumentException("Addiction not found"));

        // Ensure the addiction belongs to the authenticated user
        if (!addiction.getUser().equals(currentUser)) {
            throw new RuntimeException("You can only log cravings for your own addictions.");
        }

        return cravingsLogService.logCravings(timestamp, notes, cravingType, intensity, addiction);
    }

    @QueryMapping
    public Optional<CravingsLog> getCravingsLogById(@Argument Long id) {
        return cravingsLogService.getCravingsLogById(id);
    }

    @MutationMapping
    public String deleteCravingsLog(@Argument Long id) {
        Optional<CravingsLog> cravingsLog = cravingsLogService.getCravingsLogById(id);
        if (cravingsLog.isEmpty()) {
            throw new RuntimeException("Cravings log not found.");
        }

        Authentication auth = getAuthData();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (!cravingsLog.get().getAddiction().getUser().equals(currentUser)) {
            throw new RuntimeException("You can only delete your own cravings logs.");
        }

        cravingsLogService.deleteCravingsLog(id);
        return "Cravings log deleted successfully.";
    }

    @QueryMapping
    public List<CravingsLog> getUserCravings() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (currentUser == null) {
            throw new RuntimeException("Authenticated user not found.");
        }

        return cravingsLogService.getCravingsLogsForUser(currentUser);
    }
    @QueryMapping
    public List<CravingsLog> getCravingsLogByAddiction(@Argument Long addictionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        Addiction addiction = addictionService.getAddictionById(addictionId)
                .orElseThrow(() -> new IllegalArgumentException("Addiction not found"));
        if (!addiction.getUser().equals(currentUser)) {
            throw new RuntimeException("You can only view cravings logs for your own addictions.");
        }
        return cravingsLogService.getCravingsLogByAddiction(addictionId);
    }
}
