package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.AddictionService;
import tech.antasjain.quitterAi.service.MilestoneService;
import tech.antasjain.quitterAi.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;
    private final UserService userService;

    private final AddictionService addictionService;
    @MutationMapping
    public Milestone addMilestone(@Argument String milestoneName, @Argument String targetDate, @Argument Boolean isAchieved, @Argument Long addictionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (currentUser == null) {
            throw new RuntimeException("Authenticated user not found.");
        }
        Addiction addiction = addictionService.getAddictionById(addictionId)
                .orElseThrow(() -> new IllegalArgumentException("Addiction not found"));

        if (!addiction.getUser().equals(currentUser)) {
            throw new RuntimeException("You can only log cravings for your own addictions.");
        }
        return milestoneService.addMilestone(milestoneName, targetDate, isAchieved, addiction);
    }

    @QueryMapping
    public List<Milestone> getMilestonesByAddictionId(@Argument Long addictionId) {
        return milestoneService.getMilestonesByAddictionId(addictionId);
    }


    @QueryMapping
    public Optional<Milestone> getMilestoneById(@Argument Long id) {
        return milestoneService.getMilestoneById(id);
    }

    @MutationMapping
    public Milestone updateMilestone(@Argument Long id, @Argument Boolean isAchieved, @Argument String targetDate) {
        return milestoneService.updateMilestone(id, isAchieved, targetDate);
    }

    @MutationMapping
    public String deleteMilestone(@Argument Long id) {
        milestoneService.deleteMilestone(id);
        return "Milestone deleted successfully";
    }
}
