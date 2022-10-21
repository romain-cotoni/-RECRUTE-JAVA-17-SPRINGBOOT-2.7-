package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Competence;
import fr.projet.app.repository.CompetenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService
{
    private CompetenceRepository competenceRepository;

    public CompetenceService(CompetenceRepository competenceRepository)
    {
        this.competenceRepository = competenceRepository;
    }
    

    public List<Competence> findAllCompetences()
    {
        return competenceRepository.findAll();
    }


    public Competence getCompetence(int idCompetence)
    {
        return competenceRepository.findById(idCompetence).get();
    }


    @Transactional
    public Competence createCompetence(Candidat cdt, Competence cmp) throws Exception
    {
        try
        {
            cmp.getCandidats().add(cdt);
            return competenceRepository.save(cmp);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur CompetenceService - createCompetence() : " + exception);
        }
    }


    @Transactional
    public Competence updateCompetence(int idCompetence, Competence cmp) throws Exception
    {
        try
        {
            Competence competence = competenceRepository.findById(idCompetence).orElseThrow();
            competence.setNom(cmp.getNom());
            competence.setNiveau(cmp.getNiveau());
            competence.setType(cmp.getType());
            competence.setInfo(cmp.getInfo());

            return competenceRepository.save(competence);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur CompetenceService - updateCompetence(): " + exception);
        }
    }


    @Transactional
    public void deleteCompetence(int idCompetence)
    {
        try
        {
            Optional<Competence> optionalCmp = competenceRepository.findById(idCompetence);
            if(optionalCmp.isPresent())
            {
                competenceRepository.deleteById(idCompetence);
                System.out.println("idCompetence : "+idCompetence);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deleteCompetence - CompetenceService : " + exception);
        }
    }
}
