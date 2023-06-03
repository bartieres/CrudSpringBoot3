package bartieres.crud.springboot3.domain.consulta.validacoes.agendamento;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SATURDAY);

        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisFechamentoClinica = dataConsulta.getHour() > 18;

        if (domingo || antesAberturaClinica || depoisFechamentoClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
