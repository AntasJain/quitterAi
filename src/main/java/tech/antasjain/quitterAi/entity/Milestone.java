package tech.antasjain.quitterAi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Milestone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String milestoneName;
    private LocalDate targetDate;
    private Boolean isAchieved;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Boolean getAchieved() {
        return isAchieved;
    }

    public void setAchieved(Boolean achieved) {
        isAchieved = achieved;
    }
}
