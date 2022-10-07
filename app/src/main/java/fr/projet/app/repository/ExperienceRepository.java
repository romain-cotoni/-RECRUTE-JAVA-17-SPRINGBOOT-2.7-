package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Experience;


@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer>
{
	public void deleteById(int id);
}
