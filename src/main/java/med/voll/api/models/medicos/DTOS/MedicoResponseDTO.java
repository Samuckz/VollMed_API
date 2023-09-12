package med.voll.api.models.medicos.DTOS;

import lombok.*;
import med.voll.api.models.medicos.MedicoModel;
import med.voll.api.utils.Especialidade;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicoResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String crm;
    private Especialidade especialidade;


    public MedicoResponseDTO(MedicoModel medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }


}
