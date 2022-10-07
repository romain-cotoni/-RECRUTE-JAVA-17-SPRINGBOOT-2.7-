package fr.projet.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.app.model.Experience;
import fr.projet.app.repository.ExperienceRepository;

@Service
public class ExperienceService 
{
	@Autowired
	ExperienceRepository experienceRepository;
	

	public Experience updateExperience(int id, Experience experience) 
	{
		Optional<Experience> experienceOptional = experienceRepository.findById(id);
		Experience updatedExperience   = null;
		if(experienceOptional.isPresent())
		{
			updatedExperience = experienceOptional.get();
			updatedExperience.setDebut(experience.getDebut());
			updatedExperience.setFin(experience.getFin());
			updatedExperience.setInfo(experience.getInfo());
			updatedExperience.setDebut(experience.getDebut());
			updatedExperience.setMission(experience.getMission());
			updatedExperience.setEntreprise(experience.getEntreprise());
		}
		return experienceRepository.save(updatedExperience);
		//return updatedExperience;
	}

	public void deleteExperience(int id) 
	{
		experienceRepository.deleteById(id);
	}

}
