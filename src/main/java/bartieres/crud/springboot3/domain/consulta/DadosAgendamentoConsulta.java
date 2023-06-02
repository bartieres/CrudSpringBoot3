package bartieres.crud.springboot3.domain.consulta;

import bartieres.crud.springboot3.domain.medico.EEspecialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        EEspecialidade especialidade) {
}
