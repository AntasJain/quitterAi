package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.repository.HealthBenefitRepository;
import tech.antasjain.quitterAi.repository.MilestoneRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthBenefitService {

    private final HealthBenefitRepository healthBenefitRepository;
    private final MilestoneRepository milestoneRepository;

    public HealthBenefit addHealthBenefit(String description, String achievedDateString, Long milestoneId) {
        Milestone milestone = milestoneRepository.findById(milestoneId)
                .orElseThrow(() -> new IllegalArgumentException("Milestone not found"));

        HealthBenefit healthBenefit = new HealthBenefit();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate achievedDate = LocalDate.parse(achievedDateString, formatter);

        healthBenefit.setDescription(description);
        healthBenefit.setAchievedDate(achievedDate);
        healthBenefit.setMilestone(milestone);
        return healthBenefitRepository.save(healthBenefit);
    }

    public List<HealthBenefit> getHealthBenefitsByMilestone(Long milestoneId) {
        return healthBenefitRepository.findByMilestoneId(milestoneId);
    }

    public Optional<HealthBenefit> getHealthBenefitById(Long id){
        return  healthBenefitRepository.findById(id);
    }

    public void deleteHealthBenefit(Long id) {
        healthBenefitRepository.deleteById(id);
    }
}
