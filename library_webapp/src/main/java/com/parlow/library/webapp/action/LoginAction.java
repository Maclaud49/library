package com.parlow.library.webapp.action;

import com.parlow.library.business.manager.contract.ManagerFactory;
import com.parlow.library.model.bean.Utilisateur;
import com.parlow.library.model.exception.NotFoundException;
import com.parlow.library.webapp.client.AuthI;
import com.parlow.library.webapp.client.AuthImplService;
import org.apache.commons.lang3.StringUtils;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * Action de gestion de la connexion/déconnexion d'un utilisateur
 */
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware, SessionAware {


    // ==================== Attributs ====================

    private static final Logger logger = LogManager.getLogger(LoginAction.class);
    @Inject
    private ManagerFactory managerFactory;
    private Map<String, Object> session;
    private HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;

    // ----- Paramètres en entrée
    private String email;
    private String password;
    private boolean remember;


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
    public boolean isRemember() {
        return remember;
    }
    public void setRemember(boolean remember) {
        this.remember = remember;
    }


    // ==================== Méthodes ====================
    /**
     * Action permettant la connexion d'un utilisateur
     * @return input / success
     */
    public String doLogin() {


        String vResult = ActionSupport.INPUT;

        if (!StringUtils.isAllEmpty(email, password)) {

            AuthImplService authService = new AuthImplService();
            AuthI save = authService.getAuthImplPort();
            try {
                Utilisateur vUtilisateur
                        = managerFactory.getUtilisateurManager().login(email, password);
                // Ajout de l'utilisateur en session
                this.session.put("library_user", vUtilisateur);
                if(remember){
                    rememberMe(vUtilisateur.getId());
                }
                vResult = ActionSupport.SUCCESS;
            } catch (NotFoundException pEx) {
                this.addActionError(getText("error.login.incorrect"));
            }
        }
        return vResult;
    }


    /**
     * Action de déconnexion d'un utilisateur
     * @return success
     */
    public String doLogout() {
        // Suppression de l'utilisateur en session et du cookie
        logger.error("deconnexion");
        this.session.remove("library_user");
        this.servletRequest.getSession().invalidate();
        Cookie[] cookies = this.servletRequest.getCookies();
            for(int i=0;cookies!=null&&i<cookies.length;i++) {
                if (cookies[i].getName().equals("library_user")) {
                    cookies[i].setValue("");
                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    servletResponse.addCookie(cookies[i]);
                    logger.info("cookie supprimé" + cookies[i].getValue());
                }
        }

        return ActionSupport.SUCCESS;
    }

    public void rememberMe(int vUtilisateurId) {

        Cookie cookie = new Cookie("library_user", String.format("%d", vUtilisateurId));
        cookie.setMaxAge(60 * 60 * 24 * 365);
        System.out.println("cookie is set");
        this.servletResponse.addCookie(cookie);
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
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }
}
