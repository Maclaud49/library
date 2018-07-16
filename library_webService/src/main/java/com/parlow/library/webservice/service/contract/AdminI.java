package com.parlow.library.webservice.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AdminI {


    @WebMethod
    String validateBookrentalRequest(int rentalId);

    @WebMethod
    String BookRentalDuration(int nbJour);
}
