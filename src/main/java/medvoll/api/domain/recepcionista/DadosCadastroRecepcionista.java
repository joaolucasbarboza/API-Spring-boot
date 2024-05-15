package medvoll.api.domain.recepcionista;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medvoll.api.domain.endereco.DadosEndereco;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record DadosCadastroRecepcionista(

        @NotNull
        String nome,

        @NotBlank
        String telefone,

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dataContratacao,

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,

        @NotNull
        Float salario,

        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull
        @Valid
        DadosEndereco endereco

) {
}
