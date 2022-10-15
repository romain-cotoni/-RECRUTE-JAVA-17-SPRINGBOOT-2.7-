package fr.projet.app.controller;

import java.util.List;
import java.util.Set;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Document;
import fr.projet.app.model.Education;
import fr.projet.app.model.Experience;
import fr.projet.app.service.CandidatService;


@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class CandidatController
{
	private CandidatService candidatService;

	@Autowired
	public CandidatController(CandidatService candidatService)
	{
		this.candidatService = candidatService;
	}


	
	@GetMapping("/candidats")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public List<Candidat> getCandidats()
	{
		return candidatService.findAllCandidats();
	}
	
	@PostMapping("/candidats")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Candidat createCandidat(@RequestBody Candidat candidat)
	{
		return candidatService.createCandidat(candidat);
	}
	
	@GetMapping("candidat/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Candidat getCandidatById(@PathVariable("id") int id)
	{
		return candidatService.findCandidatById(id);
	}
	
	@DeleteMapping("candidat/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public int deleteCandidat(@PathVariable("id") int id)
	{
		candidatService.deleteCandidatById(id);
		return id;
	}
	
	@PostMapping("candidat/rechercher")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Candidat getCandidatById(@RequestBody Candidat candidat)
	{
		return candidatService.findCandidatByName(candidat);
	}
	
	
	//Education
	@GetMapping("/candidat/{id}/educations")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Set<Education> getEducations(@PathVariable("id") int id)
	{
		return candidatService.findEducationsByCandidatId(id);
	}
	
	@PostMapping("candidat/{id}/educations")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Education addEducation(@PathVariable("id") int id, @RequestBody Education education) throws Exception
	{
		return candidatService.addEducation(id, education);
	}
	
	
	//Document
	@GetMapping("/candidat/{id}/documents")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Set<Document> getDocuments(@PathVariable("id") int id)
	{
		return candidatService.findDocumentsByCandidatId(id);
	}
	
	@PostMapping("candidat/{id}/documents")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Document createDocument(@PathVariable("id") int id, @RequestBody Document document)
	{
		return candidatService.createDocument(id, document);
	}
	
	
	//Experience
	@GetMapping("/candidat/{id}/experiences")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Set<Experience> getExperiences(@PathVariable("id") int id)
	{
		return candidatService.findExperiencesByCandidatId(id);
	}

	@PostMapping("candidat/{id}/experiences")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Experience addExperience(@PathVariable("id") int id, @RequestBody Experience experience) throws Exception
	{
		return candidatService.addExperience(id, experience);
	}
	/*@GetMapping("/candidat/{id}/experiences")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Set<Experience> getExperiences(@PathVariable("id") int id)
	{
		return candidatService.findExperiencesByCandidatId(id);
	}

	@PostMapping("/candidat/{id}/experiences")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Experience createExperience(@PathVariable("id") int id, @RequestBody Experience experience)
	{
		return candidatService.createExperience(id, experience);
	}*/
	
}
