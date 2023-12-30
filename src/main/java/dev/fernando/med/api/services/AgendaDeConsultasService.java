package dev.fernando.med.api.services;

import dev.fernando.med.api.exceptions.ValidacaoException;
import dev.fernando.med.api.domain.consulta.Consulta;
import dev.fernando.med.api.domain.consulta.ConsultaMapper;
import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.DetalhamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import dev.fernando.med.api.domain.medico.Medico;
import dev.fernando.med.api.domain.paciente.Paciente;
import dev.fernando.med.api.repositories.ConsultaRepository;
import dev.fernando.med.api.repositories.MedicoRepository;
import dev.fernando.med.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AgendaDeConsultasService {
    @Autowired
    private ConsultaRepository repository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaMapper mapper;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;
    public DetalhamentoConsultaDTO agendar(AgendamentoConsultaDTO dto) {
        if(!pacienteRepository.existsById(dto.pacienteId())) {
            throw new ValidacaoException("Paciente de id(%d) não encontrado".formatted(dto.pacienteId()));
        }
        if(Objects.nonNull(dto.medicoId()) && !medicoRepository.existsById(dto.medicoId())) {
            throw new ValidacaoException("Médico de id(%d) não encontrado".formatted(dto.medicoId()));
        }
        Medico medico = escolherMedico(dto);

        validadores.forEach(validador-> validador.validar(dto));

        Paciente paciente = pacienteRepository.getReferenceById(dto.pacienteId());
        Consulta consulta = new Consulta(null, medico, paciente, dto.data());
        repository.save(consulta);
        return mapper.todetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dto) {
        if(Objects.nonNull(dto.medicoId())) {
            return medicoRepository.getReferenceById(dto.medicoId());
        }
        if(Objects.isNull(dto.especialidade())) {
            throw new ValidacaoException("Campo \"especialidade\" é de preenchimento obrigatório quando nenhum médico é informado!");
        }
        return  medicoRepository
                .escolherMedicoAleatorioEspecialistaEmELivreNaData(dto.especialidade(), dto.data())
                .orElseThrow(()->new ValidacaoException("Não foi encontrado nenhum médico especialista em %s disponível na data escolhida(%s)".formatted(dto.especialidade(), dto.data())));
    }
}
