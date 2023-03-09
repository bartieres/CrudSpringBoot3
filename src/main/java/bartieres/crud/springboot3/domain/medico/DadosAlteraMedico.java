package bartieres.crud.springboot3.domain.medico;

import bartieres.crud.springboot3.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAlteraMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
