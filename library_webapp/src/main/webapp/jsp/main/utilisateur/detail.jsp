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

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">Affichage du profil
        <small><s:property value="site.nom" /></small>
    </h1>

    <ol class="breadcrumb">
        <li class="breadcrumb-item">
            <s:a action="index" ><s:text name="home.home"/></s:a>
        </li>
        <li class="breadcrumb-item active">Profil</li>
    </ol>

    <div class="row">
        <div class="col-md-8">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-8">
                <h2>Profil</h2>
                <hr>
            </div>
        </div>
        <fieldset class="form-group">
            <legend>Informations</legend>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>E-Mail</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield readonly="true" name="utilisateur.email" class="form-control"
                                         value="%{utilisateur.email}" title="Votre adresse email"/>
                        </div>
                        <s:if test="hasFieldErrors()">
                            <small class="text-danger align-middle"><s:fielderror fieldName="registerEmail"/></small>
                        </s:if>
                    </div>
                </div>
            </div>
            <div style="display: none;">
                <s:textfield name="utilisateur.id" value="%{utilisateur.id}"/>
                <s:textfield name="utilisateur.profil" value="%{utilisateur.profil}"/>
                <s:textfield name="utilisateur.password" value="%{utilisateur.password}"/>
            </div>

            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Nom</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield readonly="true" name="utilisateur.nom" class="form-control" value="%{utilisateur.nom}" title="Votre nom" required="true"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Prénom</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.prenom"  readonly="true" class="form-control" value="%{utilisateur.prenom}" title="Votre prénom" required="true"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Date de naissance</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.dateNaissance" class="form-control" readonly="true"
                                         value="%{utilisateur.dateNaissance}" title="01/01/1981" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Cotation</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                                <s:textfield name="utilisateur.cotation" class="form-control" readonly="true"
                                             value="%{utilisateur.cotation}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="form-group">
            <legend>Adresse</legend>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Adresse ligne 1</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.adresse1" class="form-control" readonly="true"
                                         value="%{utilisateur.adresse1}" title="Votre adresse"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Adresse ligne 2</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.adresse2" class="form-control" readonly="true"
                                         value="%{utilisateur.adresse2}" title="Votre adresse"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Code postal</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.codePostal" class="form-control" readonly="true"
                                         value="%{utilisateur.codePostal}" title="Votre code postal"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Ville</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.ville" class="form-control" readonly="true"
                                         value="%{utilisateur.ville}" title="Votre ville"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="form-group has-danger">
                        <label>Pays</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <s:textfield name="utilisateur.pays" class="form-control" readonly="true"
                                         value="%{utilisateur.pays}" title="Votre pays"/>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="form-group">
            <legend>Topos</legend>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-7">
                    <div class="card-text"></strong>
                        <s:if test="listTopo.size() == 0">
                            Pas de topos
                        </s:if>
                        <s:else>
                            <ul class="text-center">
                                <s:iterator value="listTopo">
                                    <s:if test="publication == true && utilisateur.id == #session.escalade_user.id|| #session.escalade_user.profil =='admin'">
                                        <li style = "list-style: none;margin:10px">
                                            <s:a action="topo_detail" class="btn btn-primary">
                                                <s:param name="topoId" value="id" />
                                                <s:property value="nom" />
                                            </s:a>
                                        </li>
                                    </s:if>
                                </s:iterator>
                            </ul>
                        </s:else>
                    </div>
                </div>
            </div>
        </fieldset>


        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-7" style="padding-top: .35rem">
                        <span class="text-danger align-middle">
                            <s:actionerror class="text-danger align-middle"/>
                        </span>
            </div>
        </div>
        </div>

        <!-- Sidebar Widgets Column -->
        <div class="col-md-4">

            <s:if test="utilisateur.id == #session.escalade_user.id || #session.escalade_user.profil =='admin'">
                <!-- Modification profil -->
                <div class="card mb-4">
                    <h5 class="card-header">Modifier le profil</h5>
                    <div class="card-body text-center">
                        <s:a action="utilisateur_modifier" class="btn btn-secondary"><s:param name="utilisateurId" value="utilisateur.id" />Modifier</s:a>
                    </div>
                </div>
            </s:if>


        </div>

    </div>
    <!-- /.row -->


</div>

<%@ include file="../../include/footer.jsp" %>

<%@ include file="../../include/script.jsp" %>

</body>
</html>