package fr.projet.app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="profil")
public class Profil
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_prf")
    private int idProfil;

    @Column(name="nom_prf", length=50)
    @Length(max=50)
    private String nom;

    @Column(name="expmin_prf")
    private Integer expMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_etp")
    private Entreprise entreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_msn")
    private Mission mission;

    public Profil()
    {

    }

    public Profil(String nom, Integer expMin, Entreprise entreprise, Mission mission)
    {
        this.nom = nom;
        this.expMin = expMin;
        this.entreprise = entreprise;
        this.mission = mission;
    }

    public int getIdProfil() {
        return idProfil;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getExpMin() {
        return expMin;
    }

    public void setExpMin(Integer expMin) {
        this.expMin = expMin;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profil profil = (Profil) o;
        return Objects.equals(nom, profil.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
