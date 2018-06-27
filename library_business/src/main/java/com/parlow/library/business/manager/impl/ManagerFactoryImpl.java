package com.parlow.library.business.manager.impl;

import com.parlow.library.business.manager.contract.*;
import com.parlow.library.business.manager.contract.*;

import javax.inject.Named;
import javax.inject.Inject;

@Named
public final class ManagerFactoryImpl implements ManagerFactory {


    @Inject
    private UtilisateurManager utilisateurManager;

    @Override
    public UtilisateurManager getUtilisateurManager() {
        return this.utilisateurManager;
    }

    @Override
    public void setUtilisateurManager(UtilisateurManager pUtilisateurManager) {
        this.utilisateurManager = pUtilisateurManager;
    }


    }


