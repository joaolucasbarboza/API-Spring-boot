package medvoll.api.domain.consulta;

import medvoll.api.domain.medico.Medico;
import medvoll.api.domain.medico.MedicoRepository;
import medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendarConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamento dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new RuntimeException("Paciente não encontrado!");
        }
        
        if (medicoRepository != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new RuntimeException("Medico não encontrado");
        }
        
        var medico = medicoAleatorio(dados);
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
    }

    private Medico medicoAleatorio(DadosAgendamento dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
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
