package dev.fernando.med.api.domain.medico.dtos;

import dev.fernando.med.api.domain.endereco.Endereco;
import dev.fernando.med.api.domain.medico.Especialidade;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
