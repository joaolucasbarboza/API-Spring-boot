package medvoll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import medvoll.api.domain.medico.Medico;
import medvoll.api.domain.paciente.Paciente;
import medvoll.api.domain.remedio.RemedioEntity;
import medvoll.api.domain.usuario.Usuario;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "consultas")
@Entity(name = "consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "REMEDIOS_TOMADOS",
            joinColumns = @JoinColumn(name = "CONSULTAS_ID"),
            inverseJoinColumns = @JoinColumn(name = "REMEDIOS_ID"))
    private List<RemedioEntity> remedios = new ArrayList<>();

    @Column(name = "valor")
    private Float valor;

    public void atualizar(DadosAtualizarConsulta dados, List<RemedioEntity> novosRemedios) {

        for (RemedioEntity remedio : novosRemedios) {
            if (!this.remedios.contains(remedio)) {
                this.remedios.add(remedio);

                remedio.reduzirEstoque();
            } else {
                throw new RuntimeException("Não pode tomar o mesmo rémdio mais que uma vez na consulta.");
            }
        }

        if (dados.formaPagamento() != null) {
            this.formaPagamento = dados.formaPagamento();
        }

        if (dados.valor() != null) {
            this.valor = dados.valor();
        }
    }

    public ConsultaEntity(Long id, Long id1, LocalDateTime data, Object o) {
    }

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}
