package bartieres.crud.springboot3.domain.medico;

import bartieres.crud.springboot3.domain.endereco.Endereco;

public record DadosDetalheMedico(Long id, String nome, String email, String crm,
                                 EEspecialidade especialidade, Endereco endereco) {

    public DadosDetalheMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }
}
