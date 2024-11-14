package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.service.MilestoneService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MilestoneController {

    private final MilestoneService milestoneService;

    @MutationMapping
    public Milestone addMilestone(@Argument String milestoneName, @Argument String targetDate, @Argument Boolean isAchieved) {
        return milestoneService.addMilestone(milestoneName, targetDate, isAchieved);
    }

    @QueryMapping
    public List<Milestone> getAllMilestones() {
        return milestoneService.getAllMilestones();
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
