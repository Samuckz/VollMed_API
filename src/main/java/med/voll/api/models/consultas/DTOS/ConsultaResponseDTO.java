package med.voll.api.models.consultas.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.consultas.ConsultaModel;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaResponseDTO {
    private Long id;
    private String medico;
    private String paciente;
    private LocalDateTime dataHora;


    public ConsultaResponseDTO(ConsultaModel consulta) {
        this(consulta.getId(), consulta.getMedico().getNome(), consulta.getPaciente().getNome(), consulta.getData());
    }
}
