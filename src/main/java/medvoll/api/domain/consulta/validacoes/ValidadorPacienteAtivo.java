package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamento dados) {

        var pacienteAtivo = repository.findByIdAndStatus(dados.idPaciente(), true);
        if (pacienteAtivo.isEmpty()) {
            throw new RuntimeException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
