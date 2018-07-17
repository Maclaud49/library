package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.model.bean.UtilisateurEntity;
import com.parlow.library.webapp.client.AuthI;
import com.parlow.library.webapp.client.AuthImplService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {

    // ==================== Attributs ====================

    private Map<String, Object> session;
    private HttpServletRequest servletRequest;
    protected HttpServletResponse servletResponse;

    AuthImplService authService = new AuthImplService();
    AuthI auth = authService.getAuthImplPort();

    private static final Logger logger = LogManager.getLogger(UserAction.class);

    // ----- Paramètres en entrée
    private String email;
    private String mdp;
    private String mdp2;
    private String pseudo;
    private String profil;
    private boolean remember;

    // ==================== Méthodes ====================

    /**
     * Action permettant l'enregistrement d'un utilisateur
     * @return input / success
     */
    public String doRegister() {
        logger.info("doRegister");
        String vResult = ActionSupport.INPUT;
        if (!StringUtils.isAllEmpty(pseudo, email, mdp)) {
            String result = auth.register(pseudo, mdp, email, profil);
            logger.debug(result);
            if(result.equals("Enregistré")) {
                vResult = ActionSupport.SUCCESS;
            }
            else{
                addFieldError("result", result);
            }
        }

        return vResult;
    }

    /**
     * Action permettant la connexion d'un utilisateur
     * @return input / success
     */
    public String doLogin() {

        logger.info("doLogin");

        String vResult = ActionSupport.INPUT;

        if (!StringUtils.isAllEmpty(email, mdp)) {

            String result = auth.connexion(email,mdp);
            logger.debug("result " + result);

            if(!result.equals("Email et password ne sont pas reconnus")) {

                String[] str = result.split(";");
                UtilisateurEntity user = new UtilisateurEntity();
                user.setId(Integer.parseInt(str[0]));
                user.setPseudo(str[1]);
                user.setMdp(str[2]);
                user.setEmail(str[3]);
                user.setProfil(str[4]);

                this.session.put("library_user", user);
                if(remember){
                    rememberMe(user.getId());
                }
                vResult = ActionSupport.SUCCESS;
            }
            else{
                addFieldError("result", result);
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
        logger.info("deconnexion");
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



    // ==================== Méthodes utilitaire ====================

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
    public void setServletResponse(HttpServletResponse servletResponse) {
        this.servletResponse = servletResponse;
    }

    @Override
    public void validate() {

        //Check for registerAction
        if (pseudo != null) {

            if (pseudo.length() < 2 || pseudo.length() >30) {
                addFieldError("registerPseudo", "Votre nom doit faire entre 2 et 30 caratères ");
            }
            if (email.length() < 5 || email.length() >60) {
                addFieldError("registerEmail", "Votre email doit faire entre 5 et 60 caratères ");
            }
            if (mdp.length() < 6 || mdp.length() >60) {
                addFieldError("registerPassword", "Votre mot de passe doit faire entre 6 et 60 caratères ");
            }

            if (!auth.findEmail(email).equals("No user with this email")){
                addFieldError("registerEmail", "Cet email est déjà utilisé ");
            }

            if(!mdp.equals(mdp2)){
                addFieldError("registerPassword2", "Les mots de passe ne sont pas identiques ");
            }

        }
    }


    // ==================== Getters/Setters ====================


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

    public String getMdp2() {
        return mdp2;
    }

    public void setMdp2(String mdp2) {
        this.mdp2 = mdp2;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
