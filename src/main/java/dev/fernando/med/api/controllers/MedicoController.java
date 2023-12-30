package dev.fernando.med.api.controllers;

import dev.fernando.med.api.exceptions.RecordNotFoundException;
import dev.fernando.med.api.domain.medico.Medico;
import dev.fernando.med.api.domain.medico.MedicoConverter;
import dev.fernando.med.api.domain.medico.dtos.DadosAtualizacaoMedicoDTO;
import dev.fernando.med.api.domain.medico.dtos.DadosCadastroMedicoDTO;
import dev.fernando.med.api.domain.medico.dtos.DadosDetalhamentoMedicoDTO;
import dev.fernando.med.api.domain.medico.dtos.ListagemMedicoDTO;
import dev.fernando.med.api.repositories.MedicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository repository;
    @Autowired
    private MedicoConverter medicoConverter;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(
            @Valid @RequestBody DadosCadastroMedicoDTO dadosMedico,
            UriComponentsBuilder uriBuilder
    ) {
        Medico m = this.medicoConverter.fromDTO(dadosMedico);
        this.repository.save(m);
        DadosDetalhamentoMedicoDTO dto = this.medicoConverter.toDadosDetalhamentoMedicoDTO(m);
        return ResponseEntity.created(
                uriBuilder
                        .path("/medicos/{id}")
                        .buildAndExpand(dto.id())
                        .toUri()
                )
                .body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> getById(@PathVariable Long id) {
        DadosDetalhamentoMedicoDTO dto = this.medicoConverter.toDadosDetalhamentoMedicoDTO(this.repository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
        return ResponseEntity.ok(dto);
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
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dto, @PathVariable Long id) {
        Medico m = this.repository.getReferenceById(id);
        m.atualizarInformacoes(dto);
        var dados = this.medicoConverter.toDadosDetalhamentoMedicoDTO(this.repository.save(m));
        return ResponseEntity.ok(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        this.repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
