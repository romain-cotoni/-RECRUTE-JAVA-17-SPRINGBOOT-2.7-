package fr.projet.app.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "candidat")	
public class Candidat 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_cdt")
	private int idCandidat;
	
	@Column(name="nom_cdt", nullable=false, length=50)
	@NotNull
	@Length(min=1, max = 50)
	private String nom;
	
	@Column(name="prenom_cdt", nullable=true, length=50)
	@Length(max = 50)
	private String prenom;
	
	@Column(name="ne_cdt", nullable=true, length=50)
	private Date naissance;
	
	@Column(name="email_cdt", nullable=true, length=350)
	@Email
	private String email;
	
	@Column(name="fixe_cdt", nullable=true, length=50)
	@Length(max = 50)
	private String fixe;
	
	@Column(name="mob_cdt", nullable=true, length=50)
	@Length(max = 50)
	private String mob;
	
	@Column(name="adrs_cdt", nullable=true, length=50)
	@Length(max = 150)
	private String adresse;
	
	@Column(name="adrs2_cdt", nullable=true, length=50)
	@Length(max = 150)
	private String adresse2;
	
	@Column(name="salaire_cdt", nullable=true)
	private Integer salaire;
	
	@Column(name="marital_cdt", nullable=true)
	private Byte maritalStatus;
	
	@Column(name="handi_cdt", nullable=true)
	private Boolean handicape;
	
	@Column(name="permis_cdt", nullable=true)
	private Boolean permis;
	
	@Column(name="vehicule_cdt", nullable=true)
	private Boolean vehicule;

	@Column(name="teletrvl_cdt", nullable=true)
	private Boolean teletravail;
	
	@Column(name="dispo_cdt", nullable=true)
	private Boolean disponible;
	
	@Column(name="info_cdt", nullable=true, length=500)
	private String info;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	@JsonIgnore
	private Set<Education> educations = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	@JsonIgnore
	private Set<Experience> experiences = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	@JsonIgnore
	private Set<Document> documents = new HashSet<Document>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_vil", nullable = true)
	private Ville ville;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pys", nullable = true)
	private Pays pays;

	public Candidat()
	{

	}

	public int getIdCandidat() {
		 return idCandidat;
	 }
	 
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getNaissance() {
		return naissance;
	}

	public String getEmail() {
		return email;
	}

	public String getFixe() {
		return fixe;
	}

	public String getMob() {
		return mob;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFixe(String fixe) {
		this.fixe = fixe;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public Integer getSalaire() {
		return salaire;
	}

	public void setSalaire(Integer salaire) {
		this.salaire = salaire;
	}
	
	public Byte getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(Byte maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public Boolean getPermis() {
		return permis;
	}

	public void setPermis(Boolean permis) {
		this.permis = permis;
	}
	
	public Boolean isPermis() {
		return permis;
	}
	
	public Boolean getVehicule() {
		return vehicule;
	}

	public void setVehicule(Boolean vehicule) {
		this.vehicule = vehicule;
	}

	public Boolean isVehicule() {
		return vehicule;
	}
	
	public Boolean getHandicape() {
		return handicape;
	}
	
	public void setHandicape(Boolean handicape) {
		this.handicape = handicape;
	}

	public Boolean isHandicape() {
		return handicape;
	}
	
	public Boolean getTeletravail() {
		return teletravail;
	}

	public void setTeletravail(Boolean teletravail) {
		this.teletravail = teletravail;
	}

	public Boolean isTeletravail() {
		return teletravail;
	}
	
	public Boolean getDisponible() {
		return disponible;
	}
	
	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean isDisponible() {
		return disponible;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set<Education> getEducations() {
		return educations;
	}
	
	public void setEducations(Set<Education> educations) 
	{
		this.educations = educations; 
	}
	
	public void addEducation(Education education)
	{
		this.educations.add(education);
	}
	
	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}
	
	public void addExperience(Experience experience)
	{
		this.experiences.add(experience);
	}


	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}
	
	public void addDocument(Document document)
	{
		this.documents.add(document);
	}
}
