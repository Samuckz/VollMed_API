package med.voll.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosErroValidacao extends Throwable {
    private String campo;
    private String message;

    public DadosErroValidacao(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
