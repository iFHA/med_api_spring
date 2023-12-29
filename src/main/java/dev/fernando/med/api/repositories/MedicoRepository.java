package dev.fernando.med.api.repositories;

import dev.fernando.med.api.models.medico.Especialidade;
import dev.fernando.med.api.models.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            select m from Medico m
            where
            especialidade = :especialidade
            and m.id not in (
                select c.medicoId from Consulta c where c.data = :data
            )
            order by rand()
            limit 1
            """)
    Optional<Medico> escolherMedicoAleatorioEspecialistaEmELivreNaData(Especialidade especialidade, LocalDateTime data);
    @Query("""
            select m.ativo from Medico m
            where m.id = :medicoId 
            """)
    Boolean findAtivoById(Long medicoId);
}
