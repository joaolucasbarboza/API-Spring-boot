package medvoll.api.controller;

import jakarta.validation.Valid;
import medvoll.api.domain.consulta.AgendarConsultas;
import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.consulta.DadosCancelamentoConsulta;
import medvoll.api.domain.consulta.DadosDetalhamentoConsulta;
import medvoll.api.domain.medico.MedicoRepository;
import medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendarConsultas agenda;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamento dados) {
        agenda.agendar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}
