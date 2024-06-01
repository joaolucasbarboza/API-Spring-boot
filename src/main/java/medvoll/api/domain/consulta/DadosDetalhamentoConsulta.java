package medvoll.api.domain.consulta;

import medvoll.api.domain.remedio.DadosRemedio;
import medvoll.api.domain.remedio.RemedioEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DadosDetalhamentoConsulta(Long id, Long idMedico, Long idUsuario, LocalDateTime data, Float valor,
                                        FormaPagamento formaPagamento, List<DadosRemedio> remedios) {
    public DadosDetalhamentoConsulta(ConsultaEntity consulta) {
        this(
                consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getUsuario().getId(),
                consulta.getData(),
                consulta.getValor(),
                consulta.getFormaPagamento(),
                consulta.getRemedios().stream().map(DadosRemedio::new).collect(Collectors.toList())
                );
    }
}
