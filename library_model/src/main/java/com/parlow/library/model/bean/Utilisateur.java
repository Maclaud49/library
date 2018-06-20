package com.parlow.library.model.bean;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    private static final long serialVersionUID = 1L;

    // ==================== Attributs ====================
    private Integer id;
    private String pseudo;
    private String email;
    private String mdp;
    private String profil;


    // ==================== Getters/Setters ==============

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }


    // ==================== Méthodes =====================

    /**
     * Constructeur.
     *
     *
     */
    public Utilisateur() {
        //Constructeur par default
    }

    @Override
    public String toString() {
        final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
        final String vSEP = ", ";
        vStB.append(" {")
                .append("id=").append(id)
                .append(vSEP).append("pseudo=\"").append(pseudo).append('"')
                .append(vSEP).append("email=\"").append(email).append('"')
                .append(vSEP).append("mdp=\"").append(mdp).append('"')
                .append(vSEP).append("profil=\"").append(profil).append('"')
                .append("}");
        return vStB.toString();
    }
}
