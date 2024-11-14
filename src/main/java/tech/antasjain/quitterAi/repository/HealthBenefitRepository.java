package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.HealthBenefit;

@Repository
public interface HealthBenefitRepository extends JpaRepository<HealthBenefit, Long> {

}
