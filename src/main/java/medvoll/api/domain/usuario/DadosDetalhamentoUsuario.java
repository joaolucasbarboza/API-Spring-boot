package medvoll.api.domain.usuario;

public record DadosDetalhamentoUsuario(
        String nome,
        String login,
        String cpf,
        String senha) {

    public DadosDetalhamentoUsuario(String nome, String login, String cpf, String senha) {
        this.nome = nome;
        this.login = login;
        this.cpf = cpf;
        this.senha = senha;
    }
}
