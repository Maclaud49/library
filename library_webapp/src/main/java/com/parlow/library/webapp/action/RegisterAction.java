package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.business.manager.contract.ManagerFactory;
import com.parlow.library.model.bean.UtilisateurEntity;
import com.parlow.library.webapp.client.AuthI;
import com.parlow.library.webapp.client.AuthImplService;
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
            AuthImplService authService = new AuthImplService();
            AuthI auth = authService.getAuthImplPort();

            UtilisateurEntity utilisateur = new UtilisateurEntity(pseudo, mdp, email, profil);
            String result = auth.enregistrement(pseudo, mdp, email, profil);
            logger.debug(result);
            if(result.equals("Utilisateur enregistré")) {
                vResult = ActionSupport.SUCCESS;
            }
            else{
                addFieldError("result", result);
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
            /*try {
                managerFactory.getUtilisateurManager().findByEmail(email);
            } catch (NotFoundException e) {
                userExist = false;
            }
            if(userExist){
                addFieldError("registerEmail", "Cet email est déjà utilisée ");
            }*/
            if(!mdp.equals(mdp2)){
                addFieldError("registerPassword2", "Les mots de passe ne sont pas identiques ");
            }

        }
    }

}
