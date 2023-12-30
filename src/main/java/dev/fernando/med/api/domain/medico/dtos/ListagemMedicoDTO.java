package dev.fernando.med.api.domain.medico.dtos;

import dev.fernando.med.api.domain.medico.Especialidade;

public record ListagemMedicoDTO (
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
}
