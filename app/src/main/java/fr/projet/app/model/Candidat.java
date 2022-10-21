package fr.projet.app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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
	//@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
	private String nom;
	
	@Column(name="prenom_cdt", length=50)
	@Length(max = 50)
	private String prenom;
	
	@Column(name="ne_cdt")
	private Date naissance;
	
	@Column(name="email_cdt", length=350)
	@Email
	private String email;
	
	@Column(name="fixe_cdt", length=50)
	@Length(max = 50)
	private String fixe;
	
	@Column(name="mob_cdt", length=50)
	@Length(max = 50)
	private String mob;
	
	@Column(name="adrs_cdt", length=50)
	@Length(max = 150)
	private String adresse;
	
	@Column(name="adrs2_cdt", length=50)
	@Length(max = 150)
	private String adresse2;
	
	@Column(name="salaire_cdt")
	private Integer salaire;
	
	@Column(name="marital_cdt")
	private Byte marital;
	
	@Column(name="handi_cdt")
	private Boolean handicape;
	
	@Column(name="permis_cdt")
	private Boolean permis;
	
	@Column(name="vehicule_cdt")
	private Boolean vehicule;

	@Column(name="teletrvl_cdt")
	private Boolean teletravail;
	
	@Column(name="dispo_cdt")
	private Boolean disponible;
	
	@Column(name="info_cdt", length=500)
	private String info;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Education> educations = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Experience> experiences = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Document> documents = new HashSet<Document>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Pseudo> pseudos = new HashSet<Pseudo>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Projet> projets = new HashSet<Projet>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_cdt")
	private Set<Entretien> entretiens = new HashSet<Entretien>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_vil")
	private Ville ville;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pys")
	private Pays pays;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_mbl")
	private Mobilite mobilite;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "candidat_competence",
			joinColumns        = { @JoinColumn(name = "id_cdt") },
			inverseJoinColumns = { @JoinColumn(name = "id_cpt") })
	private Set<Competence> competences = new HashSet<Competence>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "candidat_langue",
			joinColumns        = { @JoinColumn(name = "id_cdt") },
			inverseJoinColumns = { @JoinColumn(name = "id_lng") })
	private Set<Langue> langues = new HashSet<Langue>();


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
	
	public Byte getMarital() {
		return marital;
	}
	
	public void setMarital(Byte marital) {
		this.marital = marital;
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
	public Set<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}
	public Set<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	public Mobilite getMobilite() {
		return mobilite;
	}

	public void setMobilite(Mobilite mobilite) {
		this.mobilite = mobilite;
	}

	public Set<Pseudo> getPseudos() {
		return pseudos;
	}

	public void setPseudos(Set<Pseudo> pseudos) {
		this.pseudos = pseudos;
	}

	public Set<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Set<Projet> projets) {
		this.projets = projets;
	}

	public Set<Entretien> getEntretiens() {
		return entretiens;
	}

	public void setEntretiens(Set<Entretien> entretiens) {
		this.entretiens = entretiens;
	}

	public Set<Competence> getCompetences() {
		return competences;
	}

	public void setCompetences(Set<Competence> competences) {
		this.competences = competences;
	}

	public Set<Langue> getLangues() {
		return langues;
	}

	public void setLangues(Set<Langue> langues) {
		this.langues = langues;
	}




	public void addEducation(Education education)
	{
		this.educations.add(education);
	}

	public void addExperience(Experience experience)
	{
		this.experiences.add(experience);
	}

	public void addDocument(Document document)
	{
		this.documents.add(document);
	}

}
