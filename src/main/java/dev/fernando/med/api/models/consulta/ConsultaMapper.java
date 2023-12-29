package dev.fernando.med.api.models.consulta;

import dev.fernando.med.api.models.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import dev.fernando.med.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ConsultaMapper {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public Consulta fromAgendamentoConsultaDTO(AgendamentoConsultaDTO dto) {
        var medico = medicoRepository.getReferenceById(dto.medicoId());
        var paciente = pacienteRepository.getReferenceById(dto.pacienteId());
        return new Consulta(null, medico, paciente, dto.data());
    }
}
