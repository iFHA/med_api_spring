package dev.fernando.med.api.exceptions;

import lombok.Getter;

public class MedicoNotFoundException extends RuntimeException {
    public MedicoNotFoundException(Long id) {
        super("Médico de Id = " + id + " não encontrado");
    }

}
