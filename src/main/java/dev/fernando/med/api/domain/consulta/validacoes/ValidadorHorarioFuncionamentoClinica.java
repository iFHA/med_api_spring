package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {
    public void validar(AgendamentoConsultaDTO dados) {
        LocalDateTime data = dados.data();
        Boolean domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean dataAntesAbertura = data.getHour() < 7;
        Boolean dataDepoisFechamento = data.getHour() > 18;
        if (domingo || dataAntesAbertura || dataDepoisFechamento) {
            throw new ValidacaoException("Data inválida, o horário de funcionamento da clínica é de segunda a sábado, das 7h às 19h");
        }
    }
}
