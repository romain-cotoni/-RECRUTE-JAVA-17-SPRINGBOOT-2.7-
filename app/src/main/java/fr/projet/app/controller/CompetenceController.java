package fr.projet.app.controller;

import fr.projet.app.model.Competence;
import fr.projet.app.service.CompetenceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class CompetenceController
{
    private CompetenceService competenceService;

    public CompetenceController(CompetenceService competenceService)
    {
        this.competenceService = competenceService;
    }

    @GetMapping("competences")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Competence> getAllCompetences()
    {
        return competenceService.findAllCompetences();
    }

    @GetMapping("competence/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Competence getCompetence(@PathVariable("id") int id)
    {
        return competenceService.getCompetence(id);
    }

    @PutMapping("competence/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Competence updateCompetence(@PathVariable("id") int id, @RequestBody Competence competence) throws Exception
    {
        return competenceService.updateCompetence(id, competence);
    }

    @DeleteMapping("competence/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public void deleteCompetence(@PathVariable("id") int id)
    {
        competenceService.deleteCompetence(id);
    }
}
