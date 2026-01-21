package fr.insa.ms.NiveauLiquide.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ms.NiveauLiquide.models.NiveauLiquideEntity;

public interface NiveauLiquideRepository extends JpaRepository<NiveauLiquideEntity, LocalDateTime> {
    Optional<NiveauLiquideEntity> findTopByCiterneIDOrderByDateDesc(int citerneID);
}
