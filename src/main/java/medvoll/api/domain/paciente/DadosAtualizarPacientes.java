package medvoll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import medvoll.api.domain.endereco.DadosEndereco;

public record DadosAtualizarPacientes(
        String nome,
        String telefone,
        DadosEndereco endereco

    ) {
}
