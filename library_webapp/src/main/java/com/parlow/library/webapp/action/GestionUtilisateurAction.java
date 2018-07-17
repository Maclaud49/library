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
            this.utilisateur.setPseudo(premiereLettreMaj(utilisateur.getPseudo()));
            this.utilisateur.setEmail(premiereLettreMaj(utilisateur.getEmail()));
            this.utilisateur.setMdp(premiereLettreMaj(utilisateur.getMdp()));
            this.utilisateur.setProfil(premiereLettreMaj(utilisateur.getProfil()));
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


    @Override
    public void validate() {
        if (utilisateur != null) {
            if (!StringUtils.isAllEmpty(utilisateur.getPseudo(), utilisateur.getEmail())) {
                logger.info("Conditions remplies pour étape validation");
                if (utilisateur.getPseudo().length() < 2 || utilisateur.getPseudo().length() > 30) {
                    addFieldError("registerPseudo", "Votre pseudo doit faire entre 2 et 30 caratères ");
                }
                if (utilisateur.getEmail().length() < 2 || utilisateur.getEmail().length() > 60) {
                    addFieldError("registerEmail", "Votre email doit faire entre 2 et 60 caratères ");
                }
            } else {
                logger.info("Conditions non remplies pour étape validation");
            }
        }
    }

}
