package fr.projet.app.service;

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
    public Langue createLangue(Langue lng) throws Exception
    {
        try
        {
            Optional<Langue> optionalLng = langueRepository.findAll().stream().filter(d -> d.equals(lng)).findFirst();
            if(optionalLng.isPresent())
            {
                Langue existLng = optionalLng.get();
                if(existLng != null) return existLng;
            }
            return langueRepository.save(lng);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur createLangue - LangueService : " + exception);
            return null;
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
            System.out.println("Erreur updateLangue - LangueService : " + exception);
            return null;
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
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deleteLangue - LangueService : " + exception);
        }
    }
}
