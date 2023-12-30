package dev.fernando.med.api.domain.consulta;

import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.CancelamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.DetalhamentoConsultaDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import dev.fernando.med.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsultaMapper {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta fromAgendamentoConsultaDTO(AgendamentoConsultaDTO dto) {
        var medico = medicoRepository.getReferenceById(dto.medicoId());
        var paciente = pacienteRepository.getReferenceById(dto.pacienteId());
        return new Consulta(null, medico, paciente, dto.data(), null, null, null);
    }
    public DetalhamentoConsultaDTO todetalhamentoConsultaDTO(Consulta consulta) {
        return new DetalhamentoConsultaDTO(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
    public void atualizaDadosCancelamentoConsulta(Consulta consulta, CancelamentoConsultaDTO dto) {
        consulta.setDataCancelamento(LocalDateTime.now());
        consulta.setMotivoCancelamento(dto.motivo());
        consulta.setDescricaoOutroMotivo(dto.descricaoOutroMotivo());
    }
}
