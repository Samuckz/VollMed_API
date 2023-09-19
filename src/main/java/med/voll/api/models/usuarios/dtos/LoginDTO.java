package med.voll.api.models.usuarios.dtos;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoginDTO {
    @NotBlank
    String login;

    @NotBlank
    String senha;
}
