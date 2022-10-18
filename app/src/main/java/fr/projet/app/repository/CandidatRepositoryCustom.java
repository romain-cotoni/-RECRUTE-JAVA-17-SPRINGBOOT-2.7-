package fr.projet.app.repository;

import fr.projet.app.model.Candidat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class CandidatRepositoryCustom
{

	//private CandidatRepository candidatRepository;
    private EntityManager entityManager;

	public CandidatRepositoryCustom(EntityManager entityManager/*, CandidatRepository candidatRepository*/)
	{
		this.entityManager = entityManager;
		//this.candidatRepository = candidatRepository;
	}

	@Transactional
	public int createCandidat(Candidat candidat)
	{
		this.entityManager.persist(candidat);
		this.entityManager.flush();
		return candidat.getIdCandidat();
	}
	
	/*public List<Candidat> findAllCandidats()
	{
		return candidatRepository.findAll();
	}*/
	
}
