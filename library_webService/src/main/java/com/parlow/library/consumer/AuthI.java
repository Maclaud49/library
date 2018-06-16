package com.parlow.library.consumer;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

@WebService(name="Auth",
        targetNamespace="http://com.parlow.library.consummer")
public interface AuthI {

    @WebMethod
    public String enregistrement(String pseudo,String mdp, String email, String profil );
}
