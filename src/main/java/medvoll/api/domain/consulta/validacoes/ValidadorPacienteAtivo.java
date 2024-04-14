package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.paciente.PacienteRepository;

public class ValidadorPacienteAtivo {

    private PacienteRepository repository;

    public void validar(DadosAgendamento dados) {

        var pacienteAtivo = repository.findStatusById(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new RuntimeException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
