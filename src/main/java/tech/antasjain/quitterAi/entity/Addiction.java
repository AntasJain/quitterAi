package tech.antasjain.quitterAi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

   @OneToMany(mappedBy = "addiction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CravingsLog> cravingLogs;

   @OneToMany(mappedBy = "addiction", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Milestone> milestones;


}
