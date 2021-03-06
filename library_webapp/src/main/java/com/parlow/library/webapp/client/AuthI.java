
package com.parlow.library.webapp.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "AuthI", targetNamespace = "http://contract.service.webservice.library.parlow.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface AuthI {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String register(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0,
            @WebParam(name = "arg1", partName = "arg1")
                    String arg1,
            @WebParam(name = "arg2", partName = "arg2")
                    String arg2,
            @WebParam(name = "arg3", partName = "arg3")
                    String arg3);

    /**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String findUser(
            @WebParam(name = "arg0", partName = "arg0")
                    int arg0);

    /**
     *
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String connexion(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0,
            @WebParam(name = "arg1", partName = "arg1")
                    String arg1);

    /**
     *
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    public String findEmail(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);

}
