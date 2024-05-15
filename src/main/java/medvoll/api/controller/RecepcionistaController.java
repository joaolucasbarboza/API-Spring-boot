package medvoll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.api.domain.recepcionista.DadosCadastroRecepcionista;
import medvoll.api.domain.recepcionista.DadosDetalhamentoRecepcionista;
import medvoll.api.domain.recepcionista.RecepcionistaEntity;
import medvoll.api.domain.recepcionista.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/recepcionistas")
@SecurityRequirement(name = "bearer-key")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRecepcionista dados, UriComponentsBuilder uriBuilder) {
    var recepcionista = new RecepcionistaEntity(dados);
    repository.save(recepcionista);

    var uri = uriBuilder.path("/recepcionistas/{id}").buildAndExpand(recepcionista.getId()).toUri();

    return ResponseEntity.created(uri).body(new DadosDetalhamentoRecepcionista(recepcionista));
    }
}
