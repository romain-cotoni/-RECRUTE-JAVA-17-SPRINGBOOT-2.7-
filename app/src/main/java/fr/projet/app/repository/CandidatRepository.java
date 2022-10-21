package fr.projet.app.repository;

import java.util.List;
import java.util.Set;

import fr.projet.app.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
	 * Method to fetch a list of candidats in the database by a list of optional parameters
	 * @param CandidatSearchQuery candidat
	 * @return List<Candidat>
	 */
	@Query( "SELECT DISTINCT cd FROM Candidat AS cd "                                                          +
			"LEFT JOIN Education              AS ed ON ed.candidat.idCandidat = cd.idCandidat "                +
			"LEFT JOIN Diplome                AS dp ON dp.idDiplome = ed.diplome.idDiplome "                   +
			"LEFT JOIN Specialite             AS sp ON sp.idSpecialite = ed.specialite.idSpecialite "          +

			"LEFT JOIN Experience             AS xp ON xp.IdExperience = cd.idCandidat "                       +
			"LEFT JOIN Mission                AS ms ON ms.idMission    = xp.mission.idMission "                +
			"LEFT JOIN Entreprise             AS et ON et.idEntreprise = xp.entreprise.idEntreprise "          +

			"LEFT JOIN cd.competences         AS cp "                                                          +

			"LEFT JOIN cd.langues             AS ln "                                                          +

			"LEFT JOIN Pseudo                 AS ps ON ps.candidat.idCandidat = cd.idCandidat "                +

			"LEFT JOIN Ville                  AS vl ON vl.IdVille = cd.ville.IdVille "                         +

			"LEFT JOIN Mobilite               AS mb ON mb.idMobilite = cd.mobilite.idMobilite "                +

			"WHERE (:prenom    LIKE CONCAT('%',cd.prenom,'%')         OR :prenom       = '') "                 +
			"AND (:nom         LIKE CONCAT('%',cd.nom,'%')            OR :nom          = '') "                 +
			"AND (:telephone   LIKE CONCAT('%',cd.mob,'%')"                                                    +
			" OR  :telephone   LIKE CONCAT('%',cd.fixe,'%')           OR :telephone    = '') "                 +
			"AND (:email       LIKE CONCAT('%',cd.email,'%')          OR :email        = '') "                 +
			"AND (cd.teletravail = :teletrvl                          OR :teletrvl IS NULL) "                  +
			"AND (cd.handicape   = :handicap                          OR :handicap IS NULL) "                  +
			"AND (cd.disponible  = :dispo                             OR :dispo    IS NULL) "                  +

			"AND (:diplomes    LIKE CONCAT('%',dp.label,'%')          OR :diplomes     = '') " 		           +
			"AND (:specialites LIKE CONCAT('%',sp.label,'%')          OR :specialites  = '') " 		           +

			"AND (:missions    LIKE CONCAT('%',ms.profession,'%')     OR :missions     = '') " 		           +
			"AND (:entreprises LIKE CONCAT('%',et.raisonSociale,'%')  OR :entreprises  = '') " 		           +

			"AND (:competences LIKE CONCAT('%',cp.nom,'%')            OR :competences  = '') " 		           +

			"AND (:langues     LIKE CONCAT('%',ln.nom,'%')            OR :langues      = '') " 		           +

			"AND (:pseudos     LIKE CONCAT('%',ps.pseudo,'%')         OR :pseudos      = '') " 		           +

			"AND (:ville       LIKE CONCAT('%',vl.ville,'%')          OR :ville        = '') "                 +

			"AND (mb.zone = :mobilite                                 OR :mobilite = 0 OR :mobilite IS NULL) " +

			"ORDER BY cd.nom, cd.prenom"
		  )
	public List<Candidat> findByParams(String prenom, String nom, String telephone, String email, Boolean teletrvl, Boolean handicap, Boolean dispo, String diplomes, String specialites, String missions, String entreprises, String competences, String langues, String pseudos, String ville, Integer mobilite);

	/**
	 * delete a candidat found by it's id given in parameter
	 * @param int id
	 * @return void
	*/
	public void deleteById(int id);

	@Query("SELECT c.educations FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Education> findEducationsByCandidatId(int id);

	@Query("SELECT c.experiences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Experience> findExperiencesByCandidatId(int id);

	@Query("SELECT c.competences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Competence> findCompetencesByCandidatId(int id);

	@Query("SELECT c.competences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Langue> findLanguesByCandidatId(int id);

	@Query("SELECT c.projets FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Projet> findProjetsByCandidatId(int id);

	@Query("SELECT c.entretiens FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Entretien> findEntretiensByCandidatId(int id);

	@Query("SELECT c.pseudos FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Pseudo> findPseudosByCandidatId(int id);

	@Query("SELECT c.documents FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Document> findDocumentsByCandidatId(int id);
}
