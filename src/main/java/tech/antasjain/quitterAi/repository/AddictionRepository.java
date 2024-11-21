package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.User;

import java.util.List;

@Repository
public interface AddictionRepository extends JpaRepository<Addiction, Long> {
    List<Addiction> findByUser(User user);
}
