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
public class CandidatController
{
	private CandidatService candidatService;

	public CandidatController(CandidatService candidatService)
	{
		this.candidatService = candidatService;
	}


	/**
	 * method to fetch the whole list of candidats in the database
	 * @return a list of candidats (List<Candidat>)
	 */
	@GetMapping("/candidats")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidats()
	{
		return candidatService.findAllCandidats();
	}
	
	/**
	 * method to fetch the whole list of candidats in the database
	 * @return a list of candidats (List<Candidat>)
	 */
	@GetMapping("/candidatsShort")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidatsShort()
	{
		return candidatService.findAllCandidatsShort();
	}

	/**
	 * method to fetch  whole list of distinct prenom for candidats in the database
	 * @return List<String>
	 */
	@GetMapping("/candidats/prenoms")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<String> getCandidatsPrenoms()
	{
		return candidatService.findAllCandidatsPrenoms();
	}

	/**
	 * method to fetch  whole list of distinct nom for candidats in the database
	 * @return List<String>
	 */
	@GetMapping("/candidats/noms")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<String> getCandidatsNoms()
	{
		return candidatService.findAllCandidatsNoms();
	}

	/**
	 * Method to create a candidat
	 * @param candidat object Candidat
	 * @return List<Candidat>
	 */
	@PostMapping("/candidat")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat createCandidat(@Valid @RequestBody Candidat candidat)
	{
		return candidatService.createCandidat(candidat);
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
		return candidatService.updateCandidat(id, candidat);
	}
	
	@GetMapping("candidat/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat getCandidatById(@PathVariable("id") int id)
	{
		return candidatService.findCandidatById(id);
	}
	
	@DeleteMapping("candidat/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public int deleteCandidat(@PathVariable("id") int id)
	{
		candidatService.deleteCandidatById(id);
		return id;
	}
	
	@PostMapping("candidat/rechercher")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat getCandidatByName(@Valid @RequestBody Candidat candidat)
	{
		return candidatService.findCandidatByName(candidat);
	}

	@PostMapping("candidats/rechercher/parametres")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidatsByParams(@Valid @RequestBody CandidatSearchQuery candidat)
	{
		//return candidatService.findCandidatsByParams(candidat);
		return candidatService.findCandidatByParamsDynamicQuery(candidat);
	}


	//Education
	@GetMapping("/candidat/{id}/educations")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Education> getEducations(@PathVariable("id") int id)
	{
		return candidatService.findEducationsByCandidatId(id);
	}
	
	@PostMapping("candidat/{id}/educations")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Education addEducation(@PathVariable("id") int id, @Valid  @RequestBody Education education) throws Exception
	{
		return candidatService.addEducation(id, education);
	}

	//Experience
	@GetMapping("/candidat/{id}/experiences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Experience> getExperiences(@PathVariable("id") int id)
	{
		return candidatService.findExperiencesByCandidatId(id);
	}

	@PostMapping("candidat/{id}/experiences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Experience addExperience(@PathVariable("id") int id, @Valid @RequestBody Experience experience) throws Exception
	{
		return candidatService.addExperience(id, experience);
	}

	//Competence
	@GetMapping("/candidat/{id}/competences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Competence> getCompetences(@PathVariable("id") int id)
	{
		return candidatService.findCompetencesByCandidatId(id);
	}

	@PostMapping("candidat/{id}/competences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Competence addCompetence(@PathVariable("id") int id, @Valid  @RequestBody Competence competence) throws Exception
	{
		return candidatService.addCompetence(id, competence);
	}

	//Langue
	@GetMapping("/candidat/{id}/langues")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Langue> getLangues(@PathVariable("id") int id)
	{
		return candidatService.findLanguesByCandidatId(id);
	}

	@PostMapping("candidat/{id}/langues")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Langue addLangue(@PathVariable("id") int id, @Valid @RequestBody Langue langue) throws Exception
	{
		return candidatService.addLangue(id, langue);
	}

	//Projet
	@GetMapping("/candidat/{id}/projets")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Projet> getProjets(@PathVariable("id") int id)
	{
		return candidatService.findProjetsByCandidatId(id);
	}

	@PostMapping("candidat/{id}/projets")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Projet addProjet(@PathVariable("id") int id, @Valid @RequestBody Projet projet) throws Exception
	{
		return candidatService.addProjet(id, projet);
	}


	//Entretien
	@GetMapping("/candidat/{id}/entretiens")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Entretien> getEntretiens(@PathVariable("id") int id)
	{
		return candidatService.findEntretiensByCandidatId(id);
	}

	@PostMapping("candidat/{id}/entretiens")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Entretien addEntretien(@PathVariable("id") int id, @Valid @RequestBody Entretien entretien) throws Exception
	{
		return candidatService.addEntretien(id, entretien);
	}


	//Pseudo
	@GetMapping("/candidat/{id}/pseudos")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Pseudo> getPseudos(@PathVariable("id") int id)
	{
		return candidatService.findPseudosByCandidatId(id);
	}

	@PostMapping("candidat/{id}/pseudos")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Pseudo addPseudo(@PathVariable("id") int id, @Valid @RequestBody Pseudo pseudo) throws Exception
	{
		return candidatService.addPseudo(id, pseudo);
	}


	//Document
	@GetMapping("/candidat/{id}/documents")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Set<Document> getDocuments(@PathVariable("id") int id)
	{
		return candidatService.findDocumentsByCandidatId(id);
	}

	@PostMapping("candidat/{id}/documents")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Document addDocument(@PathVariable("id") int id, @Valid @RequestBody Document document)
	{
		return candidatService.addDocument(id, document);
	}
}
