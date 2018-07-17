package com.parlow.library.webservice.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AuthI {


    @WebMethod
    String register(String pseudo,String password, String email, String profile );

    @WebMethod
    String connexion(String email, String password);

    @WebMethod
    String findUser(int id);

    @WebMethod
    String findEmail(String email);
}
