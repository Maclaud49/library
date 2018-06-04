<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="../../include/head.jsp" %>
    </head>

    <body>
        <%@ include file="../../include/header.jsp" %>


        <div class="container" style="margin-top:50px">

            <form class="form-horizontal" method="POST" action="login.action">
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <h2><s:text name="login.connexion"/></h2>
                        <smal><s:a action="register">Je m'enregistre</s:a></smal>
                        <hr>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group has-danger">
                            <label class="sr-only" for="email">E-Mail</label>
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                                <s:textfield type="email" name="email" class="form-control"
                                             placeholder="votreadresse@exemple.fr" required="true"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-control-feedback">
                                <span class="text-danger align-middle">
                                    <!-- <i class="fa fa-close"></i> error message -->
                                </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label class="sr-only" for="password">Mot de passe</label>
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                                <s:password type="password" class="form-control" name="password"  placeholder="Mot de passe"  required="true"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-control-feedback">
                                <span class="text-danger align-middle">
                                <!-- password error message here -->
                                </span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6" style="padding-top: .35rem">
                        <span class="text-danger align-middle">
                            <s:actionerror class="text-danger align-middle"/>
                        </span>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6" style="padding-top: .35rem">
                        <div class="form-check mb-2 mr-sm-2 mb-sm-0">
                            <label class="form-check-label">
                                <s:checkbox class="form-check-input" name="remember" type="checkbox" value="Remember Me"/>
                                <span style="padding-bottom: .15rem">Se souvenir de moi</span>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="row" style="padding-top: 1rem">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <button type="submit" class="btn btn-success"><i class="fa fa-sign-in"></i> Se connecter</button>
                        <a class="btn btn-link" href="#">Vous avez oublié votre mot de passe ?</a>
                    </div>
                </div>

           </form>
        </div>

        <%@ include file="../../include/script.jsp" %>

    </body>
</html>
