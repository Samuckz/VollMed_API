package med.voll.api.models.pacientes;

import lombok.*;
import med.voll.api.models.endereco.EnderecoModel;
import med.voll.api.models.pacientes.DTOS.PacienteDTO;
import med.voll.api.models.pacientes.DTOS.PacienteDTOUpdate;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@EqualsAndHashCode(of = "id")

@Table(name="pacientes")
@Entity(name = "Paciente")
public class PacienteModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Boolean ativo;

    @Embedded
    private EnderecoModel endereco;

    public PacienteModel(PacienteDTO paciente) {
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
        this.telefone = paciente.getTelefone();
        this.cpf = paciente.getCpf();
        this.endereco = new EnderecoModel(paciente.getEndereco());
        this.ativo = true;
    }

    public void atualizarDados(PacienteDTOUpdate paciente){
        if(paciente.getNome() != null)
            this.nome = paciente.getNome();

        if(paciente.getTelefone() != null)
            this.telefone = paciente.getTelefone();

        if(paciente.getEnderecoDTO()!= null)
            this.endereco.atualizarEndereco(paciente.getEnderecoDTO());

    }

    public void delete() {
        this.ativo = false;
    }
}
