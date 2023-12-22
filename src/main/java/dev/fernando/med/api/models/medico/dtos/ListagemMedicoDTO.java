package dev.fernando.med.api.models.medico.dtos;

import dev.fernando.med.api.models.medico.Especialidade;

public record ListagemMedicoDTO (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
}
