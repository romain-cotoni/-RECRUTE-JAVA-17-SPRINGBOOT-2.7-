package fr.projet.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.app.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>
{
	@Query("SELECT t FROM Utilisateur t WHERE t.idUtilisateur=?1")
	public Utilisateur findById(int id);
	
	@Query("SELECT t FROM Utilisateur t WHERE t.username=?1")
	public Utilisateur findByUsername(String username);

	@Transactional
	@Modifying
	@Query("UPDATE Utilisateur u SET u.username=?1, u.password=?2 WHERE u.idUtilisateur=?3")
	public int updateUtilisateurById(String username, String password, int id);
}

