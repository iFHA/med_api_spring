package dev.fernando.med.api.models.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.models.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(AgendamentoConsultaDTO dto) {
        boolean medicoPossuiOutraConsultaNoMesmoHoraio = consultaRepository.existsByMedicoIdAndData(dto.medicoId(), dto.data());
        if(medicoPossuiOutraConsultaNoMesmoHoraio) {
            throw new ValidacaoException("Médico possui outa consulta agendada no mesmo horário");
        }
    }
}
