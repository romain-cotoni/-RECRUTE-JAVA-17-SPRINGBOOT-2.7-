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
	@Query("SELECT c FROM Candidat c WHERE c.prenom=?1 AND c.nom=?2")
	public Candidat findByName(String prenom, String nom);

	@Query("SELECT DISTINCT c.prenom FROM Candidat c")
	public List<String> findAllPrenoms();

	@Query("SELECT DISTINCT c.nom FROM Candidat c")
	public List<String> findAllNoms();
	
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
	@Query("SELECT c.experiences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Experience> findExperiencesByCandidatId(int id);
	@Query("SELECT c.documents FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Document> findDocumentsByCandidatId(int id);


}
