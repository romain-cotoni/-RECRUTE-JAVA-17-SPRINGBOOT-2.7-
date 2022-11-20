package fr.projet.app.service;

import fr.projet.app.model.*;
import fr.projet.app.repository.CandidatRepository;
import fr.projet.app.repository.CandidatRepositoryCustom;
import fr.projet.app.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
	private DocumentRepository documentRepository;

	public CandidatService(CandidatRepository candidatRepository, CandidatRepositoryCustom candidatRepositoryCustom, EducationService educationService, ExperienceService experienceService, CompetenceService competenceService, LangueService langueService, ProjetService projetService, EntretienService entretienService, PseudoService pseudoService, MobiliteService mobiliteService, VilleService villeService, PaysService paysService, DocumentRepository documentRepository)
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
		this.documentRepository = documentRepository;
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

	

	/*public List<Candidat> findCandidatsByParams(CandidatSearchQuery candidat)
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
		String langues      = candidat.getLangues();      
		String pseudos      = candidat.getPseudos();       
		String ville        = candidat.getVille();        
		Integer mobilite    = candidat.getMobilite();
		List<Candidat> candidats = candidatRepository.findByParams(prenom, nom, telephone, email, teletravail, handicape, disponible, diplomes, specialites, missions, entreprises, competences, langues, pseudos, ville, mobilite);
		return candidats;
	}*/

	public List<Candidat> findCandidatByParamsDynamicQuery2(CandidatSearchQuery candidat)
	{
		return candidatRepository.findByParams
		( candidat.getPrenom(),
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

	@Autowired
	EntityManager em;
	public List<Candidat> findCandidatByParamsDynamicQuery(CandidatSearchQuery csq)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Candidat> cq = cb.createQuery(Candidat.class);

        Root<Candidat> candidat = cq.from(Candidat.class);
        
		String s = csq.getPrenom();
        List<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));
		List<Predicate> predicates = new ArrayList<>();
        if(csq.getPrenom() != null || csq.getPrenom() != "") 
		{
            //predicates.add(cb.like(candidat.get("prenom"), "%" + csq.getPrenom() + "%"));
			predicates.add(cb.like(candidat.get("prenom"), "%" + csq.getPrenom() + "%"));
        }
        if (csq.getNom() != null || csq.getNom() != "") 
		{
            predicates.add(cb.like(candidat.get("nom"), "%" + csq.getNom() + "%"));
        }
        cq.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(cq).getResultList();
		
		//Predicate prenomPredicate = cb.equal(candidat.get("prenom"), csq.getPrenom());
		/*Predicate prenomPredicate = cb.like(candidat.get("prenom"), "%" + csq.getPrenom() + "%");
        Predicate nomPredicate    = cb.like(candidat.get("nom"), "%" + csq.getNom() + "%");
        cq.where(prenomPredicate, nomPredicate);
        TypedQuery<Candidat> query = em.createQuery(cq);
        return query.getResultList();*/
	}

	public Candidat createCandidat(Candidat candidat)
	{
		int id = candidatRepositoryCustom.createCandidat(candidat);
		return findCandidatById(id);
	}

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

			for (Pseudo oldPseudo : candidat.getPseudos()) //supprimer les pseudos en bd
			{
				//System.out.println(oldPseudo.getIdPseudo());
				pseudoService.deletePseudo(oldPseudo.getIdPseudo());
			}
			for (Pseudo newPseudo : cdt.getPseudos()) //ajouter les pseudos en bd
			{
				if(newPseudo.getPseudo() != "")
				{
					//System.out.println(newPseudo.getPseudo()+" - "+newPseudo.getReseau().getReseau());
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
			System.out.println("Erreur updateCandidat() - CandidatService : " + exception);
			return null;
		}
	}

	
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
			System.out.println("Erreur addEducation - CandidatService : " + exception);
			return null;
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
			System.out.println("Erreur addExperience - CandidatService : " + exception);
			return null;
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
			System.out.println("Erreur addCompetence - CandidatService : " + exception);
			return null;
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
			System.out.println("Erreur addLangue - CandidatService : " + exception);
			return null;
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
