package tech.antasjain.quitterAi.api;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.MilestoneService;
import tech.antasjain.quitterAi.service.UserService;

import java.time.LocalDate;
import java.util.List;
@Component
public class MilestoneResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    private MilestoneService milestoneService;
    private UserService userService;

    public MilestoneResolver(MilestoneService milestoneService, UserService userService) {
        this.milestoneService = milestoneService;
        this.userService = userService;
    }

    public Milestone setMilestone(String milestoneName, Boolean isAchieved, LocalDate targetDate) {
        User user = userService.getCurrentUser();
        return milestoneService.setMilestone(user,milestoneName,isAchieved,targetDate);
    }

    public List<Milestone> getUserMilestones(){
        User user = userService.getCurrentUser();
        return milestoneService.getUserMilestones(user);
    }

    }
