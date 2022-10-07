package fr.projet.app.model;

public class CandidatRequest 
{
	private String prenom;

	private String nom;

	public CandidatRequest() {
		
	}

	public CandidatRequest(String prenom, String nom)
    {
		this.prenom = prenom;
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
