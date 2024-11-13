package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.entity.User;

import java.util.List;

@Repository
public interface HealthBenefitRepository extends JpaRepository<HealthBenefit, Long> {

    List<HealthBenefit> findByUser(User user);


}
