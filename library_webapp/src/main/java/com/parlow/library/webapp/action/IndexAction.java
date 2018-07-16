package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.model.bean.Utilisateur;
import com.parlow.library.webapp.client.AuthI;
import com.parlow.library.webapp.client.AuthImplService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class IndexAction extends ActionSupport implements ServletRequestAware, SessionAware {

    // ==================== Attributs ====================

    private static final Logger logger = LogManager.getLogger(IndexAction.class);
    private Map<String, Object> session;
    private HttpServletRequest servletRequest;
    protected Utilisateur utilisateur;


    // ==================== Getters/Setters ====================

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur vutilisateur) {
        this.utilisateur = vutilisateur;
    }
   
    
    // ==================== Méthodes ====================
    /**
     * Action permettant la connexion d'un utilisateur
     * @return input / success
     */
    public String doIndex() {

        AuthImplService authService = new AuthImplService();
        AuthI save = authService.getAuthImplPort();

        //logger.info(save.enregistrement("Maclaud1", "DepuisIndex", "mac@parlow-co.com", "Admin"));


        /*if (rememberMeLoad() >0){
            try {
                this.utilisateur = managerFactory.getUtilisateurManager().findById(rememberMeLoad());
            } catch (NotFoundException pEx) {
                this.addActionError(getText("error.login.incorrect"));
            }
            this.session.put("library_user", this.utilisateur);
        }*/
        String vResult = ActionSupport.SUCCESS;
        return vResult;
    }

    public int rememberMeLoad() {
        int vUtilisateurId = 0;
        Cookie[] cookies = servletRequest.getCookies();
        for(int i=0;cookies!=null&&i<cookies.length;i++) {
            if (cookies[i].getName().equals("library_user")) {
                vUtilisateurId = Integer.parseInt(cookies[i].getValue());
            }
        }
        return vUtilisateurId;
    }



    @Override
    public void setSession(Map<String, Object> pSession) {
        this.session = pSession;
    }

    @Override
    public void setServletRequest(HttpServletRequest pRequest) {
        this.servletRequest = pRequest;
    }
}
