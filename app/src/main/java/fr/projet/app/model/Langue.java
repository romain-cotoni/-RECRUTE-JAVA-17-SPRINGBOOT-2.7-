package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "langue")
public class Langue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lng", nullable = false)
    private Integer idLangue;

    @Column(name="nom_lng")
    private String nom;

    @Column(name="niveau_lng")
    private Integer niveau;

    @Column(name="certif_lng")
    private Integer certification;

    @Column(name="info_lng")
    private String info;

    @ManyToMany(mappedBy = "langues")
    @JsonIgnore
    Set<Candidat> candidats;

    public Langue()
    {

    }

    public Langue(String nom, Integer niveau, Integer certification, String info, Set<Candidat> candidats)
    {
        this.nom = nom;
        this.niveau = niveau;
        this.certification = certification;
        this.info = info;
        this.candidats = candidats;
    }

    public Integer getIdLangue() {
        return idLangue;
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

    public Integer getCertification() {
        return certification;
    }

    public void setCertification(Integer certification) {
        this.certification = certification;
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
        Langue langue = (Langue) o;
        return Objects.equals(nom, langue.nom) && Objects.equals(niveau, langue.niveau) && Objects.equals(certification, langue.certification) && Objects.equals(info, langue.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, niveau, certification, info);
    }
}