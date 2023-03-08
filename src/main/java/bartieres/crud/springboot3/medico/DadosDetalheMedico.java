package bartieres.crud.springboot3.medico;

import bartieres.crud.springboot3.endereco.Endereco;

public record DadosDetalheMedico(Long id, String nome, String email, String crm,
                                 EEspecialidade especialidade, Endereco endereco) {

    public DadosDetalheMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(),
                medico.getEspecialidade(), medico.getEndereco());
    }
}
