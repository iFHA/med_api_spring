package dev.fernando.med.api.controllers;

import dev.fernando.med.api.infra.security.TokenService;
import dev.fernando.med.api.models.usuario.Usuario;
import dev.fernando.med.api.models.usuario.dtos.DadosAutenticacaoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<String> efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dto){
        var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var authentication = this.authenticationManager.authenticate(token);

        return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
    }
}
