package bartieres.crud.springboot3.medico;

public record DadosListaMedico(
        Long id,
        String nome,
        String email,
        String crm,
        EEspecialidade especialidade) {

    public DadosListaMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
