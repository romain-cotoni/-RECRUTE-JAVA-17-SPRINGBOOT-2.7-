package fr.projet.app.repository;

import fr.projet.app.model.Pseudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PseudoRepository extends JpaRepository<Pseudo, Integer>
{
    @Query("SELECT p FROM Pseudo p "                                   +
           "INNER JOIN Reseau AS r ON r.idReseau = p.reseau.idReseau " +
           "WHERE p.pseudo=?1 AND p.reseau.reseau=?2")
    public Pseudo findByPseudoAndReseau(String pseudo, String reseau);

    @Query("SELECT p FROM Pseudo p "                                   +
            "INNER JOIN Reseau AS r ON r.idReseau = p.reseau.idReseau " +
            "WHERE r.reseau=?1")
    public Pseudo findByReseau(String reseau);
}
