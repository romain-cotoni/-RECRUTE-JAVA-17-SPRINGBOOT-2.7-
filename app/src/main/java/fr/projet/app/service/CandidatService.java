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
	private CandidatRepositoryCustom candidatRepositoryCustom;
	private EducationService educationService;
	private ExperienceService experienceService;
	private ProjetService projetService;
	private EntretienService entretienService;
	private PseudoService pseudoService;
	private DocumentRepository documentRepository;

	public CandidatService(CandidatRepository candidatRepository, CandidatRepositoryCustom candidatRepositoryCustom, EducationService educationService, ExperienceService experienceService, ProjetService projetService, EntretienService entretienService, PseudoService pseudoService, DocumentRepository documentRepository)
	{
		this.candidatRepository = candidatRepository;
		this.candidatRepositoryCustom = candidatRepositoryCustom;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.projetService = projetService;
		this.entretienService = entretienService;
		this.pseudoService = pseudoService;
		this.documentRepository = documentRepository;
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

	/**
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
		//System.out.println("prenom service in  : "+prenom+" - "+nom+" - "+telephone+" - "+email+" - "+teletravail+" - "+handicape+" - "+disponible+" - "+diplomes+" - "+specialites+" - "+missions+" - "+entreprises+" - "+competences+" - "+langues+" - "+ville+" - "+mobilite);
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
			System.out.println("candidatService-addExperience : "+idCandidat+" - "+exp);
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


//Projet
	@Transactional
	public Set<Projet> findProjetsByCandidatId(int id)
	{
		return candidatRepository.findProjetsByCandidatId(id);
	}

	@Transactional
	public Projet addProjet(int idCandidat, Projet prj) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Projet projet = projetService.createProjet(candidat, prj);
				candidat.getProjets().add(projet);
				candidatRepository.save(candidat);
				return projet;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur CandidatService - addProjet(): " + exception);
		}
	}

//Entretien
	@Transactional
	public Set<Entretien> findEntretiensByCandidatId(int id)
	{
		return candidatRepository.findEntretiensByCandidatId(id);
	}

	@Transactional
	public Entretien addEntretien(int idCandidat, Entretien etr) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Entretien entretien = entretienService.createEntretien(candidat, etr);
				candidat.getEntretiens().add(entretien);
				candidatRepository.save(candidat);
				return entretien;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur CandidatService - addEntretien(): " + exception);
		}
	}


//Pseudo
	@Transactional
	public Set<Pseudo> findPseudosByCandidatId(int id)
	{
		return candidatRepository.findPseudosByCandidatId(id);
	}

	@Transactional
	public Pseudo addPseudo(int idCandidat, Pseudo psd) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Pseudo pseudo = pseudoService.createPseudo(candidat, psd);
				candidat.getPseudos().add(pseudo);
				candidatRepository.save(candidat);
				return pseudo;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur CandidatService - addPseudo(): " + exception);
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
