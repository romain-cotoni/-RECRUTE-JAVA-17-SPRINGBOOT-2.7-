package fr.projet.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.projet.app.model.Document;
import fr.projet.app.repository.DocumentRepository;

@Service
public class DocumentService
{
	@Autowired
	DocumentRepository documentRepository;
	
	public Document updateDocument(int id, Document documentRequest) 
	{
		Optional<Document> documentOptional = documentRepository.findById(id);
		Document document   = null;
		if(documentOptional.isPresent())
		{
			document = documentOptional.get();
			document.setLabel(documentRequest.getLabel());
			document.setPath(documentRequest.getPath());
			document.setCategorie(documentRequest.getCategorie());
			documentRepository.save(document);
		}
		return document;
	}

	public void deleteDocument(int id) 
	{
		documentRepository.deleteById(id);
	}

}
