package dev.fernando.med.api.models.medico;

import dev.fernando.med.api.models.endereco.DadosEndereco;
import dev.fernando.med.api.models.endereco.Endereco;
import dev.fernando.med.api.models.medico.dtos.DadosAtualizacaoMedicoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "update medicos set ativo=0 where id = ?")
@SQLRestriction("ativo=1")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public void atualizarInformacoes(DadosAtualizacaoMedicoDTO dto) {
        if (dto.nome() != null && !dto.nome().isEmpty()) {
            this.nome = dto.nome();
        }
        if (dto.telefone() != null && !dto.telefone().isEmpty()) {
            this.telefone = dto.telefone();
        }
        if (dto.endereco() != null) {
            this.endereco.atualizarInformacoes(dto.endereco());
        }
    }
}
