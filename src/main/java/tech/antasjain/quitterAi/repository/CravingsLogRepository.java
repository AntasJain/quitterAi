package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.CravingsLog;

@Repository
public interface CravingsLogRepository extends JpaRepository<CravingsLog, Long> {
}
