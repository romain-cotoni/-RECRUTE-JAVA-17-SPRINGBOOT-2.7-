package fr.projet.app.service;

import fr.projet.app.model.Mobilite;
import fr.projet.app.repository.MobiliteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MobiliteService
{
    private MobiliteRepository mobiliteRepository;

    public MobiliteService(MobiliteRepository mobiliteRepository)
    {
        this.mobiliteRepository = mobiliteRepository;
    }


    @Transactional
    public Mobilite createMobilite(Mobilite mobilite)
    {
        try
        {
            Optional<Mobilite> optionalMbl = mobiliteRepository.findAll().stream().filter(d -> d.equals(mobilite)).findFirst();
            if (optionalMbl.isPresent()) {
                Mobilite existMbl = optionalMbl.get();
                if (existMbl != null) return existMbl;
                else return mobiliteRepository.save(mobilite);
            } else {
                return mobiliteRepository.save(mobilite);
            }
        }
        catch(Exception exception)
        {
            System.out.println("Erreur createMobilite - MobiliteService : " + exception);
            return null;
        }
    }


    @Transactional
    public Mobilite updateMobilite(Mobilite oldMbl, Mobilite newMbl)
    {
        try
        {
            if (oldMbl.getCandidats().size() <= 1) {
                this.deleteMobilite(oldMbl.getIdMobilite());
            }
            Optional<Mobilite> optionalMbl = mobiliteRepository.findAll().stream().filter(d -> d.equals(newMbl)).findFirst();
            if (optionalMbl.isPresent()) {
                Mobilite existMbl = optionalMbl.get();
                if (existMbl != null) return existMbl;
            }
            return mobiliteRepository.save(newMbl);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur updateMobilite - MobiliteService : " + exception);
            return null;
        }
    }


    public void deleteMobilite(int idMobilite)
    {
        try
        {
            mobiliteRepository.deleteById(idMobilite);
            System.out.println("idMbl deleted : " + idMobilite);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deleteMobilite - MobiliteService : " + exception);
        }
    }


    public Optional<Mobilite> findMobiliteById(int idMobilite)
    {
        try
        {
            return mobiliteRepository.findById(idMobilite);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findMobiliteById - MobiliteService : " + exception);
            return null;
        }
    }


    public Optional<Mobilite> findMobiliteByZone(Integer zone)
    {
        try
        {
            return mobiliteRepository.findByZone(zone);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur findMobiliteByZone - MobiliteService : " + exception);
            return null;
        }
    }
}
