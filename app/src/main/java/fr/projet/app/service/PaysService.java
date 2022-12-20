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

    public Pays createPays(Pays pays) throws Exception
    {
        try
        {
        	return paysRepository.save(pays);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur createPays - PaysService : " + exception.getMessage());
        }
    }
    
    /*@Transactional
    public Pays createPays(Pays pays) throws Exception
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
            throw new Exception("Erreur createPays - PaysService : " + exception.getMessage());
        }
    }*/


    @Transactional
    public Pays updatePays(Pays oldPays, Pays newPays) throws Exception
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
        	throw new Exception("Erreur updatePays - PaysService : " + exception.getMessage());
        }
    }


    public void deletePays(int idPays) throws Exception
    {
        try
        {
            paysRepository.deleteById(idPays);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur deletePays - PaysService : " + exception.getMessage());
        }
    }

    public Boolean checkIfPaysIsNotUsed(Pays pays) throws Exception
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
        	throw new Exception("Erreur checkIfPaysIsNotUsed - PaysService : " + exception.getMessage());
        }
    }


    public Optional<Pays> findPaysById(int idPays) throws Exception
    {
        try
        {
            return paysRepository.findById(idPays);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur findPaysById - PaysService : " + exception.getMessage());
        }
    }


    public Optional<Pays> findByPays(String pays) throws Exception
    {
        try
        {
            return paysRepository.findByPays(pays);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur findByPays - PaysService : " + exception.getMessage());
        }
    }

    public Optional<Pays> findByNationalite(String nationalite) throws Exception
    {
        try
        {
            return paysRepository.findByNationnalite(nationalite);
        }
        catch(Exception exception)
        {
        	throw new Exception("Erreur findByNationalite - PaysService : " + exception.getMessage());
        }
    }

    public List<Pays> findAllPays() throws Exception
    {
    	try
    	{
    		return paysRepository.findAllPays();
    	}
        catch(Exception exception)
        {
        	throw new Exception("Erreur findAllPays - PaysService : " + exception.getMessage());
        }
    }
}
