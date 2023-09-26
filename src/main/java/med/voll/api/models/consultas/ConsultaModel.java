package med.voll.api.models.consultas;

import lombok.*;
import med.voll.api.models.medicos.MedicoModel;
import med.voll.api.models.pacientes.PacienteModel;
import med.voll.api.utils.Cancelamento;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;

    private LocalDateTime data;
    private Boolean ativo = true;

    private Cancelamento justificativa;

    public ConsultaModel(Long id, MedicoModel medico, PacienteModel paciente, LocalDateTime data) {
        this.medico = medico;
        this.paciente = paciente;
        this.data = data;
    }
    public void cancelar(){
        this.ativo = false;
    }
}
