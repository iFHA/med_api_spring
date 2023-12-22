package dev.fernando.med.api.controllers;

import dev.fernando.med.api.models.medico.Medico;
import dev.fernando.med.api.models.medico.MedicoConverter;
import dev.fernando.med.api.models.medico.dtos.DadosAtualizacaoMedicoDTO;
import dev.fernando.med.api.models.medico.dtos.DadosCadastroMedicoDTO;
import dev.fernando.med.api.models.medico.dtos.ListagemMedicoDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoConverter medicoConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrar(@Valid @RequestBody DadosCadastroMedicoDTO dadosMedico) {
        Medico m = this.medicoConverter.fromDTO(dadosMedico);
        this.repository.save(m);
        return ResponseEntity.ok(m);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDTO>> getAll(Pageable pageable) {
        return ResponseEntity.ok(
                this.repository.findAll(pageable)
                .map(this.medicoConverter::toListagemMedicoDTO)
        );
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dto, @PathVariable Long id) {
        Medico m = this.repository.getReferenceById(id);
        m.atualizarInformacoes(dto);
        this.repository.save(m);
        return ResponseEntity.ok().build();
    }
}
