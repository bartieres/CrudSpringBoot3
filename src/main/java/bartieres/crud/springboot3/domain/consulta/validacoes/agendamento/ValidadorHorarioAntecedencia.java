package bartieres.crud.springboot3.domain.consulta.validacoes.agendamento;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinuto = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaEmMinuto < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
