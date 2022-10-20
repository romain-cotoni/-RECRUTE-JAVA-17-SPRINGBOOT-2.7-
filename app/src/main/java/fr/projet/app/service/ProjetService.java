package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Activite;
import fr.projet.app.model.Projet;
import fr.projet.app.repository.ProjetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetService 
{
    private ProjetRepository projetRepository;
    private ActiviteService activiteService;

    public ProjetService(ProjetRepository projetRepository, ActiviteService activiteService)
    {
        this.projetRepository = projetRepository;
        this.activiteService = activiteService;
    }

    public List<Projet> findAllProjets()
    {
        return projetRepository.findAll();
    }


    public Projet getProjet(int idProjet)
    {
        return projetRepository.findById(idProjet).get();
    }


    @Transactional
    public Projet createProjet(Candidat cdt, Projet prj) throws Exception
    {
        try
        {
            prj.setCandidat(cdt);
            Activite activite = activiteService.createActivite(prj.getActivite());
            if(activite != null) prj.setActivite(activite);

            return projetRepository.save(prj);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur ProjetService - createProjet() : " + exception);
        }
    }


    @Transactional
    public Projet updateProjet(int idProjet, Projet prj) throws Exception
    {
        try
        {
            Projet projet = projetRepository.findById(idProjet).orElseThrow();
            projet.setNom(prj.getNom());
            projet.setType(prj.getType());
            projet.setDebut(prj.getDebut());
            projet.setFin(prj.getFin());
            projet.setInfo(prj.getInfo());

            Activite oldAct = projet.getActivite();
            Activite rqtAct = prj.getActivite();
            if(!rqtAct.equals(oldAct))
            {
                projet.setActivite(null);
                Activite newAct = activiteService.updateActivite(oldAct, rqtAct);
                projet.setActivite(newAct);
            }

            return projetRepository.save(projet);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur ProjetService - updateProjet(): " + exception);
        }
    }


    @Transactional
    public void deleteProjet(int idProjet)
    {
        Optional<Projet> optionalPrj = projetRepository.findById(idProjet);
        if(optionalPrj.isPresent())
        {
            int idAct = optionalPrj.get().getActivite().getIdActivite();

            projetRepository.deleteById(idProjet);
            System.out.println("idProjet : "+idProjet);
            

            Optional<Activite> optionalAct = activiteService.findActiviteById(idAct);
            if(optionalAct.isPresent())
            {
                System.out.println("act Size : "+optionalAct.get().getProjets().size());
                if(optionalAct.get().getProjets().size() < 1)
                {
                    System.out.println("deleting : "+idAct);
                    activiteService.deleteActivite(idAct);
                }
            }
            System.out.println("resultat : "+idProjet +" - "+idAct);
        }
    }
}
