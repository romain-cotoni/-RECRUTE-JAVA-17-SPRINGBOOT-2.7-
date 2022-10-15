package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "recruteur")
public class Recruteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rct", nullable = false)
    private Integer idRecruteur;

    @Column(name="nom_rct", length=50)
    private String nom;

    @Column(name="prenom_rct", length=50)
    private String prenom;

    @Column(name="email_rct", length=350)
    private String email;

    @Column(name="mob_rct", length=50)
    private String mobile;

    @Column(name="fixe_rct", length=50)
    private String fixe;

    @OneToMany(mappedBy = "recruteur", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Entretien> entretiens = new HashSet<Entretien>();

    public Recruteur()
    {

    }

    public Recruteur(String nom, String prenom, String email, String mobile, String fixe, Set<Entretien> entretiens)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mobile = mobile;
        this.fixe = fixe;
        this.entretiens = entretiens;
    }

    public Integer getIdRecruteur() {
        return idRecruteur;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFixe() {
        return fixe;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public Set<Entretien> getEntretiens() {
        return entretiens;
    }

    public void setEntretiens(Set<Entretien> entretiens) {
        this.entretiens = entretiens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruteur recruteur = (Recruteur) o;
        return Objects.equals(nom, recruteur.nom) && Objects.equals(prenom, recruteur.prenom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom);
    }
}