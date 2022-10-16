package fr.projet.app.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="diplome")
public class Diplome
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_dpl")
	private int idDiplome;
	
	@Column(name="label_dpl", nullable=false, length=50)
	@NotNull
	@Length(min = 2, max = 50)
	private String label;
	
	@Column(name="niveau_dpl")
	private int niveau;
	
	@OneToMany(mappedBy = "diplome", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Education> educations = new HashSet<Education>();
	

	public int getIdDiplome() {
		return idDiplome;
	}

	public String getLabel() {
		return label;
	}

	public int getNiveau() {
		return niveau;
	}

	public Set<Education> getEducations() {
		return educations;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label/*, niveau*/);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Diplome other = (Diplome) obj;
		return Objects.equals(label, other.label) /*&& niveau == other.niveau*/;
	}
}
