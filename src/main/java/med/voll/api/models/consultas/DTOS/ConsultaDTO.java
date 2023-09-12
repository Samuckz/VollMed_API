package med.voll.api.models.consultas.DTOS;

import lombok.Getter;
import med.voll.api.utils.DataHoraDTO;

import javax.validation.constraints.NotNull;

@Getter
public class ConsultaDTO {
    @NotNull
    Long medico;

    @NotNull
    Long paciente;

    @NotNull
    DataHoraDTO dataHoraDTO;
}
