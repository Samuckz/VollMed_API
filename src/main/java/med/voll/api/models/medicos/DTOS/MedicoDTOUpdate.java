package med.voll.api.models.medicos.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.endereco.EnderecoDTO;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTOUpdate {

    @NotNull
    Long id;

    String nome;

    String telefone;

    EnderecoDTO endereco;

}
