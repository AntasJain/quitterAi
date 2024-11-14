package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.repository.MilestoneRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;

    public Milestone addMilestone(String milestoneName, String targetDate, Boolean isAchieved) {
        Milestone milestone = new Milestone();
        milestone.setMilestoneName(milestoneName);
        milestone.setTargetDate(targetDate);
        milestone.setIsAchieved(isAchieved);
        return milestoneRepository.save(milestone);
    }

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    public Optional<Milestone> getMilestoneById(Long id) {
        return milestoneRepository.findById(id);
    }

    public Milestone updateMilestone(Long id, Boolean isAchieved, String targetDate) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Milestone not found"));
        milestone.setIsAchieved(isAchieved);
        milestone.setTargetDate(targetDate);
        return milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Long id) {
        milestoneRepository.deleteById(id);
    }
}
