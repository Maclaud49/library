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
    private String mdp;
    private String mdp2;
    private String pseudo;
    private String profil;



    // ==================== Getters/Setters ====================
    public String getEmail() {
        return email;
    }
    public void setEmail(String pEmail) {
        email = pEmail;
    }
    public String getPseudo() {return pseudo;}
    public void setPseudo(String pseudo) {this.pseudo = pseudo;}

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getMdp2() {
        return mdp2;
    }

    public void setMdp2(String mdp2) {
        this.mdp2 = mdp2;
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
        if (!StringUtils.isAllEmpty(pseudo, email, mdp)) {
            Utilisateur vUtilisateur = new Utilisateur();
            vUtilisateur.setPseudo(premiereLettreMaj(pseudo));
            vUtilisateur.setEmail(email);
            vUtilisateur.setMdp(mdp);
            vUtilisateur.setProfil(profil);

            try {
                int id = managerFactory.getUtilisateurManager().insert(vUtilisateur);
                // Ajout de l'utilisateur en session
                logger.info("id" + id);
                vUtilisateur.setId(id);
                logger.info("pseudo" + vUtilisateur.getPseudo());
                this.session.put("library_user", vUtilisateur);
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
        if (!StringUtils.isAllEmpty(pseudo, email, mdp)) {
            boolean userExist = true;

            if (pseudo.length() < 2 || pseudo.length() >30) {
                addFieldError("registerPseudo", "Votre nom doit faire entre 2 et 30 caratères ");
            }
            if (email.length() < 5 || email.length() >60) {
                addFieldError("registerEmail", "Votre email doit faire entre 5 et 60 caratères ");
            }
            if (mdp.length() < 6 || mdp.length() >60) {
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
            if(!mdp.equals(mdp2)){
                addFieldError("registerPassword2", "Les mots de passe ne sont pas identiques ");
            }

        }
    }

}
