package fr.projet.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name="document")
public class Document 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_doc")
	private int idDocument;
	
	@Column(name="label_doc", nullable=false, length=50)
	@NotNull
	@Length(min = 2, max = 50)
	private String label;
	
	@Column(name="path_doc", length=250)
	@Length(max = 250)
	private String path;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cat", nullable = false)
	private Categorie categorie;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cdt")
	private Candidat candidat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_etp")
	private Entreprise entreprise;

	public Document()
	{

	}

	public Document(String label, String path, Categorie categorie, Candidat candidat, Entreprise entreprise)
	{
		this.label = label;
		this.path = path;
		this.categorie = categorie;
		this.candidat = candidat;
		this.entreprise = entreprise;
	}

	public int getIdDocument() {
		return idDocument;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Document document = (Document) o;
		return Objects.equals(label, document.label) && Objects.equals(path, document.path) && Objects.equals(categorie, document.categorie);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, path, categorie);
	}
}
