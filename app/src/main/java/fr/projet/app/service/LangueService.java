package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Langue;
import fr.projet.app.repository.LangueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LangueService
{
    private LangueRepository langueRepository;

    public LangueService(LangueRepository langueRepository)
    {
        this.langueRepository = langueRepository;
    }


    public List<Langue> findAllLangues()
    {
        return langueRepository.findAll();
    }


    public Langue getLangue(int idLangue)
    {
        return langueRepository.findById(idLangue).get();
    }


    @Transactional
    public Langue createLangue(Candidat cdt, Langue lng) throws Exception
    {
        try
        {
            lng.getCandidats().add(cdt);
            return langueRepository.save(lng);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur createLangue() - LangueService : " + exception);
        }
    }


    @Transactional
    public Langue updateLangue(int idLangue, Langue lng) throws Exception
    {
        try
        {
            Langue langue = langueRepository.findById(idLangue).orElseThrow();
            langue.setNom(lng.getNom());
            langue.setNiveau(lng.getNiveau());
            langue.setCertification(lng.getCertification());
            langue.setInfo(lng.getInfo());

            return langueRepository.save(langue);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur LangueService - updateLangue(): " + exception);
        }
    }


    @Transactional
    public void deleteLangue(int idLangue)
    {
        try
        {
            Optional<Langue> optionalLng = langueRepository.findById(idLangue);
            if(optionalLng.isPresent())
            {
                langueRepository.deleteById(idLangue);
                System.out.println("idLangue : "+idLangue);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deleteLangue - LangueService : " + exception);
        }
    }
}
