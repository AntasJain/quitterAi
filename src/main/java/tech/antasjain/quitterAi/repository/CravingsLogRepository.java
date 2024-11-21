package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.CravingsLog;
import tech.antasjain.quitterAi.entity.User;

import java.util.List;

@Repository
public interface CravingsLogRepository extends JpaRepository<CravingsLog, Long> {
    List<CravingsLog> findByAddiction_User(User user);
    List<CravingsLog> findByAddiction_Id(Long addictionId);


}
