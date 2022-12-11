package fr.projet.app.repository;

import fr.projet.app.model.Mobilite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MobiliteRepository extends JpaRepository<Mobilite, Integer>
{
    @Query("SELECT m FROM Mobilite m WHERE m.zone=?1")
    public Optional<Mobilite> findByZone(Integer zone);
}
