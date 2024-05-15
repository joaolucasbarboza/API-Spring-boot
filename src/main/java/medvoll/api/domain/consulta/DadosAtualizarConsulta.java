package medvoll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Role;

public record DadosAtualizarConsulta(
              @NotNull
              Long id,
              FormaPagamento formaPagamento
) {
}
