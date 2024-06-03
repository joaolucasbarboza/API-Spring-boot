package medvoll.api.domain.usuario;

public record DadosListagemUsuarios(Long id, String nome, String email, String cpf, Boolean status ) {
    public DadosListagemUsuarios(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getLogin(), usuario.getCpf(), usuario.getStatus());
    }
}
