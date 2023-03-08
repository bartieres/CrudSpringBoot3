package bartieres.crud.springboot3.medico;

import bartieres.crud.springboot3.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAlteraMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
