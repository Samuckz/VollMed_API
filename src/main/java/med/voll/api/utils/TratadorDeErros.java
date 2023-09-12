package med.voll.api.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

@RestControllerAdvice // Notação de classe que trata erros
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class) // tipo de erro a ser analisado
    public ResponseEntity tratarError404(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar este elemento");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();
        return ResponseEntity.badRequest().body(error.stream().map(DadosErroValidacao::new).collect(Collectors.toList()));
    }


}
