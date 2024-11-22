package tech.antasjain.quitterAi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.Addiction;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.repository.MilestoneRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final MilestoneRepository milestoneRepository;


    public Milestone addMilestone(String milestoneName, String targetDate, Boolean isAchieved, Addiction addiction) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;

        Milestone milestone = new Milestone();
        milestone.setMilestoneName(milestoneName);
        milestone.setTargetDate(LocalDate.parse(targetDate, formatter));
        milestone.setIsAchieved(isAchieved);
        milestone.setAddiction(addiction);
        return milestoneRepository.save(milestone);
    }

    public List<Milestone> getMilestonesByAddictionId(Long addictionId) {
        return milestoneRepository.findByAddictionId(addictionId);
    }



    public Optional<Milestone> getMilestoneById(Long id) {
        return milestoneRepository.findById(id);
    }

    public Milestone updateMilestone(Long id, Boolean isAchieved, String targetDate) {
        Milestone milestone = milestoneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Milestone not found"));
        milestone.setIsAchieved(isAchieved);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        milestone.setTargetDate(LocalDate.parse(targetDate, formatter));
        return milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Long id) {
        milestoneRepository.deleteById(id);
    }
}
