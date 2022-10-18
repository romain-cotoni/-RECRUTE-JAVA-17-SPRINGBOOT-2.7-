package fr.projet.app.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.projet.app.model.Candidat;

@Repository
public class CandidatRepositoryCustom
{

	private CandidatRepository candidatRepository;
    private EntityManager entityManager;

	public CandidatRepositoryCustom(EntityManager entityManager, CandidatRepository candidatRepository)
	{
		this.entityManager = entityManager;
		this.candidatRepository = candidatRepository;
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
