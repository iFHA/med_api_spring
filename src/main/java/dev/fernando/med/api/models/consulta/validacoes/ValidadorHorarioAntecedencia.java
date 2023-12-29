package dev.fernando.med.api.models.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.models.consulta.dtos.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {
    public void validar(AgendamentoConsultaDTO dados) {
        LocalDateTime data = dados.data();
        LocalDateTime agora = LocalDateTime.now();
        Long diferencaEmMinutos = Duration.between(agora, data).toMinutes();
        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
