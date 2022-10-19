package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="experience")
public class Experience
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_xpr")
	private int IdExperience;
	
	@Column(name="debut_xpr")
	private Date debut;

	@Column(name="fin_xpr")
	private Date fin;

	@Column(name="lieu_xpr", length=50)
	@Length(max=50)
	private String lieu;

	@Column(name="info_xpr",length=500)
	@Length(max=500)
	private String info;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cdt")
	@JsonIgnore
	private Candidat candidat;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_msn", nullable = false)
	private Mission mission;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_etp")
	private Entreprise entreprise;

	public Experience()
	{

	}

	public Experience(Date debut, Date fin, String lieu, String info, Candidat candidat, Mission mission, Entreprise entreprise)
	{
		this.debut      = debut;
		this.fin        = fin;
		this.lieu       = lieu;
		this.info       = info;
		this.candidat   = candidat;
		this.mission    = mission;
		this.entreprise = entreprise;
	}

	public int getIdExperience() {
		return IdExperience;
	}


	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	/*@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Experience that = (Experience) o;
		return Objects.equals(debut, that.debut) && Objects.equals(fin, that.fin) && Objects.equals(lieu, that.lieu) && Objects.equals(info, that.info) && Objects.equals(candidat, that.candidat) && Objects.equals(mission, that.mission) && Objects.equals(entreprise, that.entreprise);
	}

	@Override
	public int hashCode() {
		return Objects.hash(debut, fin, lieu, info, candidat, mission, entreprise);
	}*/
}
