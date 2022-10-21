package fr.projet.app.controller;

import fr.projet.app.model.Langue;
import fr.projet.app.service.LangueService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class LangueController
{
    private LangueService langueService;

    public LangueController(LangueService langueService)
    {
        this.langueService = langueService;
    }

    @GetMapping("langues")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Langue> getAllLangues()
    {
        return langueService.findAllLangues();
    }

    @GetMapping("langue/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Langue getLangue(@PathVariable("id") int id)
    {
        return langueService.getLangue(id);
    }

    @PutMapping("langue/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Langue updateLangue(@PathVariable("id") int id, @RequestBody Langue langue) throws Exception
    {
        return langueService.updateLangue(id, langue);
    }

    @DeleteMapping("langue/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public void deleteLangue(@PathVariable("id") int id)
    {
        langueService.deleteLangue(id);
    }
}
