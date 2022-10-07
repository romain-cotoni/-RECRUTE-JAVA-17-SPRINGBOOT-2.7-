package fr.projet.app.controller;

import java.util.List;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.projet.app.model.Education;
import fr.projet.app.service.EducationService;

@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true", maxAge=3600)
@RestController
public class EducationController
{
	@Autowired
	EducationService educationService;
	
	
	@GetMapping("educations")
	@RolesAllowed({"vador", "yoda", "jedi", "padawan"})
	public List<Education> getAllEducations()
	{
		return educationService.findAllEducations();
	}
	
	@GetMapping("education/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Education getEducation(@PathVariable("id") int id)
	{
		return educationService.getEducation(id);
	}
	
	@PutMapping("education/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Education updateEducation(@PathVariable("id") int id, @RequestBody Education education) throws Exception
	{
		return educationService.updateEducation(id, education);
	}
	
	@DeleteMapping("education/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public void deleteEducation(@PathVariable("id") int id)
	{
		educationService.deleteEducation(id);
	}
}
