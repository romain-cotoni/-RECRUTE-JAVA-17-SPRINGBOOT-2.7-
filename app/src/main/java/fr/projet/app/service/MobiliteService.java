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
        Optional<Mobilite> optionalDpl = mobiliteRepository.findAll().stream().filter(d -> d.equals(mobilite)).findFirst();
        if(optionalDpl.isPresent())
        {
            Mobilite existDpl = optionalDpl.get();
            if(existDpl != null) return existDpl;
            else return mobiliteRepository.save(mobilite);
        }
        else
        {
            return mobiliteRepository.save(mobilite);
        }
    }


    @Transactional
    public Mobilite updateMobilite(Mobilite oldDpl, Mobilite newDpl)
    {
        if(oldDpl.getEducations().size() <= 1)
        {
            this.deleteMobilite(oldDpl.getIdMobilite());
        }
        Optional<Mobilite> optionalDpl = mobiliteRepository.findAll().stream().filter(d -> d.equals(newDpl)).findFirst();
        if(optionalDpl.isPresent())
        {
            Mobilite existDpl = optionalDpl.get();
            if(existDpl != null) return existDpl;
        }
        return mobiliteRepository.save(newDpl);
    }


    public void deleteMobilite(int idMobilite)
    {
        mobiliteRepository.deleteById(idMobilite);
        System.out.println("idDpl deleted : "+idMobilite);
    }

    public Optional<Mobilite> findMobiliteById(int idMobilite)
    {
        return mobiliteRepository.findById(idMobilite);
    }
}
