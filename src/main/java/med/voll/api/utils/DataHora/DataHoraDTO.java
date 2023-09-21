package med.voll.api.utils.DataHora;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataHoraDTO {
    @NotNull
    int dia;

    @NotNull
    int mes;

    @NotNull
    int ano;

    @NotNull
    int hora;

    @NotNull
    int minutos;
}
