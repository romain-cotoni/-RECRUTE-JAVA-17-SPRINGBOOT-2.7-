package fr.projet.app.repository;

import fr.projet.app.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Integer>
{

}
