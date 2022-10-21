package fr.projet.app.repository;

import fr.projet.app.model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer>
{
    @Query("SELECT v FROM Ville v WHERE v.ville=?1 AND v.postal=?2")
    public Optional<Ville> findByVilleAndPostal(String ville, String postal);
}
