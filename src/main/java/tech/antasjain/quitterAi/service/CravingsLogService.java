package tech.antasjain.quitterAi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.repository.CravingsLogRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CravingsLogService {

    private final CravingsLogRepository cravingsLogRepository;

    public CravingsLog addCravingsLog(String timestampString, String notes, String cravingType, Integer intensity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.parse(timestampString, formatter);
        CravingsLog cravingsLog = new CravingsLog();
        cravingsLog.setTimestamp(timestamp);
        cravingsLog.setNotes(notes);
        cravingsLog.setCravingType(cravingType);
        cravingsLog.setIntensity(intensity);
        return cravingsLogRepository.save(cravingsLog);
    }

    public List<CravingsLog> getAllCravingsLogs() {
        return cravingsLogRepository.findAll();
    }

    public Optional<CravingsLog> getCravingsLogById(Long id) {
        return cravingsLogRepository.findById(id);
    }


    public void deleteCravingsLog(Long id) {
        cravingsLogRepository.deleteById(id);
    }
}
