package fr.projet.app.controller;

import fr.projet.app.model.Experience;
import fr.projet.app.service.ExperienceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class ExperienceController 
{
	ExperienceService experienceService;

	public ExperienceController(ExperienceService experienceService)
	{
		this.experienceService = experienceService;
	}


	@GetMapping("experiences")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Experience> getAllExperiences()
	{
		return experienceService.findAllExperiences();
	}

	@GetMapping("experience/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Experience getExperience(@PathVariable("id") int id)
	{
		return experienceService.getExperience(id);
	}

	@PutMapping("experience/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Experience updateExperience(@PathVariable("id") int id, @RequestBody Experience experience) throws Exception
	{
		return experienceService.updateExperience(id, experience);
	}

	@DeleteMapping("experience/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public void deleteExperience(@PathVariable("id") int id)
	{
		experienceService.deleteExperience(id);
	}

	/*@PutMapping("experience/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Experience updateExperience(@PathVariable("id") int id, @RequestBody Experience experience)
	{
		return experienceService.updateExperience(id, experience);
	}
	
	@DeleteMapping("experience/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public void deleteExperience(@PathVariable("id") int id)
	{
		experienceService.deleteExperience(id);
	}*/
}
