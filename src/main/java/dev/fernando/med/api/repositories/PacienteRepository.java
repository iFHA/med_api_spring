package dev.fernando.med.api.repositories;

import dev.fernando.med.api.models.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
