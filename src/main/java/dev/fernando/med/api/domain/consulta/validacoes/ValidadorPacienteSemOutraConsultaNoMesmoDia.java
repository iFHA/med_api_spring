package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoMesmoDia implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository repository;
    public void validar(AgendamentoConsultaDTO dto) {
        boolean existeConsultaAgendadaNoMesmoDia = repository
                .existsByPacienteIdAndDataBetween(
                        dto.pacienteId(),
                        dto.data().withHour(7),
                        dto.data().withHour(19));
        if(existeConsultaAgendadaNoMesmoDia) {
            throw new ValidacaoException("Já existe uma consulta marcada para esse paciente nesse mesmo dia");
        }
    }
}
