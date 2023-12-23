package dev.fernando.med.api.models.medico.dtos;

import dev.fernando.med.api.models.endereco.DadosEndereco;
import dev.fernando.med.api.models.endereco.Endereco;
import dev.fernando.med.api.models.medico.Especialidade;

public record DadosDetalhamentoMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
