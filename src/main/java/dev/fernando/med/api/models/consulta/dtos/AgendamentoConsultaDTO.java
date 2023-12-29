package dev.fernando.med.api.models.consulta.dtos;

import dev.fernando.med.api.models.medico.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoConsultaDTO(
        Long medicoId,
        Especialidade especialidade,
        @NotNull
        Long pacienteId,
        @NotNull
        @Future
        LocalDateTime data
) {
}
