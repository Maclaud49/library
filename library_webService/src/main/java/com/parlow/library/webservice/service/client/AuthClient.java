package com.parlow.library.webservice.service.client;

import com.parlow.library.model.bean.UtilisateurEntity;

public class AuthClient {
    public static void main(String[] args) {
        AuthImplService authService = new AuthImplService();
        AuthI save = authService.getAuthImplPort();


        save.enregistrement("Maclaud1", "AvecClient", "mac@parlow-co.com", "Admin");
    }

}
