package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Diplome;
import fr.projet.app.model.Education;
import fr.projet.app.model.Specialite;
import fr.projet.app.repository.EducationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService 
{
	EducationRepository educationRepository;
	DiplomeService diplomeService;
	SpecialiteService specialiteService;

	public EducationService(EducationRepository educationRepository, DiplomeService diplomeService, SpecialiteService specialiteService)
	{
		this.educationRepository = educationRepository;
		this.diplomeService = diplomeService;
		this.specialiteService = specialiteService;
	}

	public List<Education> findAllEducations()
	{
		return educationRepository.findAll();
	}
	public Education getEducation(int idEducation) 
	{
		return educationRepository.findById(idEducation).get();
	}
	@Transactional
	public Education createEducation(Candidat cdt, Education edc) throws Exception
	{
		try
		{
			edc.setCandidat(cdt);
			Specialite specialite = specialiteService.createSpecialite(edc.getSpecialite());
			if(specialite != null) edc.setSpecialite(specialite);
			
			Diplome diplome = diplomeService.createDiplome(edc.getDiplome());
			if(diplome != null) edc.setDiplome(diplome);				
			
			return educationRepository.save(edc);
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur EducationService - createEducation() : " + exception);
		}
	}

	
	@Transactional
	public Education updateEducation(int idEducation, Education edc) throws Exception
	{
		try
		{
			Education education = educationRepository.findById(idEducation).orElseThrow();
			education.setRecu(edc.getRecu());
			education.setLieu(edc.getLieu());
			education.setEcole(edc.getEcole());
			education.setDebut(edc.getDebut());
			education.setFin(edc.getFin());
			education.setInfo(edc.getInfo());
			
			Specialite oldSpl = education.getSpecialite();
			Specialite rqtSpl = edc.getSpecialite();
			if(!rqtSpl.equals(oldSpl))
			{	
				education.setSpecialite(null);
				Specialite newSpl = specialiteService.updateSpecialite(oldSpl, rqtSpl);
				education.setSpecialite(newSpl);
			}
			
			Diplome oldDpl = education.getDiplome();
			Diplome rqtDpl = edc.getDiplome();
			if(!rqtDpl.equals(oldDpl))
			{
				education.setDiplome(null);
				Diplome newDpl = diplomeService.updateDiplome(oldDpl, rqtDpl);
				education.setDiplome(newDpl);
			}
			
			return educationRepository.save(education);
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur EducationService - updateEducation(): " + exception);
		}
	}
	
	
	@Transactional
	public void deleteEducation(int idEducation) 
	{
		Optional<Education> optionalEdc = educationRepository.findById(idEducation);
		if(optionalEdc.isPresent())
		{
			int idSpl = optionalEdc.get().getSpecialite().getIdSpecialite();
			int idDpl = optionalEdc.get().getDiplome().getIdDiplome();
				
			educationRepository.deleteById(idEducation);
			System.out.println("idEducation : "+idEducation);
			
			Optional<Specialite> optionalSpl = specialiteService.findSpecialiteById(idSpl);
			if(optionalSpl.isPresent())
			{
				System.out.println("spl Size : "+optionalSpl.get().getEducations().size());
				if(optionalSpl.get().getEducations().size() < 1)
				{
					System.out.println("deleting : "+idSpl);
					specialiteService.deleteSpecialite(idSpl);	
				}
			}
			
			Optional<Diplome> optionalDpl = diplomeService.findDiplomeById(idDpl);
			if(optionalDpl.isPresent())
			{
				System.out.println("dpl Size : "+optionalDpl.get().getEducations().size());
				if(optionalDpl.get().getEducations().size() < 1)
				{
					System.out.println("deleting : "+idDpl);
					diplomeService.deleteDiplome(idDpl);
				}
			}
			System.out.println("resultat : "+idEducation +" - "+idSpl+" - "+idDpl);
		}
	}
}







