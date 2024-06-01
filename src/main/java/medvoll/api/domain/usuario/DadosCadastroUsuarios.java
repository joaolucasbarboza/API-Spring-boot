package medvoll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuarios(

        @NotBlank
        String nome,

        @NotNull
        String login,

        @NotNull
        String senha) {
}
