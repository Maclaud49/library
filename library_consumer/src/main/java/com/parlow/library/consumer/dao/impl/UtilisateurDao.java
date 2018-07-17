package com.parlow.library.consumer.dao.impl;

import com.parlow.library.consumer.dao.contract.UtilisateurDaoInterface;
import com.parlow.library.model.bean.UtilisateurEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UtilisateurDao implements UtilisateurDaoInterface<UtilisateurEntity> {

    private Session currentSession;

    private Transaction currentTransaction;

    public UtilisateurDao() {

    }


    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
    public void closeCurrentSession() {
        currentSession.close();
    }
    
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
    
    public Session getCurrentSession() {
        return currentSession;
    }
    
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
    
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    
    public void persist(UtilisateurEntity entity) {
        getCurrentSession().save(entity);
    }

    public void update(UtilisateurEntity entity) {
        getCurrentSession().update(entity);
    }
    
    public UtilisateurEntity findById(int id) {
        UtilisateurEntity UtilisateurEntity = (UtilisateurEntity) getCurrentSession().get(UtilisateurEntity.class, id);
        return UtilisateurEntity;
    }
    
    public void delete(UtilisateurEntity entity) {
        getCurrentSession().delete(entity);
    }
    
    @SuppressWarnings("unchecked")
    public List<UtilisateurEntity> findAll() {
        List<UtilisateurEntity> UtilisateurEntitys = (List<UtilisateurEntity>) getCurrentSession().createQuery("from UtilisateurEntity").list();
        return UtilisateurEntitys;
    }

    @Override
    public UtilisateurEntity findMember(String email, String mdp) {

            CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<UtilisateurEntity> criteria = builder.createQuery( UtilisateurEntity.class );
            Root<UtilisateurEntity> root = criteria.from(UtilisateurEntity.class);

            criteria.where(
                    builder.and(
                            builder.equal(root.get("email"), email),
                            builder.equal(root.get("mdp"), mdp)
                    ));

            UtilisateurEntity utilisateur = getCurrentSession().createQuery(criteria).getSingleResult();

        return utilisateur;

    }

    @Override
    public UtilisateurEntity findByEmail(String email) {

        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<UtilisateurEntity> criteria = builder.createQuery( UtilisateurEntity.class );
        Root<UtilisateurEntity> root = criteria.from(UtilisateurEntity.class);

        criteria.where(
                builder.and(
                        builder.equal(root.get("email"), email)
                ));

        UtilisateurEntity utilisateur = getCurrentSession().createQuery(criteria).getSingleResult();
        //Todo remplacer par list pour éviter erreur si null

        return utilisateur;

    }





}
