package fr.projet.app.service;

import fr.projet.app.model.Ville;
import fr.projet.app.repository.VilleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VilleService
{
    private VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository)
    {
        this.villeRepository = villeRepository;
    }

    @Transactional
    public Ville createVille(Ville ville)
    {
        try
        {
            Optional<Ville> optionalVille = villeRepository.findAll().stream().filter(d -> d.equals(ville)).findFirst();
            if(optionalVille.isPresent())
            {
                Ville existVille = optionalVille.get();
                if(existVille != null) return existVille;
                else return villeRepository.save(ville);
            }
            else
            {
                return villeRepository.save(ville);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur createVille - VilleService : " + exception);
            return null;
        }
    }


    @Transactional
    public Ville updateVille(Ville oldVille, Ville newVille)
    {
        try
        {
            if (oldVille.getCandidats().size() <= 1)
            {
                this.deleteVille(oldVille.getIdVille());
            }
            Optional<Ville> optionalVille = villeRepository.findAll().stream().filter(d -> d.equals(newVille)).findFirst();
            if (optionalVille.isPresent())
            {
                Ville existVille = optionalVille.get();
                if (existVille != null) return existVille;
            }
            return villeRepository.save(newVille);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur updateVille - VilleService : " + exception);
            return null;
        }
    }


    public void deleteVille(int idVille)
    {
        try
        {
            villeRepository.deleteById(idVille);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deleteVille - VilleService : " + exception);
        }
    }

    public Boolean checkIfVilleIsNotUsed(Ville ville)
    {
        try
        {
            if(ville.getCandidats().isEmpty() && ville.getEntreprises().isEmpty())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur checkIfVilleIsNotUsed - VilleService : " + exception);
            return false;
        }
    }


    public Optional<Ville> findVilleById(int idVille)
    {
        try
        {
            return villeRepository.findById(idVille);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findVilleById - VilleService : " + exception);
            return null;
        }
    }


    public Optional<Ville> findByVilleAndPostal(String ville, String postal)
    {
        try
        {
            return villeRepository.findByVilleAndPostal(ville, postal);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findByVilleAndPostal - VilleService : " + exception);
            return null;
        }
    }
}
