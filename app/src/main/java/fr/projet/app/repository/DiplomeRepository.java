package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Diplome;

@Repository
public interface DiplomeRepository extends JpaRepository<Diplome,Integer>
{
	
}
