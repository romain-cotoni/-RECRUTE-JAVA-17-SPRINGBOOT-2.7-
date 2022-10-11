package fr.projet.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="mission")
public class Mission 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_msn")
	private int idMission;
	
	@Column(name="prof_msn", nullable=false, length=50)
	@NotNull
	@Length(min=1, max=50)
	private String profession;

	@OneToMany(mappedBy="mission", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Profil> profils = new HashSet<Profil>();

	@OneToMany(mappedBy="mission", fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<Experience> experiences = new HashSet<Experience>();

	public Mission()
	{

	}

	public Mission(String profession, Set<Profil> profils, Set<Experience> experiences) {
		this.profession = profession;
		this.profils = profils;
		this.experiences = experiences;
	}

	public int getIdMission() {
		return idMission;
	}


	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Set<Profil> getProfils() {
		return profils;
	}

	public void setProfils(Set<Profil> profils) {
		this.profils = profils;
	}

	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Mission mission = (Mission) o;
		return Objects.equals(profession, mission.profession);
	}

	@Override
	public int hashCode() {
		return Objects.hash(profession);
	}
}
