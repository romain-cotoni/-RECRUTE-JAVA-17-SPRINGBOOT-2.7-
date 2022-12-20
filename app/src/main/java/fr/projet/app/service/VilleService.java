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

    public Ville createVille(Ville ville) throws Exception
    {
        try
        {
        	return villeRepository.save(ville);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur createVille - VilleService : " + exception.getMessage());
        }
    }
    
    /*@Transactional
    public Ville createVille(Ville ville) throws Exception
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
            throw new Exception("Erreur createVille - VilleService : " + exception.getMessage());
        }
    }*/


    @Transactional
    public Ville updateVille(Ville oldVille, Ville newVille) throws Exception
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
        	throw new Exception("Erreur updateVille - VilleService : " + exception.getMessage());
        }
    }


    public void deleteVille(int idVille) throws Exception
    {
        try
        {
            villeRepository.deleteById(idVille);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur deleteVille - VilleService : " + exception.getMessage());
        }
    }

    public Boolean checkIfVilleIsNotUsed(Ville ville) throws Exception
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
        	throw new Exception("Erreur checkIfVilleIsNotUsed - VilleService : " + exception.getMessage());
        }
    }


    public Optional<Ville> findVilleById(int idVille) throws Exception
    {
        try
        {
            return villeRepository.findById(idVille);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur findVilleById - VilleService : " + exception.getMessage());
        }
    }


    public Optional<Ville> findByVilleAndPostal(String ville, String postal) throws Exception
    {
        try
        {
            return villeRepository.findByVilleAndPostal(ville, postal);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur findByVilleAndPostal - VilleService : " + exception.getMessage());
        }
    }
}
