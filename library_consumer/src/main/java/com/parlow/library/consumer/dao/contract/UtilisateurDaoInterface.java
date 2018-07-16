package com.parlow.library.consumer.dao.contract;

import com.parlow.library.model.bean.UtilisateurEntity;

import java.util.List;

public interface UtilisateurDaoInterface<T> {


    void persist(T entity);

    void update(T entity);

    T findById(int id);

    void delete(T entity);

    List<T> findAll();

    T findMember(String email, String mdp);
}
