package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer>
{	
	
}
