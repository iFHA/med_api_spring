package dev.fernando.med.api.models.paciente.dtos;

import dev.fernando.med.api.models.endereco.DadosEndereco;

public record DadosAtualizacaoPacienteDTO(String nome, String telefone, DadosEndereco endereco) {
}
