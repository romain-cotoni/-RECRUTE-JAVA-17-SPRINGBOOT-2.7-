package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "activite")
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_act", nullable = false)
    private Integer idActivite;

    @Column(name="nom_act", length=50)
    private String nom;

    @OneToMany(mappedBy = "activite", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Projet> projets = new HashSet<Projet>();

    public Activite()
    {

    }

    public Activite(String nom, Set<Projet> projets)
    {
        this.nom = nom;
        this.projets = projets;
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activite activite = (Activite) o;
        return Objects.equals(nom, activite.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}