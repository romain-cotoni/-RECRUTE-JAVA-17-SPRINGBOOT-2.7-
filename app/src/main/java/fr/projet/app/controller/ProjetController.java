package fr.projet.app.controller;

import fr.projet.app.model.Projet;
import fr.projet.app.service.ProjetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true", maxAge=3600)
@RestController
public class ProjetController 
{
    private ProjetService projetService;

    public ProjetController(ProjetService projetService)
    {
        this.projetService = projetService;
    }

    @GetMapping("projets")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Projet> getAllProjets()
    {
        return projetService.findAllProjets();
    }

    @GetMapping("projet/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Projet getProjet(@PathVariable("id") int id)
    {
        return projetService.getProjet(id);
    }

    @PutMapping("projet/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Projet updateProjet(@PathVariable("id") int id, @RequestBody Projet projet) throws Exception
    {
        return projetService.updateProjet(id, projet);
    }

    @DeleteMapping("projet/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public void deleteProjet(@PathVariable("id") int id)
    {
        projetService.deleteProjet(id);
    }
}
