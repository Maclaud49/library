package com.parlow.library.webservice.service.impl;


import com.parlow.library.webservice.service.contract.VisitorI;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "com.parlow.library.webservice.service.contract.VisitorI")
public class VisitorImpl implements VisitorI{


    @Override
    public String booksList() {
        return null;
    }
}
