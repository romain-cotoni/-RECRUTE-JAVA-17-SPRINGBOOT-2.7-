package fr.projet.app.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Document;
import fr.projet.app.model.Education;
import fr.projet.app.model.Experience;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Integer>
{
	//@Query("SELECT t FROM Candidat t WHERE t.idCandidat=?1")
	//public Candidat findById(int id);

	@Query("SELECT t FROM Candidat t WHERE t.prenom=?1 AND t.nom=?2")
	public Candidat findByName(String prenom, String nom);

	public List<Candidat> findByNom(String nom);
	
	/**
	 * delete a candidat found by it's id given in parameter
	 * @param id
	 * @return void
	*/
	public void deleteById(int id);

	//@Query("SELECT DISTINCT b FROM Brand b JOIN b.categories c WHERE c.name LIKE %?1%")
	@Query("SELECT c.educations FROM Candidat c WHERE c.idCandidat=?1")
			//inner join c.education e inner join e.specialite s inner join e.diplome d"
	public Set<Education> findEducationsByCandidatId(int id);
	
	@Query("SELECT c.documents FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Document> findDocumentsByCandidatId(int id);

	@Query("SELECT c.experiences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Experience> findExperiencesByCandidatId(int id);
	
	
}
