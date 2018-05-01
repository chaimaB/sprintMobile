/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;



/**
 *
 * @author CHAIMA
 */
public class Evenement {
    private int idEvent ;
    public user user;
    public String nom ;
    private Date date;
    public String description;
    public String image ;
    public  String lieu;
    private  String prix;
    private int ticket ;
    private int particip ;

    public Evenement() {
    }

    public Evenement(String nom,Date date, String description, String image, String lieu, String prix, int ticket) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.image = image;
        this.lieu = lieu;
        this.prix = prix;
        this.ticket = ticket;
    }

   

    public Evenement(String nom, String description, String lieu) {
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
    }

    public Evenement(int idEvent, String nom, String description) {
        this.idEvent = idEvent;
        this.nom = nom;
        this.description = description;
    }

    public Evenement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    

    public Evenement(String nom, Date date, String description, String lieu, String prix, int ticket) {
        this.nom = nom;
        this.date = date;
        this.description = description;
        this.lieu = lieu;
        this.prix = prix;
        this.ticket = ticket;
    }
    

    public Evenement(String nom, String description, String lieu, String prix, int ticket) {
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.prix = prix;
        this.ticket = ticket;
    }
    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    
    

    public int getId_event() {
        return idEvent;
    }

    public void setId_event(int id_event) {
        this.idEvent = id_event;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public int getParticip() {
        return particip;
    }

    public void setParticip(int particip) {
        this.particip = particip;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", user=" + user + ", nom=" + nom + ", date=" + date + ", description=" + description + ", image=" + image + ", lieu=" + lieu + ", prix=" + prix + ", ticket=" + ticket + ", particip=" + particip + '}';
    }
    
    
}
