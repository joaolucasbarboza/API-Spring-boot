package medvoll.api.domain.consulta;

import medvoll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
import medvoll.api.domain.medico.Medico;
import medvoll.api.domain.medico.MedicoRepository;
import medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendarConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamento dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new RuntimeException("Paciente não encontrado!");
        }

        System.out.println("passei aqui no if de paciente nao encontrado");
        
        if (medicoRepository != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new RuntimeException("Medico não encontrado");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        System.out.println(paciente);

        var medico = medicoAleatorio(dados);
        System.out.println(medico);

        ConsultaEntity consulta = new ConsultaEntity();
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setData(dados.data());
        consulta.setMotivoCancelamento(MotivoCancelamento.PACIENTE_DESISTIU);

        System.out.println(consulta);

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico medicoAleatorio(DadosAgendamento dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.findById(dados.idMedico()).orElseThrow();
        }

        if (dados.especialidade() == null) {
            throw new RuntimeException("Especialidade é obrigatória quando o médico não for escolhido.");
        }

        return medicoRepository.medicoAleatoriodb(dados.especialidade(), dados.data());

    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new RuntimeException("Id da consulta informado não existe");
        }

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
