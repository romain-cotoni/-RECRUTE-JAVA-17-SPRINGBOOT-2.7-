package fr.projet.app.repository;

import fr.projet.app.model.Reseau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReseauRepository extends JpaRepository<Reseau, Integer>
{

}
