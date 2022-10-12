package fr.projet.app.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.projet.app.model.Experience;
import fr.projet.app.service.ExperienceService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class ExperienceController 
{
	@Autowired
	ExperienceService experienceService;

	@GetMapping("experiences")
	@RolesAllowed({"vador", "yoda", "jedi", "padawan"})
	public List<Experience> getAllExperiences()
	{
		return experienceService.findAllExperiences();
	}

	@GetMapping("experience/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Experience getExperience(@PathVariable("id") int id)
	{
		return experienceService.getExperience(id);
	}

	@PutMapping("experience/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Experience updateExperience(@PathVariable("id") int id, @RequestBody Experience experience) throws Exception
	{
		return experienceService.updateExperience(id, experience);
	}

	@DeleteMapping("experience/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public void deleteExperience(@PathVariable("id") int id)
	{
		experienceService.deleteExperience(id);
	}

	/*@PutMapping("experience/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Experience updateExperience(@PathVariable("id") int id, @RequestBody Experience experience)
	{
		return experienceService.updateExperience(id, experience);
	}
	
	@DeleteMapping("experience/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public void deleteExperience(@PathVariable("id") int id)
	{
		experienceService.deleteExperience(id);
	}*/
}
