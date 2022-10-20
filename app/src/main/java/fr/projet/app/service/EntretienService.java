package fr.projet.app.service;

import fr.projet.app.model.Candidat;
import fr.projet.app.model.Entretien;
import fr.projet.app.model.Recruteur;
import fr.projet.app.repository.EntretienRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntretienService
{
    private EntretienRepository entretienRepository;
    private RecruteurService recruteurService;

    public EntretienService(EntretienRepository entretienRepository, RecruteurService recruteurService)
    {
        this.entretienRepository = entretienRepository;
        this.recruteurService = recruteurService;
    }



    public List<Entretien> findAllEntretiens()
    {
        return entretienRepository.findAll();
    }


    public Entretien getEntretien(int idEntretien)
    {
        return entretienRepository.findById(idEntretien).get();
    }


    @Transactional
    public Entretien createEntretien(Candidat cdt, Entretien etr) throws Exception
    {
        try
        {
            etr.setCandidat(cdt);
            Recruteur recruteur = recruteurService.createRecruteur(etr.getRecruteur());
            if(recruteur != null) etr.setRecruteur(recruteur);

            return entretienRepository.save(etr);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur EntretienService - createEntretien() : " + exception);
        }
    }


    @Transactional
    public Entretien updateEntretien(int idEntretien, Entretien etr) throws Exception
    {
        try
        {
            Entretien entretien = entretienRepository.findById(idEntretien).orElseThrow();
            entretien.setDate(etr.getDate());
            entretien.setLieu(etr.getLieu());
            entretien.setPoste(etr.getPoste());
            entretien.setContrat(etr.getContrat());
            entretien.setResume(etr.getResume());

            Recruteur oldRct = entretien.getRecruteur();
            Recruteur rqtRct = etr.getRecruteur();
            if(!rqtRct.equals(oldRct))
            {
                entretien.setRecruteur(null);
                Recruteur newRct = recruteurService.updateRecruteur(oldRct, rqtRct);
                entretien.setRecruteur(newRct);
            }

            return entretienRepository.save(entretien);
        }
        catch(Exception exception)
        {
            throw new Exception("Erreur EntretienService - updateEntretien(): " + exception);
        }
    }


    @Transactional
    public void deleteEntretien(int idEntretien)
    {
        Optional<Entretien> optionalEtr = entretienRepository.findById(idEntretien);
        if(optionalEtr.isPresent())
        {
            int idRct = optionalEtr.get().getRecruteur().getIdRecruteur();

            entretienRepository.deleteById(idEntretien);
            System.out.println("idEntretien : "+idEntretien);


            Optional<Recruteur> optionalRct = recruteurService.findRecruteurById(idRct);
            if(optionalRct.isPresent())
            {
                System.out.println("rct Size : "+optionalRct.get().getEntretiens().size());
                if(optionalRct.get().getEntretiens().size() < 1)
                {
                    System.out.println("deleting : "+idRct);
                    recruteurService.deleteRecruteur(idRct);
                }
            }
            System.out.println("resultat : "+idEntretien +" - "+idRct);
        }
    }
}
