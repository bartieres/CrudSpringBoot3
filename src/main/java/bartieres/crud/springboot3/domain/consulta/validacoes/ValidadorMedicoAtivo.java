package bartieres.crud.springboot3.domain.consulta.validacoes;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;
import bartieres.crud.springboot3.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {

        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluido.");
        }
    }
}
