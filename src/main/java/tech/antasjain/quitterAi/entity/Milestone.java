package tech.antasjain.quitterAi.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String milestoneName;
    private LocalDate targetDate;   // Use `LocalDate` if your setup allows
    private Boolean isAchieved;
    @ManyToOne
    @JoinColumn(name = "addiction_id", nullable = false)
    private Addiction addiction;

    @OneToMany(mappedBy = "milestone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthBenefit> healthBenefits;

}
