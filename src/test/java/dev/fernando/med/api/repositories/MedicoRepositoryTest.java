package dev.fernando.med.api.repositories;

import dev.fernando.med.api.domain.consulta.Consulta;
import dev.fernando.med.api.domain.endereco.Endereco;
import dev.fernando.med.api.domain.medico.Especialidade;
import dev.fernando.med.api.domain.medico.Medico;
import dev.fernando.med.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;
    @Test
    @DisplayName("Deveria retornar Optional vazio quando único médico cadastrado não está disponível na data")
    void escolherMedicoAleatorioEspecialistaEmELivreNaDataCenario1() {
        // given ou arrange
        var proximaSegundaAs10 = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var medico = cadastrarMedico("medico 1", "meddico@fernando.dev", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("paciente 1", "paciente1@fernando.dev", "12345678901");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        // when ou act
        var medicoLivre = medicoRepository
                .escolherMedicoAleatorioEspecialistaEmELivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // then ou assert
        assertThat(medicoLivre).isEmpty();
    }
    @Test
    @DisplayName("Deveria retornar medico quando ele estiver disponível na data")
    void escolherMedicoAleatorioEspecialistaEmELivreNaDataCenario2() {
        // given ou arrange
        var proximaSegundaAs10 = LocalDate
                .now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var medico = cadastrarMedico("medico 1", "meddico@fernando.dev", "123456", Especialidade.CARDIOLOGIA);

        // when ou act
        var medicoLivre = medicoRepository
                .escolherMedicoAleatorioEspecialistaEmELivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        // then ou assert
        assertThat(medicoLivre.orElseGet(()->null)).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        this.em.persist(new Consulta(null, medico, paciente, data, null, null, null));
    }
    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = dadosCadastroMedico(nome, email, crm, especialidade);
        this.em.persist(medico);
        return medico;
    }
    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = dadosCadastroPaciente(nome, email, cpf);
        this.em.persist(paciente);
        return paciente;
    }
    private Medico dadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new Medico(
                null,
                nome,
                email,
                "88999183389",
                crm,
                true,
                especialidade,
                dadosEndereco());
    }
    private Paciente dadosCadastroPaciente(String nome, String email, String cpf) {
        return new Paciente(
                null,
                nome,
                email,
                "88999183389",
                cpf,
                dadosEndereco(),
                true
        );
    }
    private Endereco dadosEndereco() {
        return new Endereco(
                "manoel franklin",
                "8 de junho",
                "62960000",
                "tabuleiro do norte",
                "ce",
                "",
                "3848"
        );
    }
}