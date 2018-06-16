package com.parlow.library.consumer.dao;

import com.parlow.library.model.UtilisateurEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UtilisateurDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void enregistrement(String pseudo,String mdp, String email, String profil ){
        Session session = sessionFactory.openSession();

        UtilisateurEntity user = new UtilisateurEntity();
        user.setPseudo(pseudo);
        user.setEmail("email");
        user.setMdp("dualcam");
        user.setProfil("Reussite");
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }
}
