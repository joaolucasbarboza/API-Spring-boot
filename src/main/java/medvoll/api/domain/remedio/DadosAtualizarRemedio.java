package medvoll.api.domain.remedio;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.api.domain.consulta.FormaPagamento;

public record DadosAtualizarRemedio(
        String descricao,
        String resumoMedicamento,
        String numeroRegistro,
        Long estoque,
        FormaPagamento formaPagamento,
        Dosagem dosagem,
        TipoRemedio tipoRemedio
) {
}
