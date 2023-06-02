package bartieres.crud.springboot3.domain.consulta.validacoes;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamentoConsulta dados) {

        var dataConsulta = dados.data();
        var dataAgora = LocalDateTime.now();
        var diferencaEmMinuto = Duration.between(dataAgora, dataConsulta).toMinutes();

        if (diferencaEmMinuto < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos.");
        }
    }
}
