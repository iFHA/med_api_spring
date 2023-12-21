package dev.fernando.med.api.models.medico.dtos;

import dev.fernando.med.api.models.endereco.DadosEndereco;
import dev.fernando.med.api.models.medico.Especialidade;
import dev.fernando.med.api.models.medico.Medico;

public record DadosMedicoDTO(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco) {
}
