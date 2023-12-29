package dev.fernando.med.api.models.consulta.validacoes;

import dev.fernando.med.api.models.consulta.dtos.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
    void validar(AgendamentoConsultaDTO dto);
}
