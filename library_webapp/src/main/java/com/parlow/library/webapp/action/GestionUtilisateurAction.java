package com.parlow.library.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.parlow.library.business.manager.contract.ManagerFactory;
import com.parlow.library.model.bean.Utilisateur;
import com.parlow.library.model.exception.FunctionalException;
import com.parlow.library.model.exception.NotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Action de gestion des {@link Utilisateur}
 */
public class GestionUtilisateurAction extends ActionSupport {


    // ==================== Attributs ====================

    @Inject
    private ManagerFactory managerFactory;
    // ----- Paramètres en entrée
    private Integer utilisateurId;
    private String nom;
    private String prenom;


    // ----- Eléments en sortie
    private Utilisateur utilisateur;
    private List<String> listCotations;

    private static Logger logger = LogManager.getLogger();



    // ==================== Getters/Setters ====================


    public Integer getUtilisateurId() {
        return utilisateurId;
    }
    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public List<String> getListCotations() {
        if(this.listCotations==null){
            this.listCotations=selectCotation();
        }
        return listCotations;
    }
    public void setListCotations(List<String> cotations) {
        this.listCotations = cotations;
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
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    // ==================== Méthodes ====================
    /**
     * Action affichant les détails d'un {@link Utilisateur}
     * @return success / error
     */
    public String doDetail() {

        if (utilisateurId == null) {
            this.addActionError(getText("error.user.missing.id"));
        } else {
            try {
                utilisateur = managerFactory.getUtilisateurManager().findById(utilisateurId);
            } catch (NotFoundException pE) {
                this.addActionError(getText("error.user.notfound", Collections.singletonList(utilisateurId)));

            }
        }

        return (this.hasErrors()) ? ActionSupport.ERROR : ActionSupport.SUCCESS;
    }

    /**
     * Action permettant la modification d'un {@link Utilisateur}
     * @return success / error
     */
    public String doModifier() {

        String vResult = ActionSupport.INPUT;

        //vérification si affiche les données ou les update
        if (this.utilisateur != null) {
            this.utilisateur.setNom(premiereLettreMaj(utilisateur.getNom()));
            this.utilisateur.setPrenom(premiereLettreMaj(utilisateur.getPrenom()));
            this.utilisateur.setVille(premiereLettreMaj(utilisateur.getVille()));
            this.utilisateur.setPays(premiereLettreMaj(utilisateur.getPays()));
            try {
                managerFactory.getUtilisateurManager().update(utilisateur);
                vResult = ActionSupport.SUCCESS;
            } catch (FunctionalException e) {
                this.addActionError(getText("Un problème est survenu avec la base de données, réessayez plus tard"));
                vResult = ActionSupport.ERROR;
            }
        }
        else {

            try {
                this.utilisateur = managerFactory.getUtilisateurManager().findById(utilisateurId);
            } catch (NotFoundException pE) {
                this.addActionError(getText("error.user.notfound", Collections.singletonList(utilisateurId)));
            }
        }
        logger.info("result " + vResult);
        return vResult;
    }

    /**
     * Action permettant de supprimer {@link Utilisateur}
     * @return input / success / error
     */
    public String doDelete() {
        try {
            managerFactory.getUtilisateurManager().delete(utilisateurId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        String vResult = ActionSupport.SUCCESS;
        return vResult;
    }

    //transforme la premiere lettre d'un string en majuscule
    public String premiereLettreMaj(String str){

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public List<String> selectCotation(){
        List<String> list = new ArrayList<>();
        list =  Arrays.asList("3", "3a", "3b","3c","4","4a","4b","4c","5","5a","5b","5c","6","6a","6b","6c","7","7a","7b","7c","8","8a","8b","8c","9","9a","9b","9c");
        return list;
    }


    @Override
    public void validate() {
        if (utilisateur != null) {
            if (!StringUtils.isAllEmpty(utilisateur.getNom(), utilisateur.getPrenom())) {
                logger.info("Conditions remplies pour étape validation");
                if (utilisateur.getNom().length() < 2 || utilisateur.getNom().length() > 15) {
                    addFieldError("registerNom", "Votre nom doit faire entre 2 et 15 caratères ");
                }
                if (utilisateur.getPrenom().length() < 2 || utilisateur.getPrenom().length() > 15) {
                    addFieldError("registerPrenom", "Votre prénom doit faire entre 2 et 15 caratères ");
                }
            } else {
                logger.info("Conditions non remplies pour étape validation");
            }
        }
    }

}
