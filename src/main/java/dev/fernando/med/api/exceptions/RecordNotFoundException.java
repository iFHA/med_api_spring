package dev.fernando.med.api.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Registro de Id = " + id + " não encontrado");
    }

}
