package med.voll.api.models.endereco;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Embeddable
public class EnderecoModel {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public EnderecoModel(EnderecoDTO endereco) {
        this.logradouro = endereco.logradouro;
        this.bairro = endereco.bairro;
        this.cep = endereco.cep;
        this.cidade = endereco.cidade;
        this.uf = endereco.uf;
        this.complemento = endereco.complemento;
        this.numero = endereco.numero;
    }

    public void atualizarEndereco(EnderecoDTO endereco) {
        if (endereco.logradouro != null) {
            this.logradouro = endereco.logradouro;
        }
        if (endereco.bairro != null) {
            this.bairro = endereco.bairro;
        }
        if (endereco.cep != null) {
            this.cep = endereco.cep;
        }
        if (endereco.cidade != null) {
            this.cidade = endereco.cidade;
        }
        if (endereco.uf != null) {
            this.uf = endereco.uf;
        }
        if (endereco.complemento != null) {
            this.complemento = endereco.complemento;
        }
        if (endereco.numero != null) {
            this.numero = endereco.numero;
        }

    }
}
