package fr.insa.ms.Temperature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.insa.ms.Temperature.models.TemperatureEntity;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity, LocalDateTime> {
    Optional<TemperatureEntity> findTopByOrderByDateDesc();
}
