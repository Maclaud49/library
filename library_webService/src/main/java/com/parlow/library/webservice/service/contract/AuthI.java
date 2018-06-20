package com.parlow.library.webservice.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name="Auth",
        targetNamespace="http://com.parlow.library.consummer")
public interface AuthI {

    @WebMethod
    String enregistrement(String pseudo,String mdp, String email, String profil );

    @WebMethod
    String connecter(String email, String mdp);
}
