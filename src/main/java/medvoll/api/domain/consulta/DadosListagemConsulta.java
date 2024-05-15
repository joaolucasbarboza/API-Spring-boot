package medvoll.api.domain.consulta;

import medvoll.api.domain.medico.Medico;
import medvoll.api.domain.paciente.Paciente;
import medvoll.api.domain.remedio.RemedioEntity;

import java.time.LocalDateTime;
import java.util.List;

public record DadosListagemConsulta(Long id, Medico medico, Paciente paciente, LocalDateTime data_consulta, MotivoCancelamento motivoCancelamento, List<RemedioEntity> remedios_tomados) {

    public DadosListagemConsulta(ConsultaEntity consulta) {
        this(consulta.getId(), consulta.getMedico(), consulta.getPaciente(), consulta.getData(), consulta.getMotivoCancelamento(), consulta.getRemedios());
    }
}
