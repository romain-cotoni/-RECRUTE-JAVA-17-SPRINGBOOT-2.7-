package fr.projet.app.service;

import fr.projet.app.model.Entreprise;
import fr.projet.app.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EntrepriseService
{
    @Autowired
    EntrepriseRepository entrepriseRepository;


    @Transactional
    public Entreprise createEntreprise(Entreprise entreprise)
    {
        Optional<Entreprise> optionalEtp = entrepriseRepository.findAll().stream().filter(s -> s.equals(entreprise)).findFirst();
        if(optionalEtp.isPresent())
        {
            Entreprise existEtp = optionalEtp.get();
            if(existEtp != null) return existEtp;
            else return entrepriseRepository.save(entreprise);
        }
        else
        {
            return entrepriseRepository.save(entreprise);
        }
    }



    @Transactional
    public Entreprise updateEntreprise(Entreprise oldEtp, Entreprise newEtp)
    {
        if(oldEtp.getExperiences().size() <= 1)
        {
            this.deleteEntreprise(oldEtp.getIdEntreprise());
        }
        Optional<Entreprise> optionalEtp = entrepriseRepository.findAll().stream().filter(s -> s.equals(newEtp)).findFirst();
        if(optionalEtp.isPresent())
        {
            //return optionalEtp.orElseGet(() -> entrepriseRepository.save(newEtp));
            Entreprise existEtp = optionalEtp.get();
            if(existEtp != null) return existEtp;
        }
        return entrepriseRepository.save(newEtp);
    }



    public void deleteEntreprise(int idEntreprise)
    {
        entrepriseRepository.deleteById(idEntreprise);
        System.out.println("idEtp deleted : "+idEntreprise);
    }

    public Optional<Entreprise> findEntrepriseById(int idEntreprise)
    {
        return entrepriseRepository.findById(idEntreprise);
    }
}
