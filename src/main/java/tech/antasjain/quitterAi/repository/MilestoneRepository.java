package tech.antasjain.quitterAi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.antasjain.quitterAi.entity.Milestone;


@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}
