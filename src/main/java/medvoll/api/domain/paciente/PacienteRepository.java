package medvoll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByStatusTrue(Pageable paginacao);

    @Query(value = """
            select m.status
            from Pacientes m
            where
            m.id = :idPaciente
            """, nativeQuery = true)

    Boolean findStatusById(Long idPaciente);

    Optional<Paciente> findByIdAndStatus(Long idPaciente, boolean b);

    UserDetails findByLogin(String login);
}
