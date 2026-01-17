package fr.insa.ms.Log.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ms.Log.models.LogEntity;

public interface LogRepository extends JpaRepository<LogEntity, Integer> {
	Iterable<LogEntity> findAllByCiterneIDOrderByDateDesc(int citerneID);
}
