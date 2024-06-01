package medvoll.api.domain.consulta;

import java.util.List;

public record DadosAtualizarConsulta(
        Float valor,
        FormaPagamento formaPagamento,
        List<Long> idRemedios
) {
}
