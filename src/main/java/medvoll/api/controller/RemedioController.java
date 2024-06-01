package medvoll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.api.domain.remedio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/remedios")
@SecurityRequirement(name = "bearer-key")
public class RemedioController {

    private RemedioEntity remedioEntity;

    @Autowired
    private RemedioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRemedio dados, UriComponentsBuilder uriBuilder) {
        var remedio = new RemedioEntity(dados);
        repository.save(remedio);

        var uri = uriBuilder.path("/remedios/{id}").buildAndExpand(remedio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRemedios>> listar(@PageableDefault() Pageable paginacao) {

      var page = repository.findAll(paginacao).map(DadosListagemRemedios::new);

      return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var remedio = repository.getById(id);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarRemedios dados, @PathVariable Long id) {
        var remedio = repository.getById(id);

        remedio.atualizar(dados);

        if (dados.estoque() < 0) {
            throw new RuntimeException("Não é possivel cadastrar estoque negativo");
        }

        repository.save(remedio);

        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

    @PutMapping("/{id}/diminuirestoque")
    @Transactional
    public ResponseEntity diminuirEstoque(@PathVariable Long id) {
        var remedio = repository.getById(id);

        remedio.reduzirEstoque();

        repository.save(remedio);
        return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
    }

}
