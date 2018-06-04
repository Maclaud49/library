package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.business.manager.contract.ManagerFactory;
import com.parlow.library.model.bean.Utilisateur;
import com.parlow.library.model.exception.FunctionalException;
import com.parlow.library.model.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RegisterAction extends ActionSupport implements ServletRequestAware, SessionAware {

    // ==================== Attributs ====================
    @Inject
    private ManagerFactory managerFactory;
    private Map<String, Object> session;
    private HttpServletRequest servletRequest;

    private static final Logger logger = LogManager.getLogger(RegisterAction.class);

    // ----- Paramètres en entrée
    private String email;
    private String password;
    private String password2;
    private String nom;
    private String prenom;
    private String profil;



    // ==================== Getters/Setters ====================
    public String getEmail() {
        return email;
    }
    public void setEmail(String pEmail) {
        email = pEmail;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String pPassword) {
        password = pPassword;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
    // ==================== Méthodes ====================

    /**
     * Action permettant l'enregistrement d'un utilisateur
     * @return input / success
     */
    public String doRegister() {
        String vResult = ActionSupport.INPUT;
        if (!StringUtils.isAllEmpty(nom, prenom, email, password)) {
            Utilisateur vUtilisateur = new Utilisateur();
            vUtilisateur.setNom(premiereLettreMaj(nom));
            vUtilisateur.setPrenom(premiereLettreMaj(prenom));
            vUtilisateur.setEmail(email);
            vUtilisateur.setPassword(password);
            vUtilisateur.setProfil(profil);

            try {
                int id = managerFactory.getUtilisateurManager().insert(vUtilisateur);
                // Ajout de l'utilisateur en session
                logger.info("id" + id);
                vUtilisateur.setId(id);
                logger.info("nom" + vUtilisateur.getNom());
                this.session.put("escalade_user", vUtilisateur);
                vResult = ActionSupport.SUCCESS;
            } catch (FunctionalException e) {
                this.addActionError(getText("problème technique"));
            }
        }
        return vResult;
    }

    //transforme la premiere lettre d'un string en majuscule
    public String premiereLettreMaj(String str){

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public void setSession(Map<String, Object> pSession) {
        this.session = pSession;
    }

    @Override
    public void setServletRequest(HttpServletRequest pRequest) {
        this.servletRequest = pRequest;
    }

    @Override
    public void validate() {
        if (!StringUtils.isAllEmpty(nom, prenom, email, password)) {
            boolean userExist = true;

            if (nom.length() < 2 || nom.length() >15) {
                addFieldError("registerNom", "Votre nom doit faire entre 2 et 15 caratères ");
            }
            if (prenom.length() < 2 || prenom.length() >15) {
                addFieldError("registerPrenom", "Votre prénom doit faire entre 2 et 15 caratères ");
            }
            if (email.length() < 5 || email.length() >30) {
                addFieldError("registerEmail", "Votre email doit faire entre 5 et 30 caratères ");
            }
            if (password.length() < 6 || password.length() >60) {
                addFieldError("registerPassword", "Votre mot de passe doit faire entre 6 et 60 caratères ");
            }
            try {
                managerFactory.getUtilisateurManager().findByEmail(email);
            } catch (NotFoundException e) {
                userExist = false;
            }
            if(userExist){
                addFieldError("registerEmail", "Cet email est déjà utilisée ");
            }
            if(!password.equals(password2)){
                addFieldError("registerPassword2", "Les mots de passe ne sont pas identiques ");
            }

        }
    }

}
