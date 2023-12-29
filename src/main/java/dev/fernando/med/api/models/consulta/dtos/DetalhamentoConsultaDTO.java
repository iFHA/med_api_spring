package dev.fernando.med.api.models.consulta.dtos;

import java.time.LocalDateTime;

public record DetalhamentoConsultaDTO(Long id, Long medicoId, Long pacienteId, LocalDateTime data) {
}
