package dev.fernando.med.api.models.medico;

import dev.fernando.med.api.models.endereco.EnderecoConverter;
import dev.fernando.med.api.models.medico.dtos.DadosMedicoDTO;
import dev.fernando.med.api.models.medico.dtos.ListagemMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConverter {
    @Autowired
    private EnderecoConverter enderecoConverter;
    public Medico fromDTO(DadosMedicoDTO dto) {
        return new Medico(
                null,
                dto.nome(),
                dto.email(),
                dto.telefone(),
                dto.crm(),
                dto.especialidade(),
                this.enderecoConverter.fromDTO(dto.endereco()));
    }
    public ListagemMedicoDTO toListagemMedicoDTO(Medico medico) {
        return new ListagemMedicoDTO(
                medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade()
        );
    }
}
