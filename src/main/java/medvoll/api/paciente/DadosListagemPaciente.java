package medvoll.api.paciente;

import medvoll.api.endereco.DadosEndereco;
import medvoll.api.endereco.Endereco;

public record DadosListagemPaciente(
        String nome,

        String email,

        String telefone,

        String cpf,

        Endereco endereco

    ) {

    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
