package fr.projet.app.service;

import fr.projet.app.model.Activite;
import fr.projet.app.repository.ActiviteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ActiviteService
{
    private ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository)
    {
        this.activiteRepository = activiteRepository;
    }

    @Transactional
    public Activite createActivite(Activite activite)
    {
        Optional<Activite> optionalAct = activiteRepository.findAll().stream().filter(d -> d.equals(activite)).findFirst();
        if(optionalAct.isPresent())
        {
            Activite existAct = optionalAct.get();
            if(existAct != null) return existAct;
            else return activiteRepository.save(activite);
        }
        else
        {
            return activiteRepository.save(activite);
        }
    }


    @Transactional
    public Activite updateActivite(Activite oldAct, Activite newAct)
    {
        if(oldAct.getProjets().size() <= 1)
        {
            this.deleteActivite(oldAct.getIdActivite());
        }
        Optional<Activite> optionalAct = activiteRepository.findAll().stream().filter(d -> d.equals(newAct)).findFirst();
        if(optionalAct.isPresent())
        {
            Activite existAct = optionalAct.get();
            if(existAct != null) return existAct;
        }
        return activiteRepository.save(newAct);
    }

    public void deleteActivite(int idActivite)
    {
        activiteRepository.deleteById(idActivite);
    }

    public Optional<Activite> findActiviteById(int idActivite)
    {
        return activiteRepository.findById(idActivite);
    }
}
