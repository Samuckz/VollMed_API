package med.voll.api.models.consultas;

import lombok.*;
import med.voll.api.models.consultas.DTOS.ConsultaDTO;
import med.voll.api.utils.Cancelamento;
import med.voll.api.utils.DataHora;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")

@Table(name="consultas")
@Entity(name = "Consulta")
public class ConsultaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medico;
    private Long paciente;

    @Embedded
    private DataHora dataHora;
    private Boolean ativo = true;
    private Boolean done = false;

    private Cancelamento justificativa;

    public ConsultaModel(ConsultaDTO consultaDTO) {
        this.medico = consultaDTO.getMedico();
        this.paciente = consultaDTO.getPaciente();
        this.dataHora = new DataHora(consultaDTO.getDataHoraDTO());

    }

}
