package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CravingsLogRepository extends JpaRepository<CravingsLog, Long> {
    List<CravingsLog> findByUser(User user);
    List<CravingsLog> findByUserAndTimestampBetween(User user, LocalDateTime start, LocalDateTime end);
}
