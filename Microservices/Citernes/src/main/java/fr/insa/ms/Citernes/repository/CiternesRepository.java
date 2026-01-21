package fr.insa.ms.Citernes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ms.Citernes.models.CiterneEntity;


public interface CiternesRepository extends JpaRepository<CiterneEntity, Integer> {
}
