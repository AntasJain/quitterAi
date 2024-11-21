package tech.antasjain.quitterAi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addiction {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

   private String name;
   private LocalDate startDate;
   @ManyToOne
   private User user;

   @OneToMany(mappedBy = "addiction", cascade = CascadeType.ALL)
    private List<CravingsLog> cravingLogs;

   @OneToMany(mappedBy = "addiction", cascade = CascadeType.ALL)
    private List<Milestone> milestones;


}
