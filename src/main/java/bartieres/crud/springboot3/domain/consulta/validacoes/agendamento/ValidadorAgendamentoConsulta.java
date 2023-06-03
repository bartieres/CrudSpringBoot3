package bartieres.crud.springboot3.domain.consulta.validacoes.agendamento;

import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
