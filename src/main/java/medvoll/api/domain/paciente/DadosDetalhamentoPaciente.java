package medvoll.api.domain.paciente;

import medvoll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(
        Long id,
        String nome,
        String login,
        String senha,
        String telefone,
        String cpf
        ) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getLogin(), paciente.getSenha(), paciente.getTelefone(), paciente.getCpf());
    }
}
