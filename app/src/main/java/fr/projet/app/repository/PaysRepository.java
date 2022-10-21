package fr.projet.app.repository;

import fr.projet.app.model.Pays;
import fr.projet.app.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Integer>
{
    @Query("SELECT p FROM Pays p WHERE p.nationnalite=?1")
    public Optional<Pays> findByNationnalite(String nationnalite);

    @Query("SELECT p FROM Pays p WHERE p.pays=?1")
    public Optional<Pays> findByPays(String pays);

    @Query("SELECT p FROM Pays p")
    public List<Pays> findAllPays();

}
