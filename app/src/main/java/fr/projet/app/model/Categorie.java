package fr.projet.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="categorie")
public class Categorie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cat")
	private int idCategorie;
	
	@Column(name="label_cat", nullable=false, length=50)
	@NotNull
	@Length(min = 2, max = 50)
	private String label;

	@OneToMany(mappedBy = "categorie", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Document> documents = new HashSet<Document>();

	public Categorie()
	{

	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Categorie categorie = (Categorie) o;
		return Objects.equals(label, categorie.label);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(label);
	}
}
