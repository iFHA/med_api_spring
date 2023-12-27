package dev.fernando.med.api.models.paciente.dtos;

import dev.fernando.med.api.models.endereco.DadosEndereco;
import dev.fernando.med.api.models.endereco.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPacienteDTO (
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank String cpf,
        @NotNull @Valid DadosEndereco endereco
) {
}
