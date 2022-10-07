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
@Table(name="entreprise")
public class Entreprise 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_etp")
	private int idEntreprise;
	
	@Column(name="raison_etp", nullable=false, length=150)
	@NotNull
	@Length(min=1, max=150)
	private String raisonSociale;
	
	@Column(name="siret_etp", nullable=true, length=150)
	@Length(min=1, max=150)
	private String siret;
	
	@Column(name="email_etp", nullable=true, length=350)
	@Length(min=1, max=350)
	private String email;
	
	@Column(name="mob_etp", nullable=true, length=50)
	@Length(min=1, max=50)
	private String mob;
	
	@Column(name="fixe_etp", nullable=true, length=50)
	@Length(min=1, max=50)
	private String fixe;
	
	@Column(name="adr_etp", nullable=true, length=150)
	@Length(min=1, max=150)
	private String adresse;

	@Column(name="adr2_etp", nullable=true, length=150)
	@Length(min=1, max=150)
	private String adresse2;
	

	public int getIdEntreprise() {
		return idEntreprise;
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public String getSiret() {
		return siret;
	}

	public String getEmail() {
		return email;
	}

	public String getMob() {
		return mob;
	}

	public String getFixe() {
		return fixe;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public void setFixe(String fixe) {
		this.fixe = fixe;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	
	
}
