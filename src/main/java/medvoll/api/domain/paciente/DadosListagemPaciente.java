package medvoll.api.domain.paciente;

import medvoll.api.domain.endereco.Endereco;

public record DadosListagemPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf
    ) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getLogin(), paciente.getTelefone(), paciente.getCpf());
    }
}
