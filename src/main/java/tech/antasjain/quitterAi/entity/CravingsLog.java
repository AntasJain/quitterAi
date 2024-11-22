package tech.antasjain.quitterAi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CravingsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    private String notes;
    private String cravingType;
    private Integer intensity;
    @ManyToOne
    @JoinColumn(name = "addiction_id", nullable = false)
    private Addiction addiction;
}
