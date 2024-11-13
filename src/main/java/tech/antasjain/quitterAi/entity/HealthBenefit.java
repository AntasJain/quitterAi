package tech.antasjain.quitterAi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HealthBenefit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime achievedDate;

    private String description;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAchievedDate() {
        return achievedDate;
    }

    public void setAchievedDate(LocalDateTime achievedDate) {
        this.achievedDate = achievedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
