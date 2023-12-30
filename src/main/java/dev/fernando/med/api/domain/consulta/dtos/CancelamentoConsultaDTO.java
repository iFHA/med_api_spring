package dev.fernando.med.api.domain.consulta.dtos;

import dev.fernando.med.api.domain.consulta.MotivoCancelamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(
        @NotNull Long consultaId,
        @NotNull MotivoCancelamento motivo,
        String descricaoOutroMotivo
) {
}
