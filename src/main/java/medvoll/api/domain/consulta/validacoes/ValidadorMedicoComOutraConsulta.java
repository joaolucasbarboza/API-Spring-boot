package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.ConsultaRepository;
import medvoll.api.domain.consulta.DadosAgendamento;

public class ValidadorMedicoComOutraConsulta {


    private ConsultaRepository repository;

    public void validar(DadosAgendamento dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new RuntimeException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
