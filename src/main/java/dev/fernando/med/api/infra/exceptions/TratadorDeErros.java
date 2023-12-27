package dev.fernando.med.api.infra.exceptions;

import dev.fernando.med.api.exceptions.RecordNotFoundException;
import dev.fernando.med.api.models.ErroApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErroApi> handleMedicoNotFound(RecordNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroApi(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> handleParametrosInvalidos(MethodArgumentNotValidException e) {
        List<DadosErroValidacao> erros = e
                .getFieldErrors()
                .stream()
                .map(DadosErroValidacao::new)
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
