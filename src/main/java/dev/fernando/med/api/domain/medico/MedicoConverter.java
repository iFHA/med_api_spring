package dev.fernando.med.api.domain.medico;

import dev.fernando.med.api.domain.endereco.EnderecoConverter;
import dev.fernando.med.api.domain.medico.dtos.DadosCadastroMedicoDTO;
import dev.fernando.med.api.domain.medico.dtos.DadosDetalhamentoMedicoDTO;
import dev.fernando.med.api.domain.medico.dtos.ListagemMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConverter {
    @Autowired
    private EnderecoConverter enderecoConverter;
    public Medico fromDTO(DadosCadastroMedicoDTO dto) {
        return new Medico(
                null,
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.crm(),
                true,
                dto.especialidade(),
                this.enderecoConverter.fromDTO(dto.endereco()));
    }
    public ListagemMedicoDTO toListagemMedicoDTO(Medico medico) {
        return new ListagemMedicoDTO(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }

    public DadosDetalhamentoMedicoDTO toDadosDetalhamentoMedicoDTO(Medico medico) {
        return new DadosDetalhamentoMedicoDTO(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
