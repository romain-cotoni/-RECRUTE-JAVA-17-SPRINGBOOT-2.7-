package fr.projet.app.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.projet.app.model.Experience;
import fr.projet.app.service.ExperienceService;

public class ExperienceController 
{
	@Autowired
	ExperienceService experienceService;
	
	@PutMapping("experience/{id}")
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
	}
}
