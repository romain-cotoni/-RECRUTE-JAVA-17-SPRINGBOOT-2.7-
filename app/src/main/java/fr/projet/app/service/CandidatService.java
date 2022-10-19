package fr.projet.app.service;

import fr.projet.app.model.*;
import fr.projet.app.repository.CandidatRepository;
import fr.projet.app.repository.CandidatRepositoryCustom;
import fr.projet.app.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CandidatService 
{
	private CandidatRepository candidatRepository;
	private DocumentRepository documentRepository;
	private CandidatRepositoryCustom candidatRepositoryCustom;
	private EducationService educationService;
	private ExperienceService experienceService;

	public CandidatService(CandidatRepository candidatRepository, DocumentRepository documentRepository, CandidatRepositoryCustom candidatRepositoryCustom, EducationService educationService, ExperienceService experienceService)
	{
		this.candidatRepository = candidatRepository;
		this.documentRepository = documentRepository;
		this.candidatRepositoryCustom = candidatRepositoryCustom;
		this.educationService = educationService;
		this.experienceService = experienceService;
	}
	
//Candidat
	public List<Candidat> findAllCandidats() 
	{
		return candidatRepository.findAll();
	}


	public Candidat findCandidatById(int id) 
	{
		return candidatRepository.findById(id).orElseThrow();
	}
	
	public Candidat findCandidatByName(Candidat candidat) 
	{
		return candidatRepository.findByName(candidat.getPrenom(),candidat.getNom());
	}

	/***
	 *
	 * @param CandidatsearchQuery candidat
	 * @return
	 */
	public List<Candidat> findCandidatsByParams(CandidatSearchQuery candidat)
	{
		String prenom       = candidat.getPrenom();
		String nom          = candidat.getNom();
		String telephone    = candidat.getTelephone();
		String email        = candidat.getEmail();
		Boolean teletravail = candidat.getTeletravail();
		Boolean handicape   = candidat.getHandicape();
		Boolean disponible  = candidat.getDisponible();
		String diplomes     = candidat.getDiplomes();
		String specialites  = candidat.getSpecialites();
		String missions     = candidat.getMissions();
		String entreprises  = candidat.getEntreprises();
		String competences  = candidat.getCompetences();
		String  langues     = candidat.getLangues();
		String pseudos      = candidat.getPseudos();
		String ville        = candidat.getVille();
		Integer mobilite    = candidat.getMobilite();
		System.out.println("prenom service in  : "+prenom+" - "+nom+" - "+telephone+" - "+email+" - "+teletravail+" - "+handicape+" - "+disponible+" - "+diplomes+" - "+specialites+" - "+missions+" - "+entreprises+" - "+competences+" - "+langues+" - "+ville+" - "+mobilite);
		List<Candidat> candidats =	candidatRepository.findByParams(prenom, nom, telephone, email, teletravail, handicape, disponible, diplomes, specialites, missions, entreprises, competences, langues, pseudos, ville, mobilite);

		return candidats;
	}

	public Candidat createCandidat(Candidat candidat)
	{
		int id = candidatRepositoryCustom.createCandidat(candidat);
		return findCandidatById(id);
	}
	
	public void deleteCandidatById(int id) 
	{
		candidatRepository.deleteById(id);
	}

	public List<String> findAllCandidatsPrenoms() { return candidatRepository.findAllPrenoms(); }

	public List<String> findAllCandidatsNoms() { return candidatRepository.findAllNoms(); }
	
//Education
	@Transactional
	public Set<Education> findEducationsByCandidatId(int id) 
	{
		return candidatRepository.findEducationsByCandidatId(id);
	}
	
	@Transactional
	public Education addEducation(int idCandidat, Education edc) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Education education = educationService.createEducation(candidat, edc);
				candidat.getEducations().add(education);
				candidatRepository.save(candidat);
				return education;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur CandidatService - addEducation(): " + exception);
		}
	}


//Experience
	@Transactional
	public Set<Experience> findExperiencesByCandidatId(int id)
	{
		return candidatRepository.findExperiencesByCandidatId(id);
	}

	@Transactional
	public Experience addExperience(int idCandidat, Experience exp) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Experience experience = experienceService.createExperience(candidat, exp);
				candidat.getExperiences().add(experience);
				candidatRepository.save(candidat);
				return experience;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur CandidatService - addExperience(): " + exception);
		}
	}

//Document
	public Set<Document> findDocumentsByCandidatId(int id)
	{
		return candidatRepository.findDocumentsByCandidatId(id);
	}

	@Transactional
	public Document addDocument(int id, Document document)
	{
		Candidat candidat       = candidatRepository.findById(id).orElseThrow();
		Set<Document> documents = candidat.getDocuments();
		Document newDocument    = documentRepository.save(document);
		documents.add(newDocument);
		return documents.stream().filter(e -> e.equals(document)).findFirst().get();
	}


}
