package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Pseudo;
import fr.projet.app.model.Reseau;
import fr.projet.app.repository.PseudoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PseudoService
{
    private PseudoRepository pseudoRepository;
    private ReseauService reseauService;

    public PseudoService(PseudoRepository pseudoRepository, ReseauService reseauService)
    {
        this.pseudoRepository = pseudoRepository;
        this.reseauService = reseauService;
    }
    public List<Pseudo> findAllPseudos()
    {
        return pseudoRepository.findAll();
    }
    public Pseudo getPseudo(int idPseudo)
    {
        return pseudoRepository.findById(idPseudo).get();
    }
    @Transactional
    public Pseudo createPseudo(Candidat cdt, Pseudo psd) throws Exception
    {
        try
        {
            psd.setCandidat(cdt);
            Reseau reseau = reseauService.createReseau(psd.getReseau());
            if(reseau != null) psd.setReseau(reseau);

            return pseudoRepository.save(psd);
        }
        catch(Exception exception)
        {
            System.out.println("Erreur createPseudo -  PseudoService : " + exception);
            return null;
        }
    }


    @Transactional
    public Pseudo updatePseudo(int idPseudo, Pseudo psd) throws Exception
    {
        try
        {
            Pseudo pseudo = pseudoRepository.findById(idPseudo).orElseThrow();
            pseudo.setPseudo(psd.getPseudo());

            Reseau oldRes = pseudo.getReseau();
            Reseau rqtRes = psd.getReseau();
            if(!rqtRes.equals(oldRes))
            {
                pseudo.setReseau(null);
                Reseau newRes = reseauService.updateReseau(oldRes, rqtRes);
                pseudo.setReseau(newRes);
            }

            return pseudoRepository.save(pseudo);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur PseudoService - updatePseudo(): " + exception);
        }
    }

    /*@Transactional
    public void deletePseudo(int idPseudo)
    {
        Optional<Pseudo> optionalPsd = pseudoRepository.findById(idPseudo);
        if(optionalPsd.isPresent())
        {
            int idRes = optionalPsd.get().getReseau().getIdReseau();
            pseudoRepository.deleteById(idPseudo);
            Optional<Reseau> optionalRes = reseauService.findReseauById(idRes);
            if(optionalRes.isPresent())
            {
                if(optionalRes.get().getPseudos().size() < 1)
                {
                    reseauService.deleteReseau(idRes);
                }
            }
        }
    }*/

    @Transactional
    public void deletePseudo(int idPseudo)
    {
        System.out.println("delete id : "+ idPseudo);
        pseudoRepository.deletePseudoById(idPseudo);
        /*try
        {
        }
        catch(Exception exception)
        {
            System.out.println("Erreur deletePseudo - PseudoService : " + exception);
        }*/
    }

    public Pseudo findByPseudoAndReseau(String pseudo, String reseau)
    {
        return pseudoRepository.findByPseudoAndReseau(pseudo, reseau);
    }

    public Optional<Pseudo> findById(int id)
    {
        return pseudoRepository.findById(id);
    }

    public Pseudo findByReseau(String reseau)
    {
        return pseudoRepository.findByReseau(reseau);
    }
}
