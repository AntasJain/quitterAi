package tech.antasjain.quitterAi.service;

import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.HealthBenefit;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.HealthBenefitRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HealthBenefitService {

    private HealthBenefitRepository healthBenefitRepository;

    public HealthBenefitService(HealthBenefitRepository healthBenefitRepository) {
        this.healthBenefitRepository = healthBenefitRepository;
    }

    public HealthBenefit setHealthBenefit(User user, String description, LocalDateTime achievedDate){
        HealthBenefit healthBenefit = new HealthBenefit();
        healthBenefit.setUser(user);
        healthBenefit.setDescription(description);
        healthBenefit.setAchievedDate(achievedDate);
       return healthBenefitRepository.save(healthBenefit);
    }
    public List<HealthBenefit> getUserHealthBenefits(User user){
        return healthBenefitRepository.findByUser(user);
    }
}
