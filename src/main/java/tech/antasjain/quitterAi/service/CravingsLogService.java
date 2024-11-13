package tech.antasjain.quitterAi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.CravingsLogRepository;

import java.util.List;

@Service
public class CravingsLogService {


    private CravingsLogRepository cravingsLogRepository;
    @Autowired
    public CravingsLogService(CravingsLogRepository cravingsLogRepository) {
        this.cravingsLogRepository = cravingsLogRepository;
    }

    public CravingsLog logCraving(User user, String cravingType, int intensity, String notes) {
        CravingsLog log = new CravingsLog();
        log.setUser(user);
        log.setCravingType(cravingType);
        log.setIntensity(intensity);
        log.setNotes(notes);
        return cravingsLogRepository.save(log);
    }

    public List<CravingsLog> getUserCravings(User user){
        return cravingsLogRepository.findByUser(user);
    }
}
