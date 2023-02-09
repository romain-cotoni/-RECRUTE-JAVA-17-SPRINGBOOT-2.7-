package fr.projet.app.controller;

import fr.projet.app.model.*;
import fr.projet.app.service.CandidatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class CandidatController {
	
	private CandidatService candidatService;

	public CandidatController(CandidatService candidatService) {
		this.candidatService = candidatService;		
	}

	/**
	 * @param id
	 * @return Candidat
	 * @throws Exception
	 */
	@GetMapping("candidat/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat getCandidatById(@PathVariable("id") int id) throws Exception {
		try {
			return candidatService.findCandidatById(id);
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - getCandidatById() : " + exception.getMessage());
		}
	}

	/**
	 * Method to update a candidat
	 * @param candidat object Candidat
	 * @return List<Candidat>
	 */
	@PutMapping("/candidat/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat updateCandidat(@PathVariable("id") int id, @Valid @RequestBody Candidat candidat) throws Exception
	{
		try {
			return candidatService.updateCandidat(id, candidat);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - updateCandidat() : " + exception.getMessage());
		}
	}
	
	/**
	 * Method to create a candidat
	 * @param candidat object Candidat
	 * @return List<Candidat>
	 */
	@PostMapping("/candidat")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat createCandidat(@Valid @RequestBody Candidat candidat) throws Exception
	{
		try {
			return candidatService.createCandidat(candidat);
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - createCandidat() : " + exception.getMessage());
		}
	}

	
	
	@DeleteMapping("candidat/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public int deleteCandidat(@PathVariable("id") int id) throws Exception {
		try {
			candidatService.deleteCandidatById(id);
			return id;
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - deleteCandidat() : " + exception.getMessage());
		}
	}
	
	/**
	 * method to fetch the whole list of candidats in the database
	 * @return a list of candidats (List<Candidat>)
	 */
	@GetMapping("/candidats")
	@RolesAllowed({ "admin", "recruteur"})
	public List<Candidat> getCandidats() throws Exception {
		try {
			return candidatService.findAllCandidats();
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - getCandidats() : " + exception.getMessage());
		}
	}
	
	@PostMapping("candidat/rechercher")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat getCandidatByName(@Valid @RequestBody Candidat candidat) throws Exception {
		try {
			return candidatService.findCandidatByName(candidat);
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - getCandidatByName() : " + exception.getMessage());
		}
	}

	@PostMapping("candidats/rechercher/parametres")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidatsByParams(@Valid @RequestBody CandidatSearchQuery candidat) throws Exception{
		try {
			return candidatService.findCandidatByParamsDynamicQuery(candidat);
		}
		catch(Exception exception) {
			throw new Exception("Error CandidatController - getCandidatsByParams() : " + exception.getMessage());
		}
	}

	/**
	 * method to fetch the whole list of candidats in the database
	 * @return a list of candidats (List<Candidat>)
	 */ 
	@GetMapping("/candidatsShort")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidatsShort() throws Exception
	{
		try
		{
			return candidatService.findAllCandidatsShort();
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getCandidatShort() : " + exception.getMessage());
		}
	}

	/**
	 * method to fetch  whole list of distinct prenom for candidats in the database
	 * @return List<String>
	 */
	@GetMapping("/candidats/prenoms")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<String> getCandidatsPrenoms() throws Exception
	{
		try
		{
			return candidatService.findAllCandidatsPrenoms();
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getCandidatsPrenoms() : " + exception.getMessage());
		}
	}

	/**
	 * method to fetch  whole list of distinct nom for candidats in the database
	 * @return List<String>
	 */
	@GetMapping("/candidats/noms")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<String> getCandidatsNoms() throws Exception
	{
		try
		{
			return candidatService.findAllCandidatsNoms();
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getCandidatsNoms() : " + exception.getMessage());
		}
	}
	
	//Education
	@GetMapping("/candidat/{id}/educations")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Education> getEducations(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findEducationsByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getEducations() : " + exception.getMessage());
		}
	}
	
	@PostMapping("candidat/{id}/educations")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Education addEducation(@PathVariable("id") int id, @Valid  @RequestBody Education education) throws Exception
	{
		try
		{
			return candidatService.addEducation(id, education);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addEducation() : " + exception.getMessage());
		}
	}

	//Experience
	@GetMapping("/candidat/{id}/experiences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Experience> getExperiences(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findExperiencesByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getExperiences() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/experiences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Experience addExperience(@PathVariable("id") int id, @Valid @RequestBody Experience experience) throws Exception
	{
		try
		{
			return candidatService.addExperience(id, experience);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addExperience() : " + exception.getMessage());
		}
	}

	//Competence
	@GetMapping("/candidat/{id}/competences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Competence> getCompetences(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findCompetencesByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getCompetences() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/competences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Competence addCompetence(@PathVariable("id") int id, @Valid  @RequestBody Competence competence) throws Exception
	{
		try
		{
			return candidatService.addCompetence(id, competence);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addCompetence() : " + exception.getMessage());
		}
	}

	//Langue
	@GetMapping("/candidat/{id}/langues")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Langue> getLangues(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findLanguesByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getLangues() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/langues")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Langue addLangue(@PathVariable("id") int id, @Valid @RequestBody Langue langue) throws Exception
	{
		try
		{
			return candidatService.addLangue(id, langue);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addLangue() : " + exception.getMessage());
		}
	}

	//Projet
	@GetMapping("/candidat/{id}/projets")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Projet> getProjets(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findProjetsByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getProjets() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/projets")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Projet addProjet(@PathVariable("id") int id, @Valid @RequestBody Projet projet) throws Exception
	{
		try
		{
			return candidatService.addProjet(id, projet);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addProjet() : " + exception.getMessage());
		}
	}


	//Entretien
	@GetMapping("/candidat/{id}/entretiens")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Entretien> getEntretiens(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findEntretiensByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getEntretiens() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/entretiens")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Entretien addEntretien(@PathVariable("id") int id, @Valid @RequestBody Entretien entretien) throws Exception
	{
		try
		{
			return candidatService.addEntretien(id, entretien);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addEntretien() : " + exception.getMessage());
		}
	}


	//Pseudo
	@GetMapping("/candidat/{id}/pseudos")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Pseudo> getPseudos(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findPseudosByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getPseudos() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/pseudos")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Pseudo addPseudo(@PathVariable("id") int id, @Valid @RequestBody Pseudo pseudo) throws Exception
	{
		try
		{
			return candidatService.addPseudo(id, pseudo);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addPseudo() : " + exception.getMessage());
		}
	}


	//Document
	@GetMapping("/candidat/{id}/documents")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Document> getDocuments(@PathVariable("id") int id) throws Exception
	{
		try
		{
			return candidatService.findDocumentsByCandidatId(id);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - getDocuments() : " + exception.getMessage());
		}
	}

	@PostMapping("candidat/{id}/documents")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Document addDocument(@PathVariable("id") int id, @Valid @RequestBody Document document) throws Exception
	{
		try
		{
			return candidatService.addDocument(id, document);
		}
		catch(Exception exception)
		{
			throw new Exception("Error CandidatController - addDocument() : " + exception.getMessage());
		}
	}
}
