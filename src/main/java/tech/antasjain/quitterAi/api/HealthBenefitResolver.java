package tech.antasjain.quitterAi.api;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.HealthBenefitService;
import tech.antasjain.quitterAi.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class HealthBenefitResolver implements GraphQLMutationResolver, GraphQLQueryResolver {

    private HealthBenefitService healthBenefitService;
    private UserService userService;

    public HealthBenefitResolver(HealthBenefitService healthBenefitService, UserService userService) {
        this.healthBenefitService = healthBenefitService;
        this.userService = userService;
    }
    public HealthBenefit setHealthBenefit(String description, LocalDateTime achievedDate) {
        User user = userService.getCurrentUser();
        return healthBenefitService.setHealthBenefit(user, description,
                achievedDate);
    }

    public List<HealthBenefit> getUserHealthBenefit() {
        User user = userService.getCurrentUser();
        return healthBenefitService.getUserHealthBenefits(user);
    }
}