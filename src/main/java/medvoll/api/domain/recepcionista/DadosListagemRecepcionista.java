package medvoll.api.domain.recepcionista;

import java.time.LocalDate;

public record DadosListagemRecepcionista(Long id, String nome, String telefone, LocalDate dataContratacao, LocalDate dataNascimento, Float salario, String cpf) {

    public DadosListagemRecepcionista(RecepcionistaEntity recepcionista) {
        this(recepcionista.getId(), recepcionista.getNome(), recepcionista.getTelefone(), recepcionista.getDataContratacao(), recepcionista.getDataNascimento(), recepcionista.getSalario(), recepcionista.getCpf());
    }
}
