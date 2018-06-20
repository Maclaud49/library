package com.parlow.library.consumer.dao.impl;


import com.parlow.library.consumer.dao.contract.UtilisateurDao;
import com.parlow.library.model.bean.UtilisateurEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Named;

@Named
public class UtilisateurDaoImpl implements UtilisateurDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final Logger logger = LogManager.getLogger(UtilisateurDaoImpl.class);

    @Override
    public void enregistrement(UtilisateurEntity utilisateur ){
        logger.debug("I'm here");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(utilisateur);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public UtilisateurEntity findMember(String email, String mdp) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query =session.createQuery(" from UtilisateurEntity  e where e.email='mickael@parlow-co.com'");

        logger.error("query " + query.getQueryString());
        UtilisateurEntity utilisateur = (UtilisateurEntity)query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return utilisateur;


    }
}
