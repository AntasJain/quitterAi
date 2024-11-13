package tech.antasjain.quitterAi.api;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.service.CravingsLogService;
import tech.antasjain.quitterAi.service.UserService;

import java.util.List;
@Component
public class CravingsResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private CravingsLogService cravingsLogService;


    private UserService userService;
    @Autowired
    public CravingsResolver(CravingsLogService cravingsLogService, UserService userService) {
        this.cravingsLogService = cravingsLogService;
        this.userService = userService;
    }
    public CravingsLog logCraving(String cravingType, int intensity, String notes) {
        User user = userService.getCurrentUser();
        return cravingsLogService.logCraving(user, cravingType, intensity, notes);
    }

    public List<CravingsLog> getUserCravings() {
        User user = userService.getCurrentUser();
        return cravingsLogService.getUserCravings(user);
    }
}