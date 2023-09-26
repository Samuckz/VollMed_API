package med.voll.api.models.consultas.DTOS;

import med.voll.api.utils.Cancelamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CancelarConsultaResponseDTO {
    @NotNull
    Long id;

    @NotNull
    Cancelamento justificativa;

    @NotNull
    Boolean ativo;
}
