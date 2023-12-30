package dev.fernando.med.api.controllers;

import dev.fernando.med.api.domain.consulta.dtos.AgendamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.CancelamentoConsultaDTO;
import dev.fernando.med.api.domain.consulta.dtos.DetalhamentoConsultaDTO;
import dev.fernando.med.api.services.AgendaDeConsultasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {
    @Autowired
    private AgendaDeConsultasService agenda;
    @PostMapping
    public ResponseEntity<DetalhamentoConsultaDTO> agendar(@RequestBody @Valid AgendamentoConsultaDTO dto) {
        return ResponseEntity.ok(agenda.agendar(dto));
    }
    @DeleteMapping
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancelamentoConsultaDTO dto) {
        agenda.cancelarAgendamento(dto);
        return ResponseEntity.noContent().build();
    }
}
