package dev.fernando.med.api.controllers;

import dev.fernando.med.api.models.endereco.Endereco;
import dev.fernando.med.api.models.medico.Medico;
import dev.fernando.med.api.models.medico.MedicoConverter;
import dev.fernando.med.api.models.medico.dtos.DadosMedicoDTO;
import dev.fernando.med.api.models.medico.dtos.ListagemMedicoDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoConverter medicoConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrar(@Valid @RequestBody DadosMedicoDTO dadosMedico) {
        Medico m = this.medicoConverter.fromDTO(dadosMedico);
        this.repository.save(m);
        return ResponseEntity.ok(m);
    }

    @GetMapping
    public ResponseEntity<List<ListagemMedicoDTO>> getAll() {
        return ResponseEntity.ok(
                this.repository.findAll()
                        .stream()
                        .map(this.medicoConverter::toListagemMedicoDTO)
                        .sorted(Comparator.comparing(ListagemMedicoDTO::nome))
                        .toList()
        );
    }
}
