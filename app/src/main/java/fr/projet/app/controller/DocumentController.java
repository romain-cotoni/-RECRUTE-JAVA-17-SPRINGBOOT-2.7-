package fr.projet.app.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.projet.app.model.Document;
import fr.projet.app.service.DocumentService;

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
