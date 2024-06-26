package medvoll.api.domain.medico;

import medvoll.api.domain.consulta.ConsultaEntity;
import medvoll.api.domain.consulta.MotivoCancelamento;
import medvoll.api.domain.endereco.DadosEndereco;
import medvoll.api.domain.paciente.DadosCadastroPacientes;
import medvoll.api.domain.paciente.Paciente;
import medvoll.api.domain.usuario.DadosCadastroUsuarios;
import medvoll.api.domain.usuario.Usuario;
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
    @DisplayName("Deveria devolver null o quando unico medico cadastrado nao esta disponivel na data.")
    void medicoAleatoriodbCenario1() {
        var proximaSegundaAs10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico("Medico", "medico@vell.med", "123456", Especialidade.CARDIOLOGIA);
        var usuario = cadastrarUsuario("João Lucas", "joao@gmail.com", "41340766841", "123456");
        cadastrarConsulta(medico, usuario, proximaSegundaAs10);

        var medicoLivre = medicoRepository.medicoAleatoriodb(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
        assertThat(medicoLivre).isNull();
    }

    private void cadastrarConsulta(Medico medico, Usuario usuario, LocalDateTime data) {
        ConsultaEntity consulta = new ConsultaEntity();

        consulta.setMedico(medico);
        consulta.setUsuario(usuario);
        consulta.setData(data);
        consulta.setMotivoCancelamento(MotivoCancelamento.PACIENTE_DESISTIU);

        em.persist(consulta);

    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }
    private Usuario cadastrarUsuario(String nome, String login, String cpf,  String senha) {
        var usuario = new Usuario(nome, login, cpf, senha);
        em.persist(usuario);
        return usuario;
    }

    private DadosCadastroMedicos dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicos(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPacientes dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPacientes(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosCadastroUsuarios dadosUsuarios(String nome, String login, String cpf, String senha) {
        return new DadosCadastroUsuarios(
                nome,
                login,
                cpf,
                senha
        );
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