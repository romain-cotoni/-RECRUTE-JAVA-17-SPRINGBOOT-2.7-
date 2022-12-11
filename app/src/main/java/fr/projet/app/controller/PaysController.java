package fr.projet.app.controller;

import fr.projet.app.model.Pays;
import fr.projet.app.service.PaysService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200/", allowCredentials="true", maxAge=3600)
@RestController
public class PaysController
{
    private PaysService paysService;

    public PaysController(PaysService paysService)
    {
        this.paysService = paysService;
    }

    @GetMapping("/pays")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Pays> getAllPays()
    {
        return paysService.findAllPays();
    }
}
