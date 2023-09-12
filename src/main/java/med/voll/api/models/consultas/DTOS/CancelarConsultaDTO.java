package med.voll.api.models.consultas.DTOS;

import lombok.Getter;
import med.voll.api.utils.Cancelamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class CancelarConsultaDTO {
    @NotNull
    Long id;
    @NotNull
    Cancelamento cancelamento;


}
