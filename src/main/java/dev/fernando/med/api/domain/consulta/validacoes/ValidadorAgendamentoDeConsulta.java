package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
    void validar(AgendamentoConsultaDTO dto);
}
