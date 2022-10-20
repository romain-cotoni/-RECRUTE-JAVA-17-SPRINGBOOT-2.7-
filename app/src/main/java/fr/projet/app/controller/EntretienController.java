package fr.projet.app.controller;

import fr.projet.app.model.Entretien;
import fr.projet.app.service.EntretienService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class EntretienController
{
    private EntretienService entretienService;

    public EntretienController(EntretienService entretienService)
    {
        this.entretienService = entretienService;
    }

    @GetMapping("entretiens")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Entretien> getAllEntretiens()
    {
        return entretienService.findAllEntretiens();
    }

    @GetMapping("entretien/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Entretien getEntretien(@PathVariable("id") int id)
    {
        return entretienService.getEntretien(id);
    }

    @PutMapping("entretien/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Entretien updateEntretien(@PathVariable("id") int id, @RequestBody Entretien entretien) throws Exception
    {
        return entretienService.updateEntretien(id, entretien);
    }

    @DeleteMapping("entretien/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public void deleteEntretien(@PathVariable("id") int id)
    {
        entretienService.deleteEntretien(id);
    }
}
