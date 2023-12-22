package dev.fernando.med.api.models.medico.dtos;

import dev.fernando.med.api.models.endereco.DadosEndereco;
import dev.fernando.med.api.models.medico.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoMedicoDTO(
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
