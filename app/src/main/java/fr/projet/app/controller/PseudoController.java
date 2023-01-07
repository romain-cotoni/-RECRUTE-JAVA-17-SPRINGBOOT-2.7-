package fr.projet.app.controller;

import fr.projet.app.model.Pseudo;
import fr.projet.app.service.PseudoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class PseudoController
{
    private PseudoService pseudoService;

    public PseudoController(PseudoService pseudoService)
    {
        this.pseudoService = pseudoService;
    }

    @GetMapping("pseudos")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public List<Pseudo> getAllPseudos()
    {
        return pseudoService.findAllPseudos();
    }

    @GetMapping("pseudo/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Pseudo getPseudo(@PathVariable("id") int id)
    {
        return pseudoService.getPseudo(id);
    }

    @PutMapping("pseudo/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public Pseudo updatePseudo(@PathVariable("id") int id, @RequestBody Pseudo pseudo) throws Exception
    {
        return pseudoService.updatePseudo(id, pseudo);
    }

    @DeleteMapping("pseudo/{id}")
    @RolesAllowed({ "admin", "recruteur", "candidat" })
    public void deletePseudo(@PathVariable("id") int id)  throws Exception
    {
        pseudoService.deletePseudo(id);
    }
}
