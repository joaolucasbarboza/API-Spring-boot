package medvoll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.api.domain.consulta.*;
import medvoll.api.domain.remedio.RemedioEntity;
import medvoll.api.domain.remedio.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendarConsultas agenda;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private RemedioRepository remedioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados) {
        var dto = agenda.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemConsulta>> listar(@PageableDefault() Pageable pageable) {

        return ResponseEntity.ok(consultaRepository.findAll(pageable).map(DadosListagemConsulta::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarConsulta dados, @PathVariable Long id) {


        var consulta = consultaRepository.getById(id);

        List<RemedioEntity> novosRemedios = remedioRepository.findAllById(dados.idRemedios());

        consulta.atualizar(dados, novosRemedios);

        consultaRepository.save(consulta);

        return ResponseEntity.ok(new DadosDetalhamentoConsulta(consulta));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
