package com.parlow.library.webservice.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AuthI {


    @WebMethod
    String enregistrement(String pseudo,String mdp, String email, String profil );

    @WebMethod
    String connecter(String email, String mdp);
}
