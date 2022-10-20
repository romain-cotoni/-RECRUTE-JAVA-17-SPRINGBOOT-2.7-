package fr.projet.app.repository;

import fr.projet.app.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiviteRepository extends JpaRepository<Activite, Integer>
{

}
