package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Specialite;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite,Integer> 
{
	
}
