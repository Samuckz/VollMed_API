package med.voll.api.models.consultas.DTOS;

import lombok.Getter;
import med.voll.api.utils.DataHora.DataHoraDTO;
import med.voll.api.utils.Especialidade;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ConsultaDTO {
    Long medico;

    @NotNull
    Long paciente;

    @NotNull
    @Future
    LocalDateTime data;

    Especialidade especialidade; // opcional
}
