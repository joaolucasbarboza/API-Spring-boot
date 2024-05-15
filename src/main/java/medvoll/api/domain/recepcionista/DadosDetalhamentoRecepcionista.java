package medvoll.api.domain.recepcionista;

import medvoll.api.domain.endereco.Endereco;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public record DadosDetalhamentoRecepcionista(
        Long id,
        String nome,
        String telefone,
        LocalDate dataContratacao,
        LocalDate dataNascimento,
        Float salario,
        String cpf,
        Endereco endereco,
        Boolean status) {

    public DadosDetalhamentoRecepcionista(RecepcionistaEntity recepcionista) {
        this(recepcionista.getId(), recepcionista.getNome(), recepcionista.getTelefone(), recepcionista.getDataContratacao(), recepcionista.getDataNascimento(), recepcionista.getSalario(), recepcionista.getCpf(), recepcionista.getEndereco(), recepcionista.getStatus());
    }
}
