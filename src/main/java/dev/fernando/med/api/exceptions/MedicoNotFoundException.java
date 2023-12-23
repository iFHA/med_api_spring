package dev.fernando.med.api.exceptions;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(Long id) {
        super("Médico de Id = " + id + " não encontrado");
    }
}
