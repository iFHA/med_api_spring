package dev.fernando.med.api.domain.paciente.dtos;

import dev.fernando.med.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPacienteDTO(String nome, String telefone, DadosEndereco endereco) {
}
