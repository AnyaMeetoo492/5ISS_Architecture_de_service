package fr.insa.ms.Humidite.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ms.Humidite.models.HumiditeEntity;

public interface HumiditeRepository extends JpaRepository<HumiditeEntity, LocalDateTime> {
	Optional<HumiditeEntity> findTopByCiterneIDOrderByDateDesc(int citerneID);
}
