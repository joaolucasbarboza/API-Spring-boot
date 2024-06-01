package medvoll.api.controller;

import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;
import medvoll.api.domain.usuario.DadosCadastroUsuarios;
import medvoll.api.domain.usuario.DadosDetalhamentoUsuario;
import medvoll.api.domain.usuario.Usuario;
import medvoll.api.domain.usuario.UsuarioRepository;
import medvoll.api.infra.exception.EmailJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosCadastroUsuarios dados, UriComponentsBuilder uriBuilder) {

        if (repository.existsByLogin(dados.login())) {
            throw new EmailJaCadastradoException("E-mail já cadastrado");
        }

        var senhaBCrypt = passwordEncoder.encode(dados.senha());

        var usuario = new Usuario(dados.nome(), dados.login(), senhaBCrypt);

        repository.save(usuario);

        var usuarioDetalhamento = new DadosDetalhamentoUsuario(usuario.getNome(), usuario.getLogin(), usuario.getSenha());

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getLogin()).toUri();

        return ResponseEntity.created(uri).body(usuarioDetalhamento);
    }
}
