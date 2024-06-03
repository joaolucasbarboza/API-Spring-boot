package medvoll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUsuarios(

        @NotBlank
        String nome,

        @NotNull
        String login,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull
        String senha) {
}
