package dev.fernando.med.api.domain.medico.dtos;

import dev.fernando.med.api.domain.endereco.DadosEndereco;

public record DadosAtualizacaoMedicoDTO(
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
