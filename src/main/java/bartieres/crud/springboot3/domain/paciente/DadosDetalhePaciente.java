package bartieres.crud.springboot3.domain.paciente;

public record DadosDetalhePaciente(Long id, String nome) {

    public DadosDetalhePaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome());
    }
}
