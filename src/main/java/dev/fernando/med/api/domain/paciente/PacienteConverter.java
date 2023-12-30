package dev.fernando.med.api.domain.paciente;

import dev.fernando.med.api.domain.endereco.EnderecoConverter;
import dev.fernando.med.api.domain.paciente.dtos.DadosCadastroPacienteDTO;
import dev.fernando.med.api.domain.paciente.dtos.PacienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteConverter {
    @Autowired
    private EnderecoConverter enderecoConverter;
    public Paciente fromDadosCadastro(DadosCadastroPacienteDTO dto) {
        return new Paciente(
                null,
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.cpf(),
                enderecoConverter.fromDTO(dto.endereco()),
                true);
    }
    public PacienteDTO toPacienteDTO(Paciente paciente) {
        return new PacienteDTO(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
