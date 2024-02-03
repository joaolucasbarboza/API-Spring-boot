package medvoll.api.medico;

import jakarta.validation.constraints.NotNull;
import medvoll.api.endereco.DadosEndereco;

public record DadosAtualizarMedicos(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco

    ) {
}
