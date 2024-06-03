package medvoll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medvoll.api.domain.consulta.AgendarConsultas;
import medvoll.api.domain.consulta.ConsultaEntity;
import medvoll.api.domain.consulta.ConsultaRepository;
import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.consulta.DadosAtualizarConsulta;
import medvoll.api.domain.consulta.DadosCancelamentoConsulta;
import medvoll.api.domain.consulta.DadosDetalhamentoConsulta;
import medvoll.api.domain.consulta.DadosListagemConsulta;
import medvoll.api.domain.remedio.RemedioEntity;
import medvoll.api.domain.remedio.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
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

    @GetMapping("/consultas-hoje")
    public ResponseEntity<List<ConsultaEntity>> listarConsultasHoje() {

        LocalDate today = LocalDate.now();

        LocalDateTime inicioDia = today.atStartOfDay();

        LocalDateTime fimDia = today.atTime(LocalTime.MAX);

        List<ConsultaEntity> consultas = consultaRepository.findByDataBetween(inicioDia, fimDia, Sort.by("data").ascending());

        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/consultas-semana")
    public ResponseEntity<List<ConsultaEntity>> listarConsultasDaSemana() {

        LocalDateTime inicioDaSemana = LocalDateTime.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).toLocalDate().atStartOfDay();
        LocalDateTime fimDaSemana = LocalDateTime.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).toLocalDate().atTime(LocalTime.MAX);

        List<ConsultaEntity> consultas = consultaRepository.findByDataBetween(inicioDaSemana, fimDaSemana, Sort.by("data").ascending());

        return ResponseEntity.ok(consultas);
    }

    @GetMapping("/consultas-mes")
    public ResponseEntity<List<ConsultaEntity>> listarConsultasDoMes() {

        LocalDateTime inicioDoMes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
        LocalDateTime fimDoMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).atTime(LocalTime.MAX);

        List<ConsultaEntity> consultas = consultaRepository.findByDataBetween(inicioDoMes, fimDoMes, Sort.by("data").ascending());

        return ResponseEntity.ok(consultas);
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
