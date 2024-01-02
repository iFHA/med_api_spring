package dev.fernando.med.api.controllers;

import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.DetalhamentoConsultaDTO;
import dev.fernando.med.api.domain.medico.Especialidade;
import dev.fernando.med.api.services.AgendaDeConsultasService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
@ActiveProfiles("test")
class ConsultaControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<AgendamentoConsultaDTO> agendamentoConsultaDTOJacksonTester;
    @Autowired
    private JacksonTester<DetalhamentoConsultaDTO> detalhamentoConsultaDTOJacksonTester;
    @MockBean
    private AgendaDeConsultasService agendaDeConsultasService;
    @Test
    @DisplayName("Deveria devolver código 400 quando informações estiverem inválidas")
    void agendar_cenario_1() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver código 200 quando informações estiverem válidas")
    void agendar_cenario_2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;
        var medicoId = 5L;
        var pacienteId = 2L;
        var detalheAgendamento = new DetalhamentoConsultaDTO(1L, medicoId, pacienteId, data);

        when(agendaDeConsultasService.agendar(any())).thenReturn(detalheAgendamento);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        agendamentoConsultaDTOJacksonTester
                                                .write(new AgendamentoConsultaDTO(medicoId, especialidade, pacienteId, data))
                                                .getJson()
                                )

                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = detalhamentoConsultaDTOJacksonTester.write(detalheAgendamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}