package com.parlow.library.consumer.dao.contract;


import com.parlow.library.model.bean.UtilisateurEntity;

public interface UtilisateurDao {

    void enregistrement(UtilisateurEntity utilisateur);

    UtilisateurEntity findMember(String email, String mdp);


}
