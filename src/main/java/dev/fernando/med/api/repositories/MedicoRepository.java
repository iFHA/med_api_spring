package dev.fernando.med.api.repositories;

import dev.fernando.med.api.models.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
