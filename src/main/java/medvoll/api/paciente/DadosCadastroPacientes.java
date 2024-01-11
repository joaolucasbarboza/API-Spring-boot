package medvoll.api.paciente;

import medvoll.api.endereco.DadosEndereco;

public record DadosCadastroPacientes(String nome, String email, String telefone, String cpf, DadosEndereco endereco) {
}
