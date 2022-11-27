package fr.projet.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "entretien")
public class Entretien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEtr", nullable = false)
    private Integer idEntretien;

    @Column(name="date_etr")
    private Date date;

    @Column(name="lieu_etr", length=50)
    private String lieu;

    @Column(name="poste_etr", length=50)
    private String poste;

    @Column(name="contrat_etr")
    private Integer contrat;

    @Column(name="resume_etr", length=500)
    private String resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cdt")
    @JsonIgnore
    private Candidat candidat;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rct")
    private Recruteur recruteur;

    public Entretien()
    {

    }

    public Entretien(Date date, String lieu, String poste, Integer contrat, String resume, Candidat candidat, Recruteur recruteur)
    {
        this.date = date;
        this.lieu = lieu;
        this.poste = poste;
        this.contrat = contrat;
        this.resume = resume;
        this.candidat = candidat;
        this.recruteur = recruteur;
    }

    public Integer getIdEntretien() {
        return idEntretien;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Integer getContrat() {
        return contrat;
    }
    public void setContrat(Integer contrat) {
        this.contrat = contrat;
    }
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public Recruteur getRecruteur() {
        return recruteur;
    }

    public void setRecruteur(Recruteur recruteur) {
        this.recruteur = recruteur;
    }
}