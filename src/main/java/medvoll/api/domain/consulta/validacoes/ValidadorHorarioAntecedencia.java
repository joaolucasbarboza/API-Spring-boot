package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamento dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new RuntimeException("Consulta deve ser agendada com antecedencia mínima de 30 minutos");
        }
    }
}
