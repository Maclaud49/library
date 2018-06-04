package com.parlow.library.business.manager.impl;

import com.parlow.library.business.manager.contract.UtilisateurManager;
import com.parlow.library.model.bean.Utilisateur;
import com.parlow.library.model.exception.FunctionalException;
import com.parlow.library.model.exception.NotFoundException;

import javax.inject.Named;
import java.util.List;


@Named
public class UtilisateurManagerImpl implements UtilisateurManager {

    @Override
    public Utilisateur findByEmail( String pEmail )throws NotFoundException{
       return null;
    }

    @Override
    public Utilisateur login(String pEmail, String pPassword) throws NotFoundException {
        return null;

    }

    @Override
    public Utilisateur findById(int pId) throws NotFoundException {
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return null;
    }

    @Override
    public int insert(Utilisateur pUtilisateur) throws FunctionalException {
        return 0;
    }

    @Override
    public void delete(int pId) throws NotFoundException {

    }

    @Override
    public void update(Utilisateur pUtilisateur) throws FunctionalException {

    }





}
