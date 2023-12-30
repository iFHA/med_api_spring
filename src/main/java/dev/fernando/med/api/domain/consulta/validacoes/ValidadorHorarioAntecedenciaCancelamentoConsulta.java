package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.domain.consulta.Consulta;
import dev.fernando.med.api.domain.consulta.dtos.CancelamentoConsultaDTO;
import dev.fernando.med.api.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedenciaCancelamentoConsulta implements ValidadorCancelamentoConsulta {
    @Override
    public void validar(Consulta consulta) {
        var agora = LocalDateTime.now();
        var dataConsulta = consulta.getData();
        if(Duration.between(agora, dataConsulta).toHours() < 24) {
            throw new ValidacaoException("Só é permitido cancelar uma consulta com antecedência mínima de 24h");
        }
    }
}
