package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;
import medvoll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamento dados)  {

        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findStatusById(dados.idMedico());

        if (!medicoEstaAtivo) {
            throw new RuntimeException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
