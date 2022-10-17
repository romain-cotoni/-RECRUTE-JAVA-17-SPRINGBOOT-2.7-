package fr.projet.app.controller;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Document;
import fr.projet.app.model.Education;
import fr.projet.app.model.Experience;
import fr.projet.app.service.CandidatService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
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
	 * method to fetch the whole list of candidats in the database
	 * @param an object Candidat (Candidat candidat)
	 * @return a list of candidats (List<Candidat>)
	 */
	@PostMapping("/candidat")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Candidat createCandidat(@RequestBody Candidat candidat)
	{
		return candidatService.createCandidat(candidat);
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
	public Candidat getCandidatByName(@RequestBody Candidat candidat)
	{
		return candidatService.findCandidatByName(candidat);
	}

	@PostMapping("candidats/rechercher/parametres")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Candidat> getCandidatsByParams(@RequestBody Candidat candidat)
	{
		List<Candidat> candidats = candidatService.findCandidatsByParams(candidat);
		for(int i=0; i<candidats.size();i++)
		{
			System.out.println(candidats.get(i).getPrenom()+ " - "+candidats.get(i).getNom());
		}
		return candidats;
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
	public Education addEducation(@PathVariable("id") int id, @RequestBody Education education) throws Exception
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
	public Experience addExperience(@PathVariable("id") int id, @RequestBody Experience experience) throws Exception
	{
		return candidatService.addExperience(id, experience);
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
	public Document addDocument(@PathVariable("id") int id, @RequestBody Document document)
	{
		return candidatService.addDocument(id, document);
	}
}
