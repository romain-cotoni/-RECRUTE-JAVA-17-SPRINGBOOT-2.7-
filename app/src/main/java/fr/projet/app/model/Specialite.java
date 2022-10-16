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
@Table(name="specialite")
public class Specialite 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_spl")
	private int idSpecialite;
	
	@Column(name="label_spl", nullable=false, length=50)
	@NotNull
	@Length(min = 2, max = 50)
	private String label;
	
	@OneToMany(mappedBy = "specialite", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<Education> educations = new HashSet<Education>();
	

	public int getIdSpecialite() {
		return idSpecialite;
	}

	public String getLabel() {
		return label;
	}
	public Set<Education> getEducations() {
		return educations;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}

	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Specialite other = (Specialite) obj;
		return Objects.equals(label, other.label);
	}
}
