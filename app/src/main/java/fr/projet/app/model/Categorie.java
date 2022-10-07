package fr.projet.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

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

	public int getIdCategorie() {
		return idCategorie;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
