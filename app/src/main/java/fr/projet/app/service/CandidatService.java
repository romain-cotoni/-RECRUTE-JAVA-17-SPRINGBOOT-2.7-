package fr.projet.app.service;

import fr.projet.app.model.*;
import fr.projet.app.repository.CandidatRepository;
import fr.projet.app.repository.CandidatRepositoryCustom;
import fr.projet.app.repository.DocumentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class CandidatService 
{
	private CandidatRepository candidatRepository;
	private CandidatRepositoryCustom candidatRepositoryCustom;
	private EducationService educationService;
	private ExperienceService experienceService;
	private CompetenceService competenceService;
	private LangueService langueService;
	private ProjetService projetService;
	private EntretienService entretienService;
	private PseudoService pseudoService;
	private MobiliteService mobiliteService;
	private VilleService villeService;
	private PaysService paysService;
	private ReseauService reseauService;
	private DocumentRepository documentRepository;
	private EntityManager entityManager;
	
	

	public CandidatService(CandidatRepository candidatRepository, CandidatRepositoryCustom candidatRepositoryCustom, EducationService educationService, ExperienceService experienceService, CompetenceService competenceService, LangueService langueService, ProjetService projetService, EntretienService entretienService, PseudoService pseudoService, MobiliteService mobiliteService, VilleService villeService, PaysService paysService, DocumentRepository documentRepository, ReseauService reseauService, EntityManager entityManager)
	{
		this.candidatRepository = candidatRepository;
		this.candidatRepositoryCustom = candidatRepositoryCustom;
		this.educationService = educationService;
		this.experienceService = experienceService;
		this.competenceService = competenceService;
		this.langueService = langueService;
		this.projetService = projetService;
		this.entretienService = entretienService;
		this.pseudoService = pseudoService;
		this.mobiliteService = mobiliteService;
		this.villeService = villeService;
		this.paysService = paysService;
		this.reseauService = reseauService;
		this.documentRepository = documentRepository;
		this.entityManager = entityManager;
	}
	
//Candidat
	public List<Candidat> findAllCandidats() 
	{
		return candidatRepository.findAll();
	}
	
	public List<Candidat> findAllCandidatsShort() 
	{
		return candidatRepository.findAllShort();
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
	 * method to search one or many candidat by multiple and optional parameters
	 * @param candidat object CandidatSearchQuery
	 * @return List of object Candidat
	 */
	public List<Candidat> findCandidatsByParams(CandidatSearchQuery candidat)
	{
		return candidatRepository.findByParams( candidat.getPrenom(),
												candidat.getNom(), 
												candidat.getTelephone(), 
												candidat.getEmail(), 
												candidat.getTeletravail(), 
												candidat.getHandicape(), 
												candidat.getDisponible(), 
												candidat.getDiplomes(),
												candidat.getSpecialites(), 
												candidat.getMissions(),
												candidat.getEntreprises(), 
												candidat.getCompetences(), 
												candidat.getLangues(), 
												candidat.getPseudos(), 
												candidat.getVille(), 
												candidat.getMobilite());		    
	}


	public List<Candidat> findCandidatByParamsDynamicQuery2(CandidatSearchQuery candidat)
	{
		return candidatRepository.findByParams(   
			candidat.getPrenom(),
			candidat.getNom(), 
			candidat.getTelephone(), 
			candidat.getEmail(), 
			candidat.getTeletravail(), 
			candidat.getHandicape(), 
			candidat.getDisponible(), 
			candidat.getDiplomes(),
			candidat.getSpecialites(), 
			candidat.getMissions(),
			candidat.getEntreprises(), 
			candidat.getCompetences(), 
			candidat.getLangues(), 
			candidat.getPseudos(), 
			candidat.getVille(), 
			candidat.getMobilite()
		);                         
	}

	
	public List<Candidat> findCandidatByParamsDynamicQuery(CandidatSearchQuery csq)
	{
		CriteriaBuilder cb         = entityManager.getCriteriaBuilder();
        CriteriaQuery<Candidat> cq = cb.createQuery(Candidat.class);		
        Root<Candidat> candidat    = cq.from(Candidat.class);
		
        List<String> listPrenom     = new ArrayList<String>(Arrays.asList(csq.getPrenom().split("[,;:| ]")));
        List<String> listNom        = new ArrayList<String>(Arrays.asList(csq.getNom().split("[,;:| ]")));
		List<String> listTel        = new ArrayList<String>(Arrays.asList(csq.getTelephone().split("[,;:| ]")));
		List<String> listEmail      = new ArrayList<String>(Arrays.asList(csq.getEmail().split("[,;:| ]")));
		List<String> listVille      = new ArrayList<String>(Arrays.asList(csq.getVille().split("[,;:| ]")));
		List<String> listCompetence = new ArrayList<String>(Arrays.asList(csq.getCompetences().split("[,;:| ]")));
		List<String> listDiplome    = new ArrayList<String>(Arrays.asList(csq.getDiplomes().split("[,;:| ]")));
		List<String> listSpecialite = new ArrayList<String>(Arrays.asList(csq.getSpecialites().split("[,;:| ]")));
		List<String> listLangue     = new ArrayList<String>(Arrays.asList(csq.getLangues().split("[,;:| ]")));
        List<String> listMission    = new ArrayList<String>(Arrays.asList(csq.getMissions().split("[,;:| ]")));
        List<String> listEntreprise = new ArrayList<String>(Arrays.asList(csq.getEntreprises().split("[,;:| ]")));
        Boolean      handicape      = csq.getHandicape();
		Boolean      teletravail    = csq.getTeletravail();
		Boolean      disponible     = csq.getDisponible();

		List<Predicate> predicates = new ArrayList<>();
		if(!csq.getNom().isEmpty() || !csq.getNom().isBlank()) 
		{
			predicates.add(candidat.get("nom").in(listNom));
        }
		if(!csq.getPrenom().isEmpty() || !csq.getPrenom().isBlank())
		{   
			predicates.add(candidat.get("prenom").in(listPrenom));		
		}
		if(!csq.getTelephone().isEmpty() || !csq.getTelephone().isBlank()) 
		{   
			predicates.add(candidat.get("mob").in(listTel));	
		}
		if(!csq.getEmail().isEmpty() || !csq.getEmail().isBlank()) 
		{   
			predicates.add(candidat.get("email").in(listEmail));	
		}
		if(!csq.getVille().isEmpty() || !csq.getVille().isBlank()) 
		{   
			Join<Candidat,Ville> ville = candidat.join("ville",JoinType.LEFT);
			predicates.add(ville.get("ville").in(listVille));	
		}
		if(!csq.getCompetences().isEmpty() || !csq.getCompetences().isBlank()) 
		{   
			Join<Candidat,Competence> competence  = candidat.join("competences",JoinType.LEFT);
			predicates.add(competence.get("nom").in(listCompetence));	
		}
		if(!csq.getDiplomes().isEmpty() || !csq.getDiplomes().isBlank()) 
		{   
			Join<Candidat,Education> education = candidat.join("educations",JoinType.LEFT);
			Join<Education,Diplome> diplome = education.join("diplome",JoinType.LEFT);
			predicates.add(diplome.get("label").in(listDiplome));	
		}
		if(!csq.getSpecialites().isEmpty() || !csq.getSpecialites().isBlank()) 
		{
			Join<Candidat,Education> education = candidat.join("educations",JoinType.LEFT);
		    Join<Education,Specialite> specialite = education.join("specialite",JoinType.LEFT);
			predicates.add(specialite.get("label").in(listSpecialite));
		}
		if(!csq.getLangues().isEmpty() || !csq.getLangues().isBlank()) 
		{   
			Join<Candidat,Langue> langue  = candidat.join("langues",JoinType.LEFT);
			predicates.add(langue.get("nom").in(listLangue));	
		}
		if(!csq.getMissions().isEmpty() || !csq.getMissions().isBlank()) 
		{   
			Join<Candidat,Experience> experience  = candidat.join("experiences",JoinType.LEFT);
			Join<Experience,Mission> mission  = experience.join("mission",JoinType.LEFT);
			predicates.add(mission.get("profession").in(listMission));	
		}
		if(!csq.getEntreprises().isEmpty() || !csq.getEntreprises().isBlank()) 
		{   
			Join<Candidat,Experience> experience  = candidat.join("experiences",JoinType.LEFT);
			Join<Experience,Entreprise> entreprise  = experience.join("entreprise",JoinType.LEFT);
			predicates.add(entreprise.get("raisonSociale").in(listEntreprise));	
		}
		if(handicape != null) 
		{   
			predicates.add(cb.equal(candidat.get("handicape"), handicape));	
		}
		if(teletravail != null) 
		{   
			predicates.add(cb.equal(candidat.get("teletravail"), teletravail));	
		}
		if(disponible != null) 
		{   
			predicates.add(cb.equal(candidat.get("disponible"), disponible));	
		}
		
        cq.where(predicates.toArray(new Predicate[0]));
		return entityManager.createQuery(cq.distinct(true)).getResultList();
	}
	

	public Candidat createCandidat(Candidat candidat)
	{
		int id = candidatRepositoryCustom.createCandidat(candidat);
		return findCandidatById(id);
	}

	String pseudo;
	@Transactional
	public Candidat updateCandidat(int idCandidat, Candidat cdt) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			candidat.setNom(cdt.getNom());
			candidat.setPrenom(cdt.getPrenom());
			candidat.setNaissance(cdt.getNaissance());
			candidat.setEmail(cdt.getEmail());
			candidat.setFixe(cdt.getFixe());
			candidat.setMob(cdt.getMob());
			candidat.setAdresse(cdt.getAdresse());
			candidat.setAdresse2(cdt.getAdresse2());
			candidat.setSalaire(cdt.getSalaire());
			candidat.setMarital(cdt.getMarital());
			candidat.setHandicape(cdt.getHandicape());
			candidat.setPermis(cdt.getPermis());
			candidat.setVehicule(cdt.getVehicule());
			candidat.setTeletravail(cdt.getTeletravail());
			candidat.setDisponible(cdt.getDisponible());
			candidat.setInfo(cdt.getInfo());
			
			candidat = this.updatePseudo(candidat, cdt.getPseudos(), "Linkedin");
			
			candidat = this.updatePseudo(candidat, cdt.getPseudos(), "Github");
			
			candidat = this.updateVille(candidat, cdt.getVille().getVille(),cdt.getVille().getPostal());
			
			candidat = this.updateNationalite(candidat, cdt.getPays().getNationnalite());
			
			candidat = this.updateMobilite(candidat, cdt.getMobilite().getZone());
			
			return candidatRepository.save(candidat);
		}
	    catch(Exception exception)
		{
			throw new Exception("Erreur updateCandidat - CandidatService : " + exception.getMessage());
		}
	}
	
	
	public Candidat updatePseudo(Candidat candidatToUpdate, Set<Pseudo> pseudos, String reseau) throws Exception
	{
		try
		{
			for(Pseudo oldPseudo : candidatToUpdate.getPseudos()) //effacer l'ancien pseudo Linkedin 
			{
				if(oldPseudo.getReseau().getReseau().contains(reseau))
				{
					pseudoService.deletePseudo(oldPseudo.getIdPseudo());
				}
			}
			
			for(Pseudo pseudo : pseudos)
			{
				if(pseudo.getReseau().getReseau().contains(reseau))
				{
					this.pseudo = pseudo.getPseudo();
				}
			}

			Pseudo pseudoToCreate = new Pseudo(this.pseudo, reseauService.findReseauByName(reseau).get());
			candidatToUpdate.getPseudos().add(pseudoToCreate); //ajouter le nouveau pseudo
			return candidatToUpdate;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updatePseudo - CandidatService : " + exception.getMessage());
		}
	}
	
	
	/*public Candidat updatePseudoLinkedin(Candidat candidatToUpdate, String pseudo) throws Exception
	{
		try
		{
			Optional<Pseudo> pseudoOptional = pseudoService.findByPseudoAndReseau(pseudo, "Linkedin");
			if(pseudoOptional.isPresent())
			{
				Pseudo pseudoRequest = pseudoOptional.get();
				for(Pseudo oldPseudo : candidatToUpdate.getPseudos()) //effacer l'ancien pseudo Linkedin 
				{
					if(oldPseudo.getReseau().getReseau() == "Linkedin")
					{
						candidatToUpdate.getPseudos().remove(oldPseudo);
						pseudoService.deletePseudo(oldPseudo.getIdPseudo());
					}
				}
				candidatToUpdate.getPseudos().add(pseudoRequest); 
				
			}
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updatePseudo - CandidatService : " + exception.getMessage());
		}
	}*/
	
	
	public Candidat updateVille(Candidat candidatToUpdate, String ville, String postal) throws Exception 
	{
		try
		{		
			Optional<Ville> villeOptional = villeService.findByVilleAndPostal(ville, postal);
			if(villeOptional.isPresent()) //utiliser une ville existante dans la db
			{
				Ville villeRequest = villeOptional.get();
				Ville oldVille = candidatToUpdate.getVille();
				if(!villeRequest.equals(oldVille))
				{
					candidatToUpdate.setVille(villeRequest);
					if(oldVille != null && villeService.checkIfVilleIsNotUsed(oldVille)) //si la ville n'est plus utilisée on la supprime de la db
					{
						villeService.deleteVille(oldVille.getIdVille());
					}
				}
			}
			else //créer nouvelle ville
			{
				Ville newVille = villeService.createVille(new Ville(ville, postal));
				candidatToUpdate.setVille(newVille);
			}
			return candidatToUpdate;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updateVille - CandidatService : " + exception.getMessage());
		}
	}
	
	
	public Candidat updateNationalite(Candidat candidatToUpdate, String nationalite) throws Exception
	{
		try
		{
			Optional<Pays> paysOptional = paysService.findByNationalite(nationalite);
			if(paysOptional.isPresent()) //utiliser une nationalité existante dans la db
			{
				Pays paysRequest = paysOptional.get();
				Pays oldPays = candidatToUpdate.getPays();
				if(!paysRequest.equals(oldPays))
				{
					candidatToUpdate.setPays(paysRequest);
					if(oldPays != null && paysService.checkIfPaysIsNotUsed(oldPays)) //si le pays n'est plus utilisée on la supprime de la db
					{
						paysService.deletePays(oldPays.getIdPays());
					}
				}
			}
			else //créer un nouveau pays
			{
				Pays newPays = paysService.createPays(new Pays(nationalite));
				candidatToUpdate.setPays(newPays);
			}
			return candidatToUpdate;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updateNationalite() - CandidatService : " + exception.getMessage());
		}
	}
	
	
	public Candidat updateMobilite(Candidat candidatToUpdate, Integer zone) throws Exception
	{
		try
		{
			Optional<Mobilite> mobiliteOptional = mobiliteService.findMobiliteByZone(zone);
			if(mobiliteOptional.isPresent())
			{
				candidatToUpdate.setMobilite(mobiliteOptional.get());
			}
			return candidatToUpdate;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updateMobilite() - CandidatService : " + exception.getMessage());
		}
	}
	
	/*@Transactional
	public Candidat updateCandidat(int idCandidat, Candidat cdt) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			candidat.setNom(cdt.getNom());
			candidat.setPrenom(cdt.getPrenom());
			candidat.setNaissance(cdt.getNaissance());
			candidat.setEmail(cdt.getEmail());
			candidat.setFixe(cdt.getFixe());
			candidat.setMob(cdt.getMob());
			candidat.setAdresse(cdt.getAdresse());
			candidat.setAdresse2(cdt.getAdresse2());
			candidat.setSalaire(cdt.getSalaire());
			candidat.setMarital(cdt.getMarital());
			candidat.setHandicape(cdt.getHandicape());
			candidat.setPermis(cdt.getPermis());
			candidat.setVehicule(cdt.getVehicule());
			candidat.setTeletravail(cdt.getTeletravail());
			candidat.setDisponible(cdt.getDisponible());
			candidat.setInfo(cdt.getInfo());

			for (Pseudo oldPseudo : candidat.getPseudos()) //supprimer les pseudos en bd
			{
				pseudoService.deletePseudo(oldPseudo.getIdPseudo());
			}
			for (Pseudo newPseudo : cdt.getPseudos()) //ajouter les pseudos en bd
			{
				if(newPseudo.getPseudo() != "")
				{
					pseudoService.createPseudo(candidat,newPseudo);
				}
			}

			
			try
			{
				Ville rqtVille = villeService.findByVilleAndPostal(cdt.getVille().getVille(), cdt.getVille().getPostal()).orElseThrow();
			
				if(rqtVille != null) //utiliser une ville existante dans la db
				{
					Ville oldVille = candidat.getVille();
					if(!rqtVille.equals(oldVille))
					{
						candidat.setVille(rqtVille);
						if(oldVille != null && villeService.checkIfVilleIsNotUsed(oldVille)) //si la ville n'est plus utilisée on la supprime de la db
						{
							villeService.deleteVille(oldVille.getIdVille());
						}
					}
				}
				else //créer nouvelle ville
				{
					Ville newVille = villeService.createVille(rqtVille);
					candidat.setVille(newVille);
				}
	
				Pays rqtPays = paysService.findByNationnalite(cdt.getPays().getNationnalite()).orElseThrow();
				if(rqtPays != null) //utiliser une nationnalité existante dans la db
				{
					Pays oldPays = candidat.getPays();
					if(!rqtPays.equals(oldPays))
					{
						candidat.setPays(rqtPays);
						if(oldPays != null && paysService.checkIfPaysIsNotUsed(oldPays)) //si le pays n'est plus utilisée on la supprime de la db
						{
							paysService.deletePays(oldPays.getIdPays());
						}
					}
				}
				else //créer un nouveau pays
				{
					Pays newPays = paysService.createPays(rqtPays);
					candidat.setPays(newPays);
				}
			}
			catch(Exception exception)
			{
				System.out.println("Erreur updateCandidat() - CandidatService : ville + pays : " + exception);
			}

			Mobilite newMobilite = mobiliteService.findMobiliteByZone(cdt.getMobilite().getZone()).orElseThrow();
			if(newMobilite != null)
			{
				candidat.setMobilite(newMobilite);
			}

			return candidatRepository.save(candidat);
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur updateCandidat() - CandidatService : " + exception.getMessage());
		}
	}*/

	
	public void deleteCandidatById(int id) 
	{
		candidatRepository.deleteById(id);
	}

	public List<String> findAllCandidatsPrenoms()
	{
		return candidatRepository.findAllPrenoms();
	}

	public List<String> findAllCandidatsNoms()
	{
		return candidatRepository.findAllNoms();
	}


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
			throw new Exception("Erreur addEducation - CandidatService : " + exception.getMessage());
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
			throw new Exception("Erreur addExperience - CandidatService : " + exception.getMessage());
		}
	}


