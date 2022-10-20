package fr.projet.app.repository;

import fr.projet.app.model.Pseudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PseudoRepository extends JpaRepository<Pseudo, Integer>
{

}
