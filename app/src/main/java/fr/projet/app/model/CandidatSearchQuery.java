package fr.projet.app.model;

import java.util.List;

public class CandidatSearchQuery
{
    private String prenom;
    private String nom;
    private Boolean teletravail;
    private Boolean handicape;
    private Boolean disponible;
    private String diplomes;
    private String specialites;
    private String missions;
    private String entreprises;
    private String competences;
    private String langues;
    private String pseudos;
    private String reseaux;
    private Integer mobilite;

    public CandidatSearchQuery()
    {

    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getTeletravail() {
        return teletravail;
    }

    public void setTeletravail(Boolean teletravail) {
        this.teletravail = teletravail;
    }

    public Boolean getHandicape() {
        return handicape;
    }

    public void setHandicape(Boolean handicape) {
        this.handicape = handicape;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getDiplomes() {
        return diplomes;
    }

    public void setDiplomes(String diplomes) {
        this.diplomes = diplomes;
    }

    public String getSpecialites() {
        return specialites;
    }

    public void setSpecialites(String specialites) {
        this.specialites = specialites;
    }

    public String getMissions() {
        return missions;
    }

    public void setMissions(String missions) {
        this.missions = missions;
    }

    public String getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(String entreprises) {
        this.entreprises = entreprises;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }

    public String getPseudos() {
        return pseudos;
    }

    public void setPseudos(String pseudos) {
        this.pseudos = pseudos;
    }

    public String getReseaux() {
        return reseaux;
    }

    public void setReseaux(String reseaux) {
        this.reseaux = reseaux;
    }

    public Integer getMobilite() {
        return mobilite;
    }

    public void setMobilite(Integer mobilite) {
        this.mobilite = mobilite;
    }
}
