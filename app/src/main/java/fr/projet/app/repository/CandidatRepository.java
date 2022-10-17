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
	 *
	 * @return
	 */
	@Query("SELECT cd.id_cdt, cd.prenom_cdt, cd.nom_cdt "                    +
			"FROM       candidat            AS cd "                          +
			"INNER JOIN education           AS ed ON ed.id_cdt = cd.id_cdt " +
			"INNER JOIN diplome             AS dp ON dp.id_dpl = ed.id_dpl " +
			"INNER JOIN specialite          AS sp ON sp.id_spl = ed.id_spl " +
			"INNER JOIN experience          AS xp ON xp.id_cdt = cd.id_cdt " +
			"INNER JOIN mission             AS ms ON ms.id_msn = xp.id_msn " +
			"INNER JOIN entreprise          AS et ON et.id_etp = xp.id_etp " +
			"INNER JOIN candidat_competence AS cc ON cc.id_cdt = cd.id_cdt " +
			"INNER JOIN competence          AS cp ON cp.id_cpt = cc.id_cpt " +
			"INNER JOIN candidat_langue     AS cl ON cl.id_cdt = cd.id_cdt " +
			"INNER JOIN langue              AS ln ON ln.id_lng = cl.id_lng " +
			"INNER JOIN pseudo              AS ps ON ps.id_cdt = cd.id_cdt " +
			"INNER JOIN reseau              AS rs ON rs.id_res = ps.id_res " +
			"INNER JOIN mobilite            AS mb ON mb.id_mbl = cd.id_cdt " +
			"WHERE (cd.nom_cdt    = ?1              OR ?1 = '') "            +
			"AND cd.nom_cdt       = ?2              OR ?2 = '') "            +
			"AND (cd.teletrvl_cdt = ?3               OR ?3 is null) "        +
			"AND (cd.handi_cdt    = ?4               OR ?4 is null) "        +
			"AND (cd.dispo_cdt    = ?5               OR ?5 is null) "        +
			"AND (mb.zone_mbl     = ?6               OR ?6  = '') "          +
			"AND (?7  LIKE '%' + dp.label_dpl  + '%' OR ?7  = '') "          +
			"AND (?8  LIKE '%' + sp.label_spl  + '%' OR ?8  = '') "          +
			"AND (?9  LIKE '%' + ms.prof_msn   + '%' OR ?9  = '') "          +
			"AND (?10 LIKE '%' + et.raison_etp + '%' OR ?10 = '') "          +
			"AND (?11 LIKE '%' + cp.nom_cpt    + '%' OR ?11 = '') "          +
			"AND (?12 LIKE '%' + ln.nom_lng    + '%' OR ?12 = '') "          +
			"AND (?13 LIKE '%' + ps.pseudo_psd + '%' OR ?13 = '') "          +
			"AND (?14 LIKE '%' + rs.reseau_res + '%' OR ?14 = '') "          +
			"GROUP BY cd.id_cdt, cd.prenom_cdt, cd.nom_cdt "                 +
			"ORDER BY cd.nom_cdt, cd.prenom_cdt")
	//prenom, nom, teletrvl, handi, dispo, mobilite, diplome, specialite, mission, entreprise, competence, langue, pseudo, reseau
	public List<Candidat> findByParams(String prenom, String nom, Boolean teletrvl, Boolean handi, Boolean dispo, Integer mobilite, String diplome, String specialite, String mission, String  raison, String competence, String  langue, String pseudo, String reseau);
	
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
