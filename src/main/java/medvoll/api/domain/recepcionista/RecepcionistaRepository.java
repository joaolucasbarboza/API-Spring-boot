package medvoll.api.domain.recepcionista;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecepcionistaRepository extends JpaRepository<RecepcionistaEntity, Long> {
    Page<RecepcionistaEntity> findAllByStatusTrue(Pageable pageable);
}
