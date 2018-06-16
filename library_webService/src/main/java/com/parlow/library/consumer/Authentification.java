package com.parlow.library.consumer;


import com.parlow.library.consumer.dao.HibernateUtil;
import com.parlow.library.consumer.dao.UtilisateurDao;
import com.parlow.library.model.UtilisateurEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.jws.WebService;

@WebService(serviceName="Auth",
        endpointInterface = "com.parlow.library.consumer.AuthI")
public class Authentification implements AuthI {


    @Override
    public String enregistrement(String pseudo,String mdp, String email, String profil ){

        UtilisateurDao utilisateur = new UtilisateurDao();
        utilisateur.enregistrement(pseudo,mdp,email,profil);

        return pseudo + " , vous voila enregistré";
    }

    public static void main(String[] args){

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();


        UtilisateurEntity user = new UtilisateurEntity();
        user.setPseudo("moi");
        user.setEmail("email");
        user.setMdp("dualcam");
        user.setProfil("admin");
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("fini");
    }

}
