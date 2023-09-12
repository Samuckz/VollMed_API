package med.voll.api.models.pacientes.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.pacientes.PacienteModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PacienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    public PacienteResponseDTO(PacienteModel pacienteModel){
        this(pacienteModel.getId(), pacienteModel.getNome(), pacienteModel.getEmail(), pacienteModel.getCpf());
    }
}
