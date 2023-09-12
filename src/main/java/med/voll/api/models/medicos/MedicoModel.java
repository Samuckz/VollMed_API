package med.voll.api.models.medicos;

import lombok.*;
import med.voll.api.models.endereco.EnderecoModel;
import med.voll.api.models.medicos.DTOS.MedicoDTO;
import med.voll.api.models.medicos.DTOS.MedicoDTOUpdate;
import med.voll.api.utils.Especialidade;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Table(name="medicos")
@Entity(name = "Medico")

public class MedicoModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private EnderecoModel endereco;

    public MedicoModel(MedicoDTO medico) {
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.endereco = new EnderecoModel(medico.getEndereco());
        this.ativo = true;
    }


    public void atualizarDados(MedicoDTOUpdate medico) {
        if(medico.getNome() != null)
            this.nome = medico.getNome();

        if(medico.getTelefone() != null)
            this.telefone = medico.getTelefone();

        if(medico.getEndereco() != null)
            this.endereco.atualizarEndereco(medico.getEndereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
