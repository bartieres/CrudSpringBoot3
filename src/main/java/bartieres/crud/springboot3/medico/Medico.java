package bartieres.crud.springboot3.medico;

import bartieres.crud.springboot3.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private boolean ativo;

    @Enumerated
    private EEspecialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAlteraMedico dados) {
        if (Objects.nonNull(dados.nome())) {
            this.nome = dados.nome();
        }
        if (Objects.nonNull(dados.telefone())) {
            this.telefone = dados.telefone();
        }
        if (Objects.nonNull(dados.endereco())) {
            endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
