package fr.projet.app.service;

import fr.projet.app.model.Document;
import fr.projet.app.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService
{
	private DocumentRepository documentRepository;

	public DocumentService(DocumentRepository documentRepository)
	{
		this.documentRepository = documentRepository;
	}

	
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
