package com.parlow.library.webservice.service.impl;



import com.parlow.library.consumer.dao.impl.UtilisateurDao;
import com.parlow.library.model.bean.UtilisateurEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@WebService(endpointInterface = "com.parlow.library.webservice.service.contract.AuthI")
public class AuthImpl  {

    private static final Logger logger = LogManager.getLogger(AuthImpl.class);
    private UtilisateurDao utilisateurDao;

    public AuthImpl(){
        utilisateurDao = new UtilisateurDao();
    }


    @WebMethod
    public String enregistrement(String pseudo,String mdp, String email, String profil ){

        StringBuilder message = new StringBuilder();

        logger.debug("pseudotest " + pseudo);

        UtilisateurEntity utilisateur = new UtilisateurEntity(pseudo, mdp, email, profil);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UtilisateurEntity>> violations = validator.validate(utilisateur);

        for (ConstraintViolation<UtilisateurEntity> violation : violations) {
            logger.error(violation.getMessage());
            message.append("Le champ " + violation.getPropertyPath() + " " + violation.getMessage() +".");
        }

        if(violations.isEmpty()){
            try{
                this.persist(utilisateur);
                message.append("Utilisateur enregistré");
            } catch (Exception e){
                logger.debug(e.toString());
            }
        }

        return message.toString();
    }

    @WebMethod
    public String connecter(String email, String mdp) {

        StringBuilder message = new StringBuilder();
        UtilisateurEntity utilisateur;

        try {
            utilisateur = this.findMember(email, mdp);
            message.append("Vous etes " + utilisateur.getPseudo());
        }catch(Exception e) {
            logger.debug(e.toString());
        }
        return message.toString();
    }

    private void persist(UtilisateurEntity entity) {

        this.utilisateurDao.openCurrentSessionwithTransaction();
        this.utilisateurDao.persist(entity);
        this.utilisateurDao.closeCurrentSessionwithTransaction();
    }


    private void update(UtilisateurEntity entity) {
        this.utilisateurDao.openCurrentSessionwithTransaction();
        this.utilisateurDao.update(entity);
        this.utilisateurDao.closeCurrentSessionwithTransaction();
    }

    private UtilisateurEntity findById(int id) {
        this.utilisateurDao.openCurrentSession();
        UtilisateurEntity UtilisateurEntity = this.utilisateurDao.findById(id);
        this.utilisateurDao.closeCurrentSession();
        return UtilisateurEntity;
    }

    private void delete(int id) {
        this.utilisateurDao.openCurrentSessionwithTransaction();
        UtilisateurEntity UtilisateurEntity = this.utilisateurDao.findById(id);
        this.utilisateurDao.delete(UtilisateurEntity);
        this.utilisateurDao.closeCurrentSessionwithTransaction();
    }

    private List<UtilisateurEntity> findAll() {
        this.utilisateurDao.openCurrentSession();
        List<UtilisateurEntity> UtilisateurEntitys = this.utilisateurDao.findAll();
        this.utilisateurDao.closeCurrentSession();
        return UtilisateurEntitys;
    }
    private UtilisateurEntity findMember(String email, String mdp) {
        this.utilisateurDao.openCurrentSession();
        UtilisateurEntity UtilisateurEntity = this.utilisateurDao.findMember(email,mdp);
        this.utilisateurDao.closeCurrentSession();
        return UtilisateurEntity;
    }

}
