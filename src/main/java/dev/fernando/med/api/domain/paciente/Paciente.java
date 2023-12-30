package dev.fernando.med.api.domain.paciente;

import dev.fernando.med.api.domain.endereco.Endereco;
import dev.fernando.med.api.domain.paciente.dtos.DadosAtualizacaoPacienteDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@SQLDelete(sql = "update pacientes set ativo=0 where id = ?")
@SQLRestriction("ativo=1")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;
    private Boolean ativo;

    public void atualizarInformacoes(DadosAtualizacaoPacienteDTO dto) {
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
