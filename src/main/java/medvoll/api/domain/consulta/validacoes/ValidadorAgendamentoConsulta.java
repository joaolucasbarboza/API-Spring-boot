package medvoll.api.domain.consulta.validacoes;

import medvoll.api.domain.consulta.DadosAgendamento;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamento dados);
}
