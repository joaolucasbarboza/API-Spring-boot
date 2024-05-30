package medvoll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

        String logradouro,


        String bairro,


        @Pattern(regexp = "\\d{8}")
        String cep,


        String cidade,


        String uf,

        String complemento,
        String numero) {
}
