package com.parlow.library.webservice.service.contract;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface RentalI {

    @WebMethod
    String rentalBookList();

    @WebMethod
    String bookRental(int bookId);

    @WebMethod
    String bookRental2x(int rentalId);

    @WebMethod
    String bookRentalReturn(int bookId);

    //renommer en book


}
