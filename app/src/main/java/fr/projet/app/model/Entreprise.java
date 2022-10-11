package fr.projet.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

	@OneToMany(mappedBy = "entreprise", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Experience> experiences = new HashSet<Experience>();

	@OneToMany(mappedBy = "entreprise", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Document> documents = new HashSet<Document>();


	public Entreprise()
	{

	}

	public Entreprise(String raisonSociale, String siret, String email, String mob, String fixe, String adresse, String adresse2, Set<Experience> experiences, Set<Document> documents)
	{
		this.raisonSociale = raisonSociale;
		this.siret = siret;
		this.email = email;
		this.mob = mob;
		this.fixe = fixe;
		this.adresse = adresse;
		this.adresse2 = adresse2;
		this.experiences = experiences;
		this.documents = documents;
	}

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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Entreprise that = (Entreprise) o;
		return Objects.equals(raisonSociale, that.raisonSociale);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(raisonSociale);
	}
}
