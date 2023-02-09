package fr.projet.app.repository;

import java.util.List;
import java.util.Set;

import fr.projet.app.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat,Integer>, 
                                            JpaSpecificationExecutor<Candidat> {
	
    public List<Candidat> findByEmailAndNom(String email, String nom);

	@Query("SELECT c FROM Candidat c WHERE c.prenom=?1 AND c.nom=?2")
	public Candidat findByName(String prenom, String nom);

	@Query("SELECT c.educations FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Education> findEducationsByCandidatId(int id);

	@Query("SELECT c.experiences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Experience> findExperiencesByCandidatId(int id);

	@Query("SELECT c.competences FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Competence> findCompetencesByCandidatId(int id);

	@Query("SELECT c.langues FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Langue> findLanguesByCandidatId(int id);

	@Query("SELECT c.projets FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Projet> findProjetsByCandidatId(int id);

	@Query("SELECT c.entretiens FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Entretien> findEntretiensByCandidatId(int id);

	@Query("SELECT c.pseudos FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Pseudo> findPseudosByCandidatId(int id);

	@Query("SELECT c.documents FROM Candidat c WHERE c.idCandidat=?1")
	public Set<Document> findDocumentsByCandidatId(int id);
	
	/**
	 * delete a candidat found by it's id given in parameter
	 * @param id int
	 * @return void
	*/
	public void deleteById(int id);

	@Query("SELECT new Candidat(c.idCandidat, c.nom, c.prenom) FROM Candidat c")
    List<Candidat> findAllShort();
	
	@Query("SELECT DISTINCT c.prenom FROM Candidat c")
	public List<String> findAllPrenoms();

	@Query("SELECT DISTINCT c.nom FROM Candidat c")
	public List<String> findAllNoms();

	/**
	 * method to search one or many candidat by multiple and optional parameters
	 * @param string
	 * @return List<Candidat>
	 */
	@Query("SELECT DISTINCT cd FROM Candidat AS cd " +
		   "LEFT JOIN Education      AS ed ON ed.candidat.idCandidat = cd.idCandidat " +	               
		   "LEFT JOIN Diplome        AS dp ON dp.idDiplome = ed.diplome.idDiplome " +
		   "LEFT JOIN Specialite     AS sp ON sp.idSpecialite = ed.specialite.idSpecialite " +
		   "LEFT JOIN Experience     AS xp ON xp.IdExperience = cd.idCandidat " +
		   "LEFT JOIN Mission        AS ms ON ms.idMission    = xp.mission.idMission " +
		   "LEFT JOIN Entreprise     AS et ON et.idEntreprise = xp.entreprise.idEntreprise " +
		   "LEFT JOIN cd.competences AS cp " +
		   "LEFT JOIN cd.langues     AS ln " +
		   "LEFT JOIN Pseudo         AS ps ON ps.candidat.idCandidat = cd.idCandidat " +
		   "LEFT JOIN Ville          AS vl ON vl.IdVille = cd.ville.IdVille " +
		   "LEFT JOIN Mobilite       AS mb ON mb.idMobilite = cd.mobilite.idMobilite " +
		   "WHERE (:prenom LIKE CONCAT('%',cd.prenom,'%') OR :prenom = '') " +
		   "AND (:nom LIKE CONCAT('%',cd.nom,'%') OR :nom = '') " +
		   "AND (:telephone LIKE CONCAT('%',cd.mob,'%') " +
		   "OR  :telephone  LIKE CONCAT('%',cd.fixe,'%')  OR :telephone = '') " +
		   "AND (:email     LIKE CONCAT('%',cd.email,'%') OR :email = '') " +
		   "AND (cd.teletravail = :teletravail OR :teletravail IS NULL) " +
		   "AND (cd.handicape   = :handicap OR :handicap IS NULL) " +
		   "AND (cd.disponible  = :disponible OR :disponible IS NULL) " +
		   "AND (:diplomes    LIKE CONCAT('%',dp.label,'%') OR :diplomes = '') " +
		   "AND (:specialites LIKE CONCAT('%',sp.label,'%') OR :specialites = '') " +
		   "AND (:missions    LIKE CONCAT('%',ms.profession,'%') OR :missions = '') " +
		   "AND (:entreprises LIKE CONCAT('%',et.raisonSociale,'%') OR :entreprises = '') " +
		   "AND (:competences LIKE CONCAT('%',cp.nom,'%') OR :competences = '') " +
		   "AND (:langues     LIKE CONCAT('%',ln.nom,'%') OR :langues = '') " +
		   "AND (:pseudos     LIKE CONCAT('%',ps.pseudo,'%') OR :pseudos = '') " +
		   "AND (:ville       LIKE CONCAT('%',vl.ville,'%') OR :ville = '') " +
		   "AND (mb.zone = :mobilite OR :mobilite = 0 OR :mobilite IS NULL) " +
		   "ORDER BY cd.nom, cd.prenom"
		   )
	    public List<Candidat> findByParams(@Param("prenom") String prenom, 
		                                   @Param("nom") String nom,
										   @Param("telephone") String telephone,
										   @Param("email") String email,
										   @Param("teletravail") Boolean teletravail,
										   @Param("handicap") Boolean handicap, 
										   @Param("disponible") Boolean disponible,
										   @Param("diplomes") String diplomes, 
										   @Param("specialites") String specialites, 
										   @Param("missions") String missions, 
										   @Param("entreprises") String entreprises, 
										   @Param("competences") String competences,
										   @Param("langues") String langues,
										   @Param("pseudos") String pseudos,
										   @Param("ville") String ville,
										   @Param("mobilite") Integer mobilite);									   
	
	/*@Query(value =
			  "SELECT cd.id_cdt, cd.prenom_cdt, cd.nom_cdt "
			+ "FROM      candidat            AS cd "
			+ "LEFT JOIN education           AS ed ON ed.id_cdt = cd.id_cdt "
			+ "LEFT JOIN diplome             AS dp ON dp.id_dpl = ed.id_dpl "
			+ "LEFT JOIN specialite          AS sp ON sp.id_spl = ed.id_spl "
			+ "LEFT JOIN experience          AS xp ON xp.id_cdt = cd.id_cdt "
			+ "LEFT JOIN mission             AS ms ON ms.id_msn = xp.id_msn "
			+ "LEFT JOIN entreprise          AS et ON et.id_etp = xp.id_etp "
			+ "LEFT JOIN candidat_competence AS cc ON cc.id_cdt = cd.id_cdt "
			+ "LEFT JOIN competence          AS cp ON cp.id_cpt = cc.id_cpt "
			+ "LEFT JOIN candidat_langue     AS cl ON cl.id_cdt = cd.id_cdt "
			+ "LEFT JOIN langue              AS ln ON ln.id_lng = cl.id_lng "
			+ "LEFT JOIN pseudo              AS ps ON ps.id_cdt = cd.id_cdt "
			+ "LEFT JOIN reseau              AS rs ON rs.id_res = ps.id_res "
			+ "LEFT JOIN ville               AS vl ON vl.id_vil = cd.id_vil "
			+ "LEFT JOIN mobilite            AS mb ON mb.id_mbl = cd.id_cdt "
			+ "WHERE (cd.prenom_cdt   = :prenom      OR :prenom = '') "
			+ "  AND (cd.nom_cdt      = :nom         OR :nom = '') "
			+ "  AND (cd.mob_cdt      = :telephone   OR :telephone = '') "
			+ "  AND (cd.email_cdt    = :email       OR :email = '') "
			+ "  AND (cd.teletrvl_cdt = :teletravail OR :teletravail   is null) "
			+ "  AND (cd.handi_cdt    = :handicap    OR :handicap   is null) "
			+ "  AND (cd.dispo_cdt    = :disponible  OR :disponible is null) "
			+ "  AND (mb.zone_mbl     = :mobilite    OR :mobilite   = '') "
			+ "  AND (:diplomes    LIKE '%' + dp.label_dpl  + '%' OR :diplomes    = '') "
			+ "  AND (:specialites LIKE '%' + sp.label_spl  + '%' OR :specialites = '') "
			+ "  AND (:missions    LIKE '%' + ms.prof_msn   + '%' OR :missions    = '') "
			+ "  AND (:entreprises LIKE '%' + et.raison_etp + '%' OR :entreprises = '') "
			+ "  AND (:competences LIKE '%' + cp.nom_cpt    + '%' OR :competences = '') "
			+ "  AND (:langues     LIKE '%' + ln.nom_lng    + '%' OR :langues     = '') "
			+ "  AND (:pseudos     LIKE '%' + ps.pseudo_psd + '%' OR :pseudos     = '') "
			+ "  AND (:ville       LIKE '%' + vl.ville_vil  + '%' OR :ville       = '') "
			+ "  AND (:mobilite = :mobilite    LIKE '%' + mb.zone_mbl   + '%' OR :mobilite    = '') "
			+ "GROUP BY cd.id_cdt, cd.prenom_cdt, cd.nom_cdt "
			+ "ORDER BY cd.nom_cdt, cd.prenom_cdt"
			, nativeQuery=true)
	public List<Candidat> findByParamsNativeQuery
	(
	   @Param("prenom") String prenom, 
       @Param("nom") String nom,
	   @Param("telephone") String telephone,
	   @Param("email") String email,
	   @Param("teletravail") Boolean teletravail,
	   @Param("handicap") Boolean handicap, 
	   @Param("disponible") Boolean disponible,
	   @Param("diplomes") String diplomes, 
	   @Param("specialites") String specialites, 
	   @Param("missions") String missions, 
	   @Param("entreprises") String entreprises, 
	   @Param("competences") String competences,
	   @Param("langues") String langues,
	   @Param("pseudos") String pseudos,
	   @Param("ville") String ville,
	   @Param("mobilite") Integer mobilite
	);*/
	
	
}
