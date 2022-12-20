package fr.projet.app.service;

import fr.projet.app.model.Reseau;
import fr.projet.app.repository.ReseauRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ReseauService
{
    private ReseauRepository reseauRepository;

    public ReseauService(ReseauRepository reseauRepository)
    {
        this.reseauRepository = reseauRepository;
    }


    @Transactional
    public Reseau createReseau(Reseau reseau)
    {
        Optional<Reseau> optionalRes = reseauRepository.findAll().stream().filter(d -> d.equals(reseau)).findFirst();
        if(optionalRes.isPresent())
        {
            Reseau existRes = optionalRes.get();
            if(existRes != null) return existRes;
            else return reseauRepository.save(reseau);
        }
        else
        {
            return reseauRepository.save(reseau);
        }
    }


    @Transactional
    public Reseau updateReseau(Reseau oldRes, Reseau newRes)
    {
        if(oldRes.getPseudos().size() <= 1)
        {
            this.deleteReseau(oldRes.getIdReseau());
        }
        Optional<Reseau> optionalRes = reseauRepository.findAll().stream().filter(d -> d.equals(newRes)).findFirst();
        if(optionalRes.isPresent())
        {
            Reseau existRes = optionalRes.get();
            if(existRes != null) return existRes;
        }
        return reseauRepository.save(newRes);
    }


    public void deleteReseau(int idReseau)
    {
        reseauRepository.deleteById(idReseau);
        System.out.println("idRes deleted : "+idReseau);
    }

    public Optional<Reseau> findReseauById(int idReseau)
    {
        return reseauRepository.findById(idReseau);
    }
    
    public Optional<Reseau> findReseauByName(String name)
    {
        return reseauRepository.findByReseau(name);
    }
}
