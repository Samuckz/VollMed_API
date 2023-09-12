package med.voll.api.models.consultas.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponseDTO {
    private Long id;
    private String medico;
    private String paciente;
    private String dataHora;


}
