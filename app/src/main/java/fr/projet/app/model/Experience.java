package fr.projet.app.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="experience")
public class Experience 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_xpr")
	private int IdExperience;
	
	@Column(name="debut_xpr", nullable=true)
	private Date debut;

	@Column(name="fin_xpr", nullable=true)
	private Date fin;

	@Column(name="info_xpr", nullable=true, length=500)
	@Length(max=500)
	private String info;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_msn", nullable = false)
	@JsonIgnore
	private Mission mission;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "id_etp", nullable = true)
	@JsonIgnore
	private Entreprise entreprise;
	
	
	public Experience(Date debut, Date fin, @Length(max = 500) String info, Mission mission, Entreprise entreprise) 
	{
		this.debut = debut;
		this.fin = fin;
		this.info = info;
		this.mission = mission;
		this.entreprise = entreprise;
	}

	
	public int getIdExperience() {
		return IdExperience;
	}

	public Date getDebut() {
		return debut;
	}

	public Date getFin() {
		return fin;
	}

	public String getInfo() {
		return info;
	}

	public Mission getMission() {
		return mission;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	
	
}
