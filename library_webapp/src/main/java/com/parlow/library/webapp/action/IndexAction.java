package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.model.bean.UtilisateurEntity;
import com.parlow.library.webapp.client.AuthI;
import com.parlow.library.webapp.client.AuthImplService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class IndexAction extends ActionSupport implements ServletRequestAware, SessionAware {

    // ==================== Attributs ====================

    private static final Logger logger = LogManager.getLogger(IndexAction.class);
    private Map<String, Object> session;
    private HttpServletRequest servletRequest;
    private UtilisateurEntity user;


    // ==================== Getters/Setters ====================

    public UtilisateurEntity getUser() {
        return user;
    }
    public void setUser(UtilisateurEntity vuser) {
        this.user = vuser;
    }
   
    
    // ==================== Méthodes ====================
    /**
     * Action permettant la connexion d'un utilisateur
     * @return input / success
     */
    public String doIndex() {

        AuthImplService authService = new AuthImplService();
        AuthI auth = authService.getAuthImplPort();

        if (rememberMeLoad() >0){

            String result = auth.findUser(rememberMeLoad());
            logger.debug("result " + result);


            if(!result.equals("No user with this id")) {

                String[] str = result.split(";");
                UtilisateurEntity user = new UtilisateurEntity();
                user.setId(Integer.parseInt(str[0]));
                user.setPseudo(str[1]);
                user.setMdp(str[2]);
                user.setEmail(str[3]);
                user.setProfil(str[4]);

                this.session.put("library_user", user);
            }
        }
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
