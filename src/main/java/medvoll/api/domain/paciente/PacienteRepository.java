package medvoll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByStatusTrue(Pageable paginacao);

    @Query("""
            select m.ativo
            from Paciente m
            where
            m.id = :id
            """)

    Boolean findStatusById(Long idPaciente);
}
