package fr.projet.app.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import fr.projet.app.model.Document;
import fr.projet.app.service.DocumentService;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class DocumentController 
{
	@Autowired
	DocumentService documentService;
	
	@PutMapping("document/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public Document updateDocument(@PathVariable("id") int id, @RequestBody Document document)
	{
		return documentService.updateDocument(id, document);
	}
	
	@DeleteMapping("document/{id}")
	@RolesAllowed({ "vador", "yoda", "jedi", "padawan" })
	public void deleteDocument(@PathVariable("id") int id)
	{
		documentService.deleteDocument(id);
	}
}
