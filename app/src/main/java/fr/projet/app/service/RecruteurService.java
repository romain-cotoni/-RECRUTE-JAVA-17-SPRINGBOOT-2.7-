package fr.projet.app.service;

import fr.projet.app.model.Recruteur;
import fr.projet.app.repository.RecruteurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RecruteurService
{
    private RecruteurRepository recruteurRepository;

    public RecruteurService(RecruteurRepository recruteurRepository)
    {
        this.recruteurRepository = recruteurRepository;
    }


    @Transactional
    public Recruteur createRecruteur(Recruteur recruteur)
    {
        Optional<Recruteur> optionalRct = recruteurRepository.findAll().stream().filter(d -> d.equals(recruteur)).findFirst();
        if(optionalRct.isPresent())
        {
            Recruteur existRct = optionalRct.get();
            if(existRct != null) return existRct;
            else return recruteurRepository.save(recruteur);
        }
        else
        {
            return recruteurRepository.save(recruteur);
        }
    }


    @Transactional
    public Recruteur updateRecruteur(Recruteur oldRct, Recruteur newRct)
    {
        if(oldRct.getEntretiens().size() <= 1)
        {
            this.deleteRecruteur(oldRct.getIdRecruteur());
        }
        Optional<Recruteur> optionalRct = recruteurRepository.findAll().stream().filter(d -> d.equals(newRct)).findFirst();
        if(optionalRct.isPresent())
        {
            Recruteur existRct = optionalRct.get();
            if(existRct != null) return existRct;
        }
        return recruteurRepository.save(newRct);
    }


    public void deleteRecruteur(int idRecruteur)
    {
        recruteurRepository.deleteById(idRecruteur);
        System.out.println("idRct deleted : "+idRecruteur);
    }

    public Optional<Recruteur> findRecruteurById(int idRecruteur)
    {
        return recruteurRepository.findById(idRecruteur);
    }
}
