package medvoll.api.domain.recepcionista;

import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import medvoll.api.domain.endereco.Endereco;

import java.time.LocalDate;

public record DadosAtualizarRecepcionista(
        String nome,
        String telefone,
        LocalDate dataContratacao,
        LocalDate dataNascimento,
        Float salario,
        String cpf,

        @Embedded
        Endereco endereco
) {
}
