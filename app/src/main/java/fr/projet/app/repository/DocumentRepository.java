package fr.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.projet.app.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> 
{
	public void deleteById(int id);
}
