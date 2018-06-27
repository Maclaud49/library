package com.parlow.library.consumer.dao.impl;


import com.parlow.library.consumer.dao.contract.UtilisateurDao;
import com.parlow.library.model.bean.UtilisateurEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


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

        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        UtilisateurEntity utilisateur = null;

        try{
            tx = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UtilisateurEntity> criteria = builder.createQuery( UtilisateurEntity.class );
            Root<UtilisateurEntity> root = criteria.from(UtilisateurEntity.class);

            criteria.where(
                    builder.and(
                            builder.equal(root.get("email"), email),
                            builder.equal(root.get("mdp"), mdp)
                    ));

            utilisateur = session.createQuery(criteria).getSingleResult();
            tx.commit();
        } catch (NoResultException e) {
            logger.debug("No result Exception");
            throw new NoResultException("No result Exception");
        } catch (HibernateException e) {
            logger.debug("Hibernate Exception");
            throw new HibernateException("Hibernate Exception");
        } finally {
            session.close();
        }

        return utilisateur;

    }
}
