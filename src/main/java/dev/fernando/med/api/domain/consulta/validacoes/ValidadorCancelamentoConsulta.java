package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.domain.consulta.Consulta;
import dev.fernando.med.api.domain.consulta.dtos.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoConsulta {
    void validar(Consulta consulta);
}
