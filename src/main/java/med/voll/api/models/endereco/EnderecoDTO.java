package med.voll.api.models.endereco;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class EnderecoDTO {

    @NotBlank
    String logradouro;

    @NotBlank
    String bairro;

    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep;

    @NotBlank
    String cidade;

    @NotBlank
    String uf;

    String complemento;

    String numero;
}
