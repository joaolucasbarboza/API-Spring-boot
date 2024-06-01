package medvoll.api.domain.consulta;

import medvoll.api.domain.medico.Medico;
import medvoll.api.domain.remedio.RemedioEntity;
import medvoll.api.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DadosListagemConsulta(Long id, Medico medico, Usuario usuario, LocalDateTime data_consulta, MotivoCancelamento motivoCancelamento, List<RemedioEntity> remedios_tomados, FormaPagamento formaPagamento) {

    public DadosListagemConsulta(ConsultaEntity consulta) {
        this(consulta.getId(), consulta.getMedico(), consulta.getUsuario(), consulta.getData(), consulta.getMotivoCancelamento(), consulta.getRemedios(), consulta.getFormaPagamento());
    }
}
