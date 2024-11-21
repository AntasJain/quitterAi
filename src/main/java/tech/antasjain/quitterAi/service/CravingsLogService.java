package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.CravingsLogRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CravingsLogService {

    private final CravingsLogRepository cravingsLogRepository;

    public CravingsLog logCravings(String timestampString, String notes, String cravingType, Integer intensity, Addiction addiction) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime timestamp = LocalDateTime.parse(timestampString, formatter);

        CravingsLog cravingsLog = new CravingsLog();
        cravingsLog.setTimestamp(timestamp);
        cravingsLog.setNotes(notes);
        cravingsLog.setCravingType(cravingType);
        cravingsLog.setIntensity(intensity);
        cravingsLog.setAddiction(addiction);

        return cravingsLogRepository.save(cravingsLog);
    }

    public List<CravingsLog> getCravingsLogsForUser(User user) {
        return cravingsLogRepository.findByAddiction_User(user);
    }

    public List<CravingsLog> getCravingsLogsForAddiction(Long addictionId) {
        return cravingsLogRepository.findByAddiction_Id(addictionId);
    }

    public Optional<CravingsLog> getCravingsLogById(Long id) {
        return cravingsLogRepository.findById(id);
    }

    public void deleteCravingsLog(Long id) {
        cravingsLogRepository.deleteById(id);
    }
}
