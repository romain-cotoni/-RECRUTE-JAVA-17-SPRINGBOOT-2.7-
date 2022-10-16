package fr.projet.app.controller;

import fr.projet.app.model.Education;
import fr.projet.app.service.EducationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true", maxAge=3600)
@RestController
public class EducationController
{
	private EducationService educationService;

	public EducationController(EducationService educationService)
	{
		this.educationService = educationService;
	}
	
	@GetMapping("educations")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public List<Education> getAllEducations()
	{
		return educationService.findAllEducations();
	}
	
	@GetMapping("education/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Education getEducation(@PathVariable("id") int id)
	{
		return educationService.getEducation(id);
	}
	
	@PutMapping("education/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public Education updateEducation(@PathVariable("id") int id, @RequestBody Education education) throws Exception
	{
		return educationService.updateEducation(id, education);
	}
	
	@DeleteMapping("education/{id}")
	@RolesAllowed({ "admin", "recruteur", "candidat" })
	public void deleteEducation(@PathVariable("id") int id)
	{
		educationService.deleteEducation(id);
	}
}
