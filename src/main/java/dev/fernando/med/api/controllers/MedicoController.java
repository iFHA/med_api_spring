package dev.fernando.med.api.controllers;

import dev.fernando.med.api.models.endereco.Endereco;
import dev.fernando.med.api.models.medico.Medico;
import dev.fernando.med.api.models.medico.MedicoConverter;
import dev.fernando.med.api.models.medico.dtos.DadosMedicoDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoConverter medicoConverter;

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody DadosMedicoDTO dadosMedico) {
        Medico m = this.medicoConverter.fromDTO(dadosMedico);
        this.repository.save(m);
        return ResponseEntity.ok(m);
    }
}
