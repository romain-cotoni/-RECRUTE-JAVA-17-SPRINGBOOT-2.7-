package fr.projet.app.controller;

import fr.projet.app.model.Document;
import fr.projet.app.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", maxAge=3600)
@RestController
public class DocumentController 
{
	private DocumentService documentService;

	public DocumentController(DocumentService documentService)
	{
		this.documentService = documentService;
	}

	@PutMapping("document/{id}")
	@RolesAllowed({  "admin", "recruteur", "candidat"  })
	public Document updateDocument(@PathVariable("id") int id, @RequestBody Document document)
	{
		return documentService.updateDocument(id, document);
	}
	
	@DeleteMapping("document/{id}")
	@RolesAllowed({  "admin", "recruteur", "candidat"  })
	public void deleteDocument(@PathVariable("id") int id)
	{
		documentService.deleteDocument(id);
	}
}
