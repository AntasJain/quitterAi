package tech.antasjain.quitterAi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.AddictionService;
import tech.antasjain.quitterAi.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AddictionController {

    private final AddictionService addictionService;
    private final UserService userService;

    @MutationMapping
    public Addiction addAddiction(@Argument String name, @Argument String startDate) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (currentUser == null) {
            throw new RuntimeException("User not found.");
        }

        return addictionService.addAddiction(name, startDate, currentUser);
    }

    @QueryMapping
    public List<Addiction> getAddictions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new RuntimeException("User is not authenticated.");
        }

        User currentUser = userService.findByEmail(auth.getName());
        if (currentUser == null) {
            throw new RuntimeException("User not found.");
        }

        return addictionService.getAddictionsByUser(currentUser);
    }

    @MutationMapping
    public String deleteAddictionById(@Argument Long addictionId){
        Optional<Addiction> addiction = addictionService.getAddictionById(addictionId);
        if(addiction.isEmpty()){
            throw new RuntimeException("Addiction not found");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null||auth.getName()==null){
            throw new RuntimeException("Unauthenticated User");
        }
        User currentUser = userService.findByEmail(auth.getName());
        if(!addiction.get().getUser().equals(currentUser)){
            throw new RuntimeException("You can only delete your own Addictions.");
        }

        try{
            addictionService.deleteAddiction(addictionId);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "Addiction Deleted Successfully";

    }


}
