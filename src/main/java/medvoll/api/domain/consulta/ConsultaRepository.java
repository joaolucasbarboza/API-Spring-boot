package medvoll.api.domain.consulta;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    boolean existsByUsuarioIdAndDataBetween(Long idUsuario, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime data);

    List<ConsultaEntity> findByDataBetween(LocalDateTime inicioDaSemana, LocalDateTime fimDaSemana, Sort data);
}
