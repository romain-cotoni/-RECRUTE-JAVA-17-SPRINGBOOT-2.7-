package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "competence")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cpt", nullable = false)
    private Integer idCompetence;

    @Column(name="nom_cpt")
    private String nom;

    @Column(name="niveau_cpt")
    private Integer niveau;

    @Column(name="type_cpt")
    private Integer type;

    @Column(name="info_cpt")
    private String info;

    @ManyToMany(mappedBy = "competences")
    @JsonIgnore
    Set<Candidat> candidats;

    public Competence()
    {

    }

    public Competence(String nom, Integer niveau, Integer type, String info)
    {
        this.nom = nom;
        this.niveau = niveau;
        this.type = type;
        this.info = info;
    }

    public Competence(String nom, Integer niveau, Integer type, String info, Set<Candidat> candidats)
    {
        this.nom = nom;
        this.niveau = niveau;
        this.type = type;
        this.info = info;
        this.candidats = candidats;
    }

    public Integer getIdCompetence() {
        return idCompetence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competence that = (Competence) o;
        return Objects.equals(nom, that.nom) && Objects.equals(niveau, that.niveau) && Objects.equals(type, that.type) && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, niveau, type, info);
    }
}