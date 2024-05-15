package medvoll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.api.domain.recepcionista.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<Page<DadosListagemRecepcionista>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable pageable) {
        var page = repository.findAllByStatusTrue(pageable).map(DadosListagemRecepcionista::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarRecepcionista dados, @PathVariable Long id) {
        var recepcionista = repository.getReferenceById(id);

        recepcionista.atualizarInformacoes(dados);

        repository.save(recepcionista);

        return ResponseEntity.ok(new DadosDetalhamentoRecepcionista(recepcionista));
    }
}
