package com.parlow.library.model.bean;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "utilisateur", schema = "public", catalog = "library")
public class UtilisateurEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String pseudo;
    private String mdp;
    private String email;
    private String profil;
    private final static String  PROFILMEMBRE = "MEMBRE";

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @NotNull
    @Size(min=2, max=30)
    @Column(name = "pseudo", nullable = false, length = 30)
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Basic
    @NotNull
    @Size(min=6, max=60)
    @Column(name = "mdp", nullable = false, length = 60)
    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Basic
    @NotNull
    @Size(max=60)
    @Email
    @Column(name = "email", nullable = false, length = 60)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @NotNull
    @Pattern(regexp="Membre|Admin")
    @Size(max=20)
    @Column(name = "profil", nullable = false, length = 20)
    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        if(profil.equals("")){
            this.profil = PROFILMEMBRE;
        }
        else{
            this.profil = profil;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UtilisateurEntity that = (UtilisateurEntity) o;

        if (id != that.id) return false;
        if (pseudo != null ? !pseudo.equals(that.pseudo) : that.pseudo != null) return false;
        if (mdp != null ? !mdp.equals(that.mdp) : that.mdp != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (profil != null ? !profil.equals(that.profil) : that.profil != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (pseudo != null ? pseudo.hashCode() : 0);
        result = 31 * result + (mdp != null ? mdp.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (profil != null ? profil.hashCode() : 0);
        return result;
    }

    public UtilisateurEntity(){}

    public UtilisateurEntity(String pseudo, String mdp, String email, String profil){
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.email = email;
        this.profil = profil;
    }

    public UtilisateurEntity(String email, String mdp){
        this.email = email;
        this.mdp = mdp;
    }
}
