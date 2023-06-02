package bartieres.crud.springboot3.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        EMotivoCancelamento motivo) {
}
