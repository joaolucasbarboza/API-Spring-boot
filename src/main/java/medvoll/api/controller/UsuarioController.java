package medvoll.api.controller;

import jakarta.validation.Valid;
import medvoll.api.domain.usuario.*;
import medvoll.api.infra.exception.EmailJaCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
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

        var usuario = new Usuario(dados.nome(), dados.login(), dados.cpf(), senhaBCrypt);

        repository.save(usuario);

        var usuarioDetalhamento = new DadosDetalhamentoUsuario(usuario.getNome(), usuario.getLogin(), usuario.getCpf(), usuario.getSenha());

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getLogin()).toUri();

        return ResponseEntity.created(uri).body(usuarioDetalhamento);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarios>> listar(@PageableDefault Pageable pageable) {

        var usuarios = repository.findAll(pageable).map(DadosListagemUsuarios::new);

        return ResponseEntity.ok(usuarios);
    }
}
