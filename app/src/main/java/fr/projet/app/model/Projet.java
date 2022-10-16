package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prj", nullable = false)
    private Integer idProjet;

    @Column(name="nom_prj", length=50)
    private String nom;

    @Column(name="type_prj", nullable=false , columnDefinition = "Integer default 1")
    private Integer type;


    @Column(name="debut_prj")
    private Date debut;

    @Column(name="fin_prj")
    private Date fin;

    @Column(name="info_prj", length=500)
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cdt")
    @JsonIgnore
    private Candidat candidat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_act")
    private Activite activite;

    public Projet()
    {

    }

    public Projet(String nom, Integer type, Date debut, Date fin, String info, Candidat candidat, Activite activite)
    {
        this.nom = nom;
        this.type = type;
        this.debut = debut;
        this.fin = fin;
        this.info = info;
        this.candidat = candidat;
        this.activite = activite;
    }

    public Integer getIdProjet() {
        return idProjet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
}