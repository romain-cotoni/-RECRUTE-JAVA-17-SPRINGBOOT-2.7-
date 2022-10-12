package fr.projet.app.service;

import java.util.List;
import java.util.Optional;

import fr.projet.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.app.repository.ExperienceRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExperienceService 
{
	@Autowired
	ExperienceRepository experienceRepository;

	@Autowired
	MissionService missionService;

	@Autowired
	EntrepriseService entrepriseService;

	public List<Experience> findAllExperiences()
	{
		return experienceRepository.findAll();
	}


	public Experience getExperience(int idExperience)
	{
		return experienceRepository.findById(idExperience).get();
	}


	@Transactional
	public Experience createExperience(Candidat cdt, Experience exp) throws Exception
	{
		try
		{
			exp.setCandidat(cdt);
			Mission mission = missionService.createMission(exp.getMission());
			if(mission != null) exp.setMission(mission);

			Entreprise entreprise = entrepriseService.createEntreprise(exp.getEntreprise());
			if(entreprise != null) exp.setEntreprise(entreprise);

			return experienceRepository.save(exp);
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur ExperienceService - createExperience() : " + exception);
		}
	}


	@Transactional
	public Experience updateExperience(int idExperience, Experience exp) throws Exception
	{
		try
		{
			Experience experience = experienceRepository.findById(idExperience).orElseThrow();
			experience.setDebut(exp.getDebut());
			experience.setFin(exp.getFin());
			experience.setInfo(exp.getInfo());
			experience.setLieu(exp.getLieu());

			Mission oldMsn = experience.getMission();
			Mission rqtMsn = exp.getMission();
			if(!rqtMsn.equals(oldMsn))
			{
				experience.setMission(null);
				Mission newMsn = missionService.updateMission(oldMsn, rqtMsn);
				experience.setMission(newMsn);
			}

			Entreprise oldEtp = experience.getEntreprise();
			Entreprise rqtEtp = exp.getEntreprise();
			if(!rqtEtp.equals(oldEtp))
			{
				experience.setEntreprise(null);
				Entreprise newEtp = entrepriseService.updateEntreprise(oldEtp, rqtEtp);
				experience.setEntreprise(newEtp);
			}

			return experienceRepository.save(experience);
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur ExperienceService - updateExperience(): " + exception);
		}
	}


	@Transactional
	public void deleteExperience(int idExperience)
	{
		Optional<Experience> optionalexp = experienceRepository.findById(idExperience);
		if(optionalexp.isPresent())
		{
			int idMsn = optionalexp.get().getMission().getIdMission();
			int idEtp = optionalexp.get().getEntreprise().getIdEntreprise();

			experienceRepository.deleteById(idExperience);
			System.out.println("idExperience : "+idExperience);

			Optional<Mission> optionalMsn = missionService.findMissionById(idMsn);
			if(optionalMsn.isPresent())
			{
				System.out.println("msn Size : "+optionalMsn.get().getExperiences().size());
				if(optionalMsn.get().getExperiences().size() < 1)
				{
					System.out.println("deleting : "+idMsn);
					missionService.deleteMission(idMsn);
				}
			}

			Optional<Entreprise> optionalEtp = entrepriseService.findEntrepriseById(idEtp);
			if(optionalEtp.isPresent())
			{
				System.out.println("etp Size : "+optionalEtp.get().getExperiences().size());
				if(optionalEtp.get().getExperiences().size() < 1)
				{
					System.out.println("deleting : "+idEtp);
					entrepriseService.deleteEntreprise(idEtp);
				}
			}
			System.out.println("" +
					"" +
					"resultat : "+idExperience +" - "+idMsn+" - "+idEtp);
		}
	}
	/*public Experience updateExperience(int id, Experience experience)
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
	}*/

}
