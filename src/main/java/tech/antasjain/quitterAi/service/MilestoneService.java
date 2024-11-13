package tech.antasjain.quitterAi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.antasjain.quitterAi.entity.Milestone;
import tech.antasjain.quitterAi.entity.User;
import tech.antasjain.quitterAi.repository.MilestoneRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class MilestoneService {


    private MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneService(MilestoneRepository milestoneRepository){
        this.milestoneRepository=milestoneRepository;
    }

    public Milestone setMilestone(User user, String milestone, Boolean isAchieved, LocalDate targetDate){
        Milestone log = new Milestone();
        log.setUser(user);
        log.setMilestoneName(milestone);
        log.setAchieved(isAchieved);
        log.setTargetDate(targetDate);
        return  milestoneRepository.save(log);
    }

    public List<Milestone> getUserMilestones(User user){
        return milestoneRepository.findByUser(user);
    }
}
