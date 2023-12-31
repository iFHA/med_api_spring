package dev.fernando.med.api.repositories;

import dev.fernando.med.api.domain.consulta.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Long medicoId, LocalDateTime data);
    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime data1, LocalDateTime data2);
}
