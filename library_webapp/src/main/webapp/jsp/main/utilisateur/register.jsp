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

    <form class="form-horizontal" method="POST" action="register.action">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h2>Enregistrement</h2>
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
                                     placeholder="votreadresse@exemple.fr" title="Votre adresse email" required="true"/>
                    </div>
                    <s:if test="hasFieldErrors()">
                        <small class="text-danger align-middle"><s:fielderror fieldName="registerEmail" style="list-style:none"/></small>
                    </s:if>
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
                        <s:password type="password" class="form-control" name="password"  placeholder="Votre mot de passe"
                                    title=" Votre mot de passe - Au moins 6 caractères" required="true" />
                    </div>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"></div>
                        <small id="passwordHelp" class="form-text text-muted">Doit contenir au moins 6 caractères</small>
                    </div>
                    <s:if test="hasFieldErrors()">
                        <small class="text-danger align-middle"><s:fielderror fieldName="registerPassword" style="list-style:none"/></small>
                    </s:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="form-group">
                    <label class="sr-only" for="password">Vérification mot de passe</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                        <s:password type="password" class="form-control" name="password2" placeholder="Vérification de votre mot de passe"
                                    title="Vérification de votre mot de passe" required="true"/>
                    </div>
                    <s:if test="hasFieldErrors()">
                        <small class="text-danger align-middle"><s:fielderror fieldName="registerPassword" style="list-style:none"/></small>
                    </s:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" style="padding-top: .35rem">
                        <span class="text-danger align-middle">
                            <s:if test="hasFieldErrors()">
                              <small class="text-danger align-middle"><s:fielderror fieldName="registerPassword2" style="list-style:none"/></small>
                            </s:if>
                        </span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="form-group has-danger">
                    <label class="sr-only" for="nom">Nom</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <s:textfield name="nom" class="form-control" placeholder="Votre nom" title="Votre nom" required="true"/>
                    </div>
                    <s:if test="hasFieldErrors()">
                        <small class="text-danger align-middle"><s:fielderror fieldName="registerNom" style="list-style:none"/></small>
                    </s:if>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="form-group has-danger">
                    <label class="sr-only" for="prenom">Prénom</label>
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                        <s:textfield name="prenom" class="form-control" placeholder="Votre prénom" title="Votre prénom" required="true"/>
                    </div>
                    <s:if test="hasFieldErrors()">
                        <small class="text-danger align-middle"><s:fielderror fieldName="registerPrenom" style="list-style:none"/></small>
                    </s:if>
                </div>
            </div>
        </div>
        <s:if test="#session.escalade_user.profil =='admin'">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label>Profil</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:select  class="form-control" name="profil"
                                       list="{'utilisateur','admin'}" value="utilisateur"/>
                        </div>
                    </div>
                </div>
            </div>
        </s:if>
        <s:else>
            <div style="display: none;">
                <s:textfield name="profil" value="utilisateur"/>
            </div>
        </s:else>

        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6" style="padding-top: .35rem">
                        <span class="text-danger align-middle">
                            <s:actionerror class="text-danger align-middle"/>
                        </span>
            </div>
        </div>

        <div class="row" style="padding-top: 1rem">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <button type="submit" class="btn btn-success"><i class="fa fa-sign-in"></i> S'enregistrer</button>
            </div>
        </div>

    </form>
</div>

<%@ include file="../../include/script.jsp" %>

</body>
</html>
