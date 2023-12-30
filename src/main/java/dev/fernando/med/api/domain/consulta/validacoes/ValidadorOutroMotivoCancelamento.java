package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.domain.consulta.Consulta;
import dev.fernando.med.api.domain.consulta.MotivoCancelamento;
import dev.fernando.med.api.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorOutroMotivoCancelamento implements  ValidadorCancelamentoConsulta {
    @Override
    public void validar(Consulta consulta) {
        if (MotivoCancelamento.OUTRO == consulta.getMotivoCancelamento() && (Objects.isNull(consulta.getDescricaoOutroMotivo()) || consulta.getDescricaoOutroMotivo().isEmpty())) {
            throw new ValidacaoException("Deve ser informada uma descrição do motivo de cancelamento, caso a opção selecionada seja \"Outros\".");
        }
    }
}
