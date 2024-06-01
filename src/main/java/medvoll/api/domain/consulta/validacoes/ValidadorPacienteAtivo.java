package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.paciente.PacienteRepository;
import medvoll.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private UsuarioRepository repository;

    public void validar(DadosAgendamento dados) {

        var usuarioAtivo = repository.findByIdAndStatus(dados.idUsuario(), true);
        if (usuarioAtivo.isEmpty()) {
            throw new RuntimeException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
