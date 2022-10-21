package fr.projet.app.service;

import fr.projet.app.model.Pays;
import fr.projet.app.repository.PaysRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PaysService
{
    private PaysRepository paysRepository;

    public PaysService(PaysRepository paysRepository)
    {
        this.paysRepository = paysRepository;
    }

    @Transactional
    public Pays createPays(Pays pays)
    {
        try
        {
            Optional<Pays> optionalPays = paysRepository.findAll().stream().filter(d -> d.equals(pays)).findFirst();
            if(optionalPays.isPresent())
            {
                Pays existPays = optionalPays.get();
                if(existPays != null) return existPays;
                else return paysRepository.save(pays);
            }
            else
            {
                return paysRepository.save(pays);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur createPays - PaysService : " + exception);
            return null;
        }
    }


    @Transactional
    public Pays updatePays(Pays oldPays, Pays newPays)
    {
        try
        {
            if (oldPays.getCandidats().size() <= 1)
            {
                this.deletePays(oldPays.getIdPays());
            }
            Optional<Pays> optionalPays = paysRepository.findAll().stream().filter(d -> d.equals(newPays)).findFirst();
            if (optionalPays.isPresent())
            {
                Pays existPays = optionalPays.get();
                if (existPays != null) return existPays;
            }
            return paysRepository.save(newPays);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur updatePays - PaysService : " + exception);
            return null;
        }
    }


    public void deletePays(int idPays)
    {
        try
        {
            paysRepository.deleteById(idPays);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deletePays - PaysService : " + exception);
        }
    }

    public Boolean checkIfPaysIsNotUsed(Pays pays)
    {
        try
        {
            if(pays.getCandidats().isEmpty() && pays.getVilles().isEmpty())
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
            System.out.println("Erreur checkIfPaysIsNotUsed - PaysService : " + exception);
            return false;
        }
    }


    public Optional<Pays> findPaysById(int idPays)
    {
        try
        {
            return paysRepository.findById(idPays);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findPaysById - PaysService : " + exception);
            return null;
        }
    }


    public Optional<Pays> findByPays(String pays)
    {
        try
        {
            return paysRepository.findByPays(pays);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findByPays - PaysService : " + exception);
            return null;
        }
    }

    public Optional<Pays> findByNationnalite(String nationnalite)
    {
        try
        {
            return paysRepository.findByNationnalite(nationnalite);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findByNationnalite - PaysService : " + exception);
            return null;
        }
    }

    public List<Pays> findAllPays()
    {
        return paysRepository.findAllPays();
    }
}
