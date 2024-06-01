package medvoll.api.domain.usuario;

public record DadosDetalhamentoUsuario(
        String nome,
        String login,
        String senha) {

    public DadosDetalhamentoUsuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
