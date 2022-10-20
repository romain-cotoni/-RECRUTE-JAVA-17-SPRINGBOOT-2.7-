package fr.projet.app.repository;

import fr.projet.app.model.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LangueRepository extends JpaRepository<Langue, Integer>
{

}
