package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.ConsultaRepository;
import medvoll.api.domain.consulta.DadosAgendamento;

public class ValidadorPacienteSemOutraConsultaNoDia {

    private ConsultaRepository repository;

    public void validar(DadosAgendamento dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new RuntimeException("Paciente já possui uma consulta agendada nesse dia.");
        }
    }
}
