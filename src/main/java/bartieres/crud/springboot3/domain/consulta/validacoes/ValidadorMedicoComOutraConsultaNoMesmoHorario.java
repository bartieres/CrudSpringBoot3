package bartieres.crud.springboot3.domain.consulta.validacoes;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.ConsultaRepository;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
