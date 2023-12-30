package dev.fernando.med.api.domain.consulta;

import dev.fernando.med.api.domain.medico.Medico;
import dev.fernando.med.api.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;
    @Column(name = "descricao_outro_motivo_cancelamento")
    private String descricaoOutroMotivo;
    private LocalDateTime dataCancelamento;
}
