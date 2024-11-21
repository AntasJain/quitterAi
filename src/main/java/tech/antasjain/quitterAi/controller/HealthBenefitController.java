package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.service.HealthBenefitService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HealthBenefitController {

    private final HealthBenefitService healthBenefitService;

    @MutationMapping
    public HealthBenefit addHealthBenefit(@Argument String description, @Argument String achievedDate,@Argument Long milestoneId) {
        return healthBenefitService.addHealthBenefit(description, achievedDate, milestoneId);
    }


    @QueryMapping
    public Optional<HealthBenefit> getHealthBenefitById(@Argument Long id) {
        return healthBenefitService.getHealthBenefitById(id);
    }

    @MutationMapping
    public String deleteHealthBenefit(@Argument Long id) {
        healthBenefitService.deleteHealthBenefit(id);
        return "HealthBenefit deleted successfully";
    }
    @QueryMapping
    public List<HealthBenefit> getHealthBenefitsByMilestone(@Argument Long milestoneId) {
        return healthBenefitService.getHealthBenefitsByMilestone(milestoneId);
    }
}

