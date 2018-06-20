package com.parlow.library.consumer.dao.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    
    private static SessionFactory sessionFactory;


    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().
                    configure("hibernate.cfg.xml").buildSessionFactory();

            return sessionFactory;
        }
        catch (Throwable ex) {
           
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
