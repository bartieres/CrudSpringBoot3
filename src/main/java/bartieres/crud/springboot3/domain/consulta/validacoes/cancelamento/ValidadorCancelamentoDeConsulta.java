package bartieres.crud.springboot3.domain.consulta.validacoes.cancelamento;

import bartieres.crud.springboot3.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);
}
