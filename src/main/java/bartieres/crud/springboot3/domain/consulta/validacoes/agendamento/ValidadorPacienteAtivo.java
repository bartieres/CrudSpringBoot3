package bartieres.crud.springboot3.domain.consulta.validacoes.agendamento;

import bartieres.crud.springboot3.domain.ValidacaoException;
import bartieres.crud.springboot3.domain.consulta.DadosAgendamentoConsulta;
import bartieres.crud.springboot3.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluido.");
        }
    }
}
