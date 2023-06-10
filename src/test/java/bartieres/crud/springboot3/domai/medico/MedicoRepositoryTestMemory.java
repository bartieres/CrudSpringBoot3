package bartieres.crud.springboot3.domai.medico;

import bartieres.crud.springboot3.domain.consulta.Consulta;
import bartieres.crud.springboot3.domain.endereco.DadosEndereco;
import bartieres.crud.springboot3.domain.medico.DadosCadastroMedico;
import bartieres.crud.springboot3.domain.medico.EEspecialidade;
import bartieres.crud.springboot3.domain.medico.Medico;
import bartieres.crud.springboot3.domain.medico.MedicoRepository;
import bartieres.crud.springboot3.domain.paciente.DadosCadastroPaciente;
import bartieres.crud.springboot3.domain.paciente.Paciente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

/**
 * Para usar teste com banco de memoria, basta adicionar a dependência do H2
 * e não definir arquivo -test.yml nem ActiveProfiles
 */
@DataJpaTest
public class MedicoRepositoryTestMemory {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico Teste", "med@gmail.com", "32432", EEspecialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente Teste");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(EEspecialidade.CARDIOLOGIA, proximaSegundaAs10);
        Assertions.assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = cadastrarMedico("Medico Teste", "med@gmail.com", "32432", EEspecialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(EEspecialidade.CARDIOLOGIA, proximaSegundaAs10);
        Assertions.assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, EEspecialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome) {
        var paciente = new Paciente(dadosPaciente(nome));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, EEspecialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome) {
        return new DadosCadastroPaciente(nome);
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}
