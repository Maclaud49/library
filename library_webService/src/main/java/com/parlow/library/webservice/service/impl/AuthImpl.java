package com.parlow.library.webservice.service.impl;

import com.parlow.library.consumer.dao.contract.DaoFactory;
import com.parlow.library.consumer.dao.contract.UtilisateurDao;
import com.parlow.library.consumer.dao.impl.DaoFactoryImpl;
import com.parlow.library.consumer.dao.impl.UtilisateurDaoImpl;
import com.parlow.library.webservice.service.contract.AuthI;
import com.parlow.library.model.bean.UtilisateurEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


@WebService(serviceName="Auth",
        endpointInterface = "com.parlow.library.webservice.service.contract.AuthI")
@Named
public class AuthImpl extends AbstractDao implements AuthI {

    private static final Logger logger = LogManager.getLogger(AuthImpl.class);

    @Override
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
           UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
            utilisateurDao.enregistrement(utilisateur);

            //daoFactory.getUtilisateurDao().enregistrement(utilisateur);
            message.append("Utilisateur enregistré");
        }

        return message.toString();

    }

    @Override
    public String connecter(String email, String mdp) {

        StringBuilder message = new StringBuilder();

        UtilisateurEntity utilisateur;

        UtilisateurDao utilisateurDao = new UtilisateurDaoImpl();
        utilisateur = utilisateurDao.findMember(email,mdp);

        //utilisateur = daoFactory.getUtilisateurDao().findMember(email,mdp);

        message.append("Vous etes " + utilisateur.getPseudo());

        return message.toString();
    }

}
