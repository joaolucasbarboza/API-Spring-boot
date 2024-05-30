package medvoll.api.controller;

import jakarta.validation.Valid;
import medvoll.api.domain.paciente.Paciente;
import medvoll.api.domain.paciente.PacienteDadosAutenticacao;
import medvoll.api.infra.security.TokenDadosJWT;
import medvoll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid PacienteDadosAutenticacao dados) {
        var authenticationtoken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationtoken);

        var tokenJWT = tokenService.gerarToken((Paciente) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDadosJWT(tokenJWT));
    }

}
