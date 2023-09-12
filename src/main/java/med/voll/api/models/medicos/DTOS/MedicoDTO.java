package med.voll.api.models.medicos.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.endereco.EnderecoDTO;
import med.voll.api.utils.Especialidade;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MedicoDTO {

    @NotBlank // apenas para strings
    String nome;

    @NotBlank
    @Email // formatação de email
    String email;

    @NotBlank
    String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{4,6}") // define que os valores devem ser digitos, de 4 a 6 digitos
    String crm;

    @NotNull
    Especialidade especialidade;

    @NotNull
    @Valid // Valide esse outro DTO
    EnderecoDTO endereco;

}
