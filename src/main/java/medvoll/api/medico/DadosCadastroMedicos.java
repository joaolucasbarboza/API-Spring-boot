package medvoll.api.medico;

import medvoll.api.endereco.DadosEndereco;

public record DadosCadastroMedicos(String nome, String email, String crm, Especialidade especialidade,
                                   DadosEndereco endereco) {

}