//Competence
	@Transactional
	public Set<Competence> findCompetencesByCandidatId(int id)
	{
		return candidatRepository.findCompetencesByCandidatId(id);
	}

	@Transactional
	public Competence addCompetence(int idCandidat, Competence cmp) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Competence competence = competenceService.createCompetence(cmp);
				candidat.getCompetences().add(competence);
				candidatRepository.save(candidat);
				return competence;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur addCompetence - CandidatService : " + exception.getMessage());
		}
	}


//Langue
	@Transactional
	public Set<Langue> findLanguesByCandidatId(int id)
	{
		return candidatRepository.findLanguesByCandidatId(id);
	}

	@Transactional
	public Langue addLangue(int idCandidat, Langue lng) throws Exception
	{
		try
		{
			Candidat candidat = candidatRepository.findById(idCandidat).orElseThrow();
			if(candidat != null)
			{
				Langue langue = langueService.createLangue(lng);
				candidat.getLangues().add(langue);
				candidatRepository.save(candidat);
				return langue;
			}
			else return null;
		}
		catch(Exception exception)
		{
			throw new Exception("Erreur addLangue - CandidatService : " + exception.getMessage());
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
			throw new Exception("Erreur CandidatService - addProjet(): " + exception.getMessage());
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
			throw new Exception("Erreur CandidatService - addEntretien(): " + exception.getMessage());
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
			throw new Exception("Erreur CandidatService - addPseudo(): " + exception.getMessage());
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
