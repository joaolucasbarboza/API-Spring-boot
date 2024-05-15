package medvoll.api.domain.recepcionista;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medvoll.api.domain.endereco.Endereco;

import java.time.LocalDate;

@Table(name = "recepcionistas")
@Entity(name = "Recepcionista")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RecepcionistaEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private LocalDate dataContratacao;
    private LocalDate dataNascimento;
    private Float salario;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean status = true;

    public RecepcionistaEntity(DadosCadastroRecepcionista dados) {
        this.nome = dados.nome();
        this.telefone = dados.telefone();
        this.dataContratacao = dados.dataContratacao();
        this.dataNascimento = dados.dataNascimento();
        this.salario = dados.salario();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }
}
