package dev.fernando.med.api.controllers;

import dev.fernando.med.api.exceptions.RecordNotFoundException;
import dev.fernando.med.api.domain.paciente.Paciente;
import dev.fernando.med.api.domain.paciente.PacienteConverter;
import dev.fernando.med.api.domain.paciente.dtos.DadosAtualizacaoPacienteDTO;
import dev.fernando.med.api.domain.paciente.dtos.DadosCadastroPacienteDTO;
import dev.fernando.med.api.domain.paciente.dtos.PacienteDTO;
import dev.fernando.med.api.repositories.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private PacienteConverter pacienteConverter;
    @PostMapping
    public ResponseEntity<PacienteDTO> insert(@Valid @RequestBody DadosCadastroPacienteDTO dto, UriComponentsBuilder uriBuilder) {
        Paciente p = pacienteRepository.save(pacienteConverter.fromDadosCadastro(dto));
        return ResponseEntity.created(
                uriBuilder
                        .path("/{id}")
                        .buildAndExpand(p.getId())
                        .toUri()
        ).body(pacienteConverter.toPacienteDTO(p));
    }
    @GetMapping
    public ResponseEntity<Page<PacienteDTO>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(
                pacienteRepository
                        .findAll(pageable)
                        .map(pacienteConverter::toPacienteDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteConverter.toPacienteDTO(findOrFail(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoPacienteDTO dto) {
        Paciente p = findOrFail(id);
        p.atualizarInformacoes(dto);
        pacienteRepository.save(p);
        return ResponseEntity.ok(pacienteConverter.toPacienteDTO(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var p = findOrFail(id);
        pacienteRepository.delete(p);
        return ResponseEntity.noContent().build();
    }

    private Paciente findOrFail(Long id) {
        return pacienteRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }
}
