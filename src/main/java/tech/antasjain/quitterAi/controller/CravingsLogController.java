package tech.antasjain.quitterAi.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.service.CravingsLogService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CravingsLogController {

    private final CravingsLogService cravingsLogService;

    @MutationMapping
    public CravingsLog addCravingsLog(@Argument String timestamp, @Argument String notes, @Argument String cravingType, @Argument Integer intensity) {
        return cravingsLogService.addCravingsLog(timestamp, notes, cravingType, intensity);
    }

    @QueryMapping
    public List<CravingsLog> getAllCravingsLogs() {
        return cravingsLogService.getAllCravingsLogs();
    }

    @QueryMapping
    public Optional<CravingsLog> getCravingsLogById(@Argument Long id) {
        return cravingsLogService.getCravingsLogById(id);
    }

    @MutationMapping
    public String deleteCravingsLog(@Argument Long id) {
        cravingsLogService.deleteCravingsLog(id);
        return "CravingsLog deleted successfully";
    }
}
