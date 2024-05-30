package medvoll.api.domain.remedio;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.api.domain.consulta.FormaPagamento;

public record DadosCadastroRemedio(

        @NotBlank
        String descricao,

        String resumoMedicamento,

        @NotBlank
        @Pattern(regexp = "\\d{13}")
        @JsonProperty("numero_registro")
        String numeroRegistro,

        @NotNull
        Long estoque,

//        @JsonProperty("forma_pagamento")
//        FormaPagamento formaPagamento,

        @NotNull
        Dosagem dosagem,

        @NotNull
        @JsonProperty("tipo_remedio")
        TipoRemedio tipoRemedio
) {
}
