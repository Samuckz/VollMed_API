package med.voll.api.models.pacientes.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.models.endereco.EnderecoDTO;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PacienteDTO {

    @NotBlank
    String nome;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String telefone;

    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf;

    @NotNull
    @Valid // Valide esse outro DTO
    EnderecoDTO endereco;
}
