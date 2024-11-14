package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.repository.HealthBenefitRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HealthBenefitService {

    private final HealthBenefitRepository healthBenefitRepository;

    public HealthBenefit addHealthBenefit(String description, String achievedDate) {
        HealthBenefit healthBenefit = new HealthBenefit();
        healthBenefit.setDescription(description);
        healthBenefit.setAchievedDate(achievedDate);
        return healthBenefitRepository.save(healthBenefit);
    }

    public List<HealthBenefit> getAllHealthBenefits() {
        return healthBenefitRepository.findAll();
    }

    public Optional<HealthBenefit> getHealthBenefitById(Long id) {
        return healthBenefitRepository.findById(id);
    }

    public void deleteHealthBenefit(Long id) {
        healthBenefitRepository.deleteById(id);
    }
}
