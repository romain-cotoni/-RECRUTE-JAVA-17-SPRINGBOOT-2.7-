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

	
	public Mission(@NotNull @Length(min = 1, max = 50) String profession) 
	{
		this.profession = profession;
	}

	public int getIdMission() {
		return idMission;
	}

	public String getNom() {
		return profession;
	}

	public void setNom(String profession) {
		this.profession = profession;
	}
	
	
}
