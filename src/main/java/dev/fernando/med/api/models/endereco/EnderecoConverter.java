package dev.fernando.med.api.models.endereco;

import org.springframework.stereotype.Component;

@Component
public class EnderecoConverter {
    public Endereco fromDTO (DadosEndereco dto){
        return new Endereco(
                dto.logradouro(),
                dto.bairro(),
                dto.cep(),
                dto.cidade(),
                dto.uf(),
                dto.complemento(),
                dto.numero());
    }
}
