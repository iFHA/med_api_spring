package dev.fernando.med.api.models.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.models.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private PacienteRepository repository;
    public void validar(AgendamentoConsultaDTO dto) {
        if (Objects.isNull(dto.pacienteId())) {
            return;
        }
        boolean pacienteEstaAtivo = repository.findAtivoById(dto.pacienteId());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
