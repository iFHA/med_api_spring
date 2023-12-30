package dev.fernando.med.api.domain.consulta.validacoes;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private MedicoRepository repository;
    public void validar(AgendamentoConsultaDTO dto) {
        if (Objects.isNull(dto.medicoId())) {
            return;
        }
        Boolean medicoEstaAtivo = repository.findAtivoById(dto.medicoId());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
        }
    }
}
