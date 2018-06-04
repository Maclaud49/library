<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="../include/head.jsp" %>
</head>

<body>
    <%@ include file="../include/header.jsp" %>

<!-- Page Content -->
<div class="container" style="min-height: 700px">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">Console d'administration</h1>

    <s:if test="hasActionMessages()">
        <div class="alert alert-success text-center" style="margin-top:20px">
            <s:actionmessage style="list-style:none" />
        </div>
    </s:if>

    <div class="row">
        <div class="col-md-8">
            <ul class="nav nav-tabs nav-justified">
                <li class="nav-item"><a href="#sites" data-toggle="tab" class="nav-link active show">Sites</a>
                </li>
                <li class="nav-item"><a href="#secteurs" data-toggle="tab" class="nav-link">Secteurs</a>
                </li>
                <li class="nav-item"><a href="#voies" data-toggle="tab" class="nav-link">Voies</a>
                </li>
                <li class="nav-item"><a href="#longueurs" data-toggle="tab" class="nav-link">Longueurs</a>
                </li>
                <li class="nav-item"><a href="#commentaires" data-toggle="tab" class="nav-link">Commentaires</a>
                </li>
                <li class="nav-item"><a href="#utilisateurs" data-toggle="tab" class="nav-link">Utilisateurs</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="tab-content">

        <!------------------------------------------------------- SITE ------------------------------------------------------->

        <div class="tab-pane fade in active adminTable active show" id="sites">
            <s:if test="!listSite.isEmpty()">
            <div class="table-responsive">
                <table class="table table-hover table-sm">
                    <thead>
                    <tr>
                        <th>Nom</th>
                        <!--<th class="text-center">Nb de secteurs</th>-->
                        <th class="text-center">Status</th>
                        <!--<th class="text-center">Nb commentaires</th>-->
                        <th class="text-center">Dernière modification</th>
                        <th></th>
                        <!-- Actions column -->
                    </tr>
                    </thead>
                    <s:iterator value="listSite">
                    <tr>
                        <td>
                            <s:a action="site_detail">
                                <s:param name="siteId" value="id" />
                                <s:property value="nom" />
                            </s:a>
                        </td>
                        <!-- <td class="text-center"><s:property value="site.nbSecteurs" /></td>-->
                        <td class="text-center">
                            <s:if test="publication">
                                Publié
                            </s:if>
                            <s:else>
                                A valider
                            </s:else>
                        </td>
                        <!--<td class="text-center"><s:property value="site.nbCommentaires" /></td>-->
                        <td class="text-center"><s:date name="lastUpdate" format="dd/MM/yyyy" /> à <s:date name="lastUpdate" format="HH:mm:ss" /></td>
                        <td class="text-center">
                            <s:a action="site_modifier" class="btn btn-info btn-xs" title="Editer">
                                <s:param name="siteId" value="id" />
                                <i class="fa fa-edit"></i>
                            </s:a>
                            <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#siteDialog${id}"><i class="fa fa-close"></i>
                            </button>
                            <div class="modal fade" id="siteDialog${id}" tabindex="-1"
                                 role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                        </div>
                                        <div class="modal-body">Supprimer le site <s:property value="nom" /> ?</div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                            <s:a action="site_supprimer" class="btn btn-danger">
                                                <s:param name="siteId" value="id" />
                                                Confirmer
                                            </s:a>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
                        </td>
                    </tr>
                    </s:iterator>
                </table>
            </div>
            </s:if>
            <s:else>
            <div class="alert alert-warning">Pas de sites.</div>
            </s:else>
            <s:a action="site_new" class="btn btn-primary">
                <i class="fa fa-plus"></i>
                Ajouter un site
            </s:a>
        </div>
<!------------------------------------------------------- SECTEUR ------------------------------------------------------->

        <div class="tab-pane fade in  adminTable" id="secteurs">
            <s:if test="!listSecteur.isEmpty()">
                <div class="table-responsive">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <!--<th class="text-center">Nb de voies</th>-->
                            <th class="text-center">Status</th>
                            <!--<th class="text-center">Nb commentaires</th>-->
                            <th class="text-center">Dernière modification</th>
                            <th></th>
                            <!-- Actions column -->
                        </tr>
                        </thead>
                        <s:iterator value="listSecteur">
                            <tr>
                                <td>
                                    <s:a action="secteur_detail">
                                        <s:param name="secteurId" value="id" />
                                        <s:property value="nom" />
                                    </s:a>
                                </td>
                                <!--<td class="text-center"><s:property value="secteur.nbVoies" /></td>-->
                                <td class="text-center">
                                    <s:if test="publication">
                                        Publié
                                    </s:if>
                                    <s:else>
                                        A valider
                                    </s:else>
                                </td>
                                <!--<td class="text-center"><s:property value="secteur.nbCommentaires" /></td>-->
                                <td class="text-center"><s:date name="lastUpdate" format="dd/MM/yyyy" /> à <s:date name="lastUpdate" format="HH:mm:ss" /></td>
                                <td class="text-center">
                                    <s:a action="secteur_modifier" class="btn btn-info btn-xs" title="Editer">
                                        <s:param name="secteurId" value="id" />
                                        <i class="fa fa-edit"></i>
                                    </s:a>
                                    <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#secteurDialog${id}"><i class="fa fa-close"></i>
                                    </button>
                                    <div class="modal fade" id="secteurDialog${id}" tabindex="-1"
                                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                </div>
                                                <div class="modal-body">Supprimer le secteur <s:property value="nom" /> ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                                    <s:a action="secteur_supprimer" class="btn btn-danger">
                                                        <s:param name="secteurId" value="id" />
                                                        Confirmer
                                                    </s:a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                <div class="alert alert-warning">Pas de secteurs.</div>
            </s:else>
            <s:a action="secteur_new" class="btn btn-primary">
                <i class="fa fa-plus"></i>
                Ajouter un secteur
            </s:a>
        </div>

        <!------------------------------------------------------- VOIE ------------------------------------------------------->

        <div class="tab-pane fade in adminTable" id="voies">
            <s:if test="!listVoie.isEmpty()">
                <div class="table-responsive">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <!--<th class="text-center">Nb de longueurs</th>-->
                            <th class="text-center">Status</th>
                            <!--<th class="text-center">Nb commentaires</th>-->
                            <th class="text-center">Dernière modification</th>
                            <th></th>
                            <!-- Actions column -->
                        </tr>
                        </thead>
                        <s:iterator value="listVoie">
                            <tr>
                                <td>
                                    <s:a action="voie_detail">
                                        <s:param name="voieId" value="id" />
                                        <s:property value="nom" />
                                    </s:a>
                                </td>
                                <!--<td class="text-center"><s:property value="voie.nbLongueurs" /></td>-->
                                <td class="text-center">
                                    <s:if test="publication">
                                        Publié
                                    </s:if>
                                    <s:else>
                                        A valider
                                    </s:else>
                                </td>
                                <!--<td class="text-center"><s:property value="voie.nbCommentaires" /></td>-->
                                <td class="text-center"><s:date name="lastUpdate" format="dd/MM/yyyy" /> à <s:date name="lastUpdate" format="HH:mm:ss" /></td>
                                <td class="text-center">
                                    <s:a action="voie_modifier" class="btn btn-info btn-xs" title="Editer">
                                        <s:param name="voieId" value="id" />
                                        <i class="fa fa-edit"></i>
                                    </s:a>
                                    <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#voieDialog${id}"><i class="fa fa-close"></i>
                                    </button>
                                    <div class="modal fade" id="voieDialog${id}" tabindex="-1"
                                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                </div>
                                                <div class="modal-body">Supprimer le voie <s:property value="nom" /> ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                                    <s:a action="voie_supprimer" class="btn btn-danger">
                                                        <s:param name="voieId" value="id" />
                                                        Confirmer
                                                    </s:a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                <div class="alert alert-warning">Pas de voies.</div>
            </s:else>
            <s:a action="voie_new" class="btn btn-primary">
                <i class="fa fa-plus"></i>
                Ajouter un voie
            </s:a>
        </div>

        <!------------------------------------------------------- LONGUEUR ------------------------------------------------------->

        <div class="tab-pane fade in adminTable" id="longueurs">
            <s:if test="!listLongueur.isEmpty()">
                <div class="table-responsive">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th class="text-center">Status</th>
                            <!--<th class="text-center">Nb commentaires</th>-->
                            <th class="text-center">Dernière modification</th>
                            <th></th>
                            <!-- Actions column -->
                        </tr>
                        </thead>
                        <s:iterator value="listLongueur">
                            <tr>
                                <td>
                                    <s:a action="longueur_detail">
                                        <s:param name="longueurId" value="id" />
                                        <s:property value="nom" />
                                    </s:a>
                                </td>
                                <td class="text-center">
                                    <s:if test="publication">
                                        Publié
                                    </s:if>
                                    <s:else>
                                        A valider
                                    </s:else>
                                </td>
                                <!--<td class="text-center"><s:property value="longueur.nbCommentaires" /></td>-->
                                <td class="text-center"><s:date name="lastUpdate" format="dd/MM/yyyy" /></td>
                                <td class="text-center">
                                    <s:a action="longueur_modifier" class="btn btn-info btn-xs" title="Editer">
                                        <s:param name="longueurId" value="id" />
                                        <i class="fa fa-edit"></i>
                                    </s:a>
                                    <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#longueurDialog${id}"><i class="fa fa-close"></i>
                                    </button>
                                    <div class="modal fade" id="longueurDialog${id}" tabindex="-1"
                                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                </div>
                                                <div class="modal-body">Supprimer le longueur <s:property value="nom" /> ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                                    <s:a action="longueur_supprimer" class="btn btn-danger">
                                                        <s:param name="longueurId" value="id" />
                                                        Confirmer
                                                    </s:a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                <div class="alert alert-warning">Pas de longueurs.</div>
            </s:else>
            <s:a action="longueur_new" class="btn btn-primary">
                <i class="fa fa-plus"></i>
                Ajouter un longueur
            </s:a>
        </div>

        <!------------------------------------------------------- COMMENTAIRE ------------------------------------------------------->

        <div class="tab-pane fade in  adminTable" id="commentaires">
            <s:if test="!listCommentaire.isEmpty()">
                <div class="table-responsive">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th class="text-center">Commentaire</th>
                            <th class="text-center">Auteur</th>
                            <th class="text-center">Section</th>
                            <th class="text-center">Date création</th>
                            <th></th>
                            <!-- Actions column -->
                        </tr>
                        </thead>
                        <s:iterator value="listCommentaire">
                            <tr>
                                <td>
                                    <s:if test="target_table == 'SITE'">
                                        <s:a action="site_detail">
                                            <s:param name="siteId" value="reference_id" />
                                            <s:property value="commentaire" />
                                        </s:a>
                                    </s:if>
                                    <s:elseif test="target_table == 'SECTEUR'">
                                        <s:a action="secteur_detail">
                                            <s:param name="secteurId" value="reference_id" />
                                            <s:property value="commentaire" />
                                        </s:a>
                                    </s:elseif>
                                    <s:elseif test="target_table == 'VOIE'">
                                        <s:a action="voie_detail">
                                            <s:param name="voieId" value="reference_id" />
                                            <s:property value="commentaire" />
                                        </s:a>
                                    </s:elseif>
                                    <s:elseif test="target_table == 'LONGUEUR'">
                                        <s:a action="longueur_detail">
                                            <s:param name="longueurId" value="reference_id" />
                                            <s:property value="commentaire" />
                                        </s:a>
                                    </s:elseif>
                                </td>
                                <td class="text-center"><s:property value="utilisateur.prenom" /> <s:property value="utilisateur.nom" /></td>
                                <td class="text-center"><s:property value="target_table" /></td>
                                <td class="text-center"><s:date name="dateCreation" format="dd/MM/yyyy" /> <s:date name="dateCreation" format="HH:mm" /></td>
                                <td class="text-center">
                                    <!--<s:a action="commentaire_modifier" class="btn btn-info btn-xs" title="Editer">
                                        <s:param name="commentaireId" value="id" />
                                        <i class="fa fa-edit"></i>
                                    </s:a>-->
                                    <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#commentaireDialog${id}"><i class="fa fa-close"></i>
                                    </button>
                                    <div class="modal fade" id="commentaireDialog${id}" tabindex="-1"
                                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                </div>
                                                <div class="modal-body">Supprimer le commentaire <s:property value="nom" /> ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                                    <s:a action="commentaire_supprimer" class="btn btn-danger">
                                                        <s:param name="commentaireId" value="id" />
                                                        Confirmer
                                                    </s:a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                <div class="alert alert-warning">Pas de commentaires.</div>
            </s:else>
        </div>

        <!------------------------------------------------------- UTILISATEUR ------------------------------------------------------->

        <div class="tab-pane fade in  adminTable " id="utilisateurs">
            <s:if test="!listUtilisateur.isEmpty()">
                <div class="table-responsive">
                    <table class="table table-hover table-sm">
                        <thead>
                        <tr>
                            <th class="text-center">Nom et Prénom</th>
                            <th class="text-center">Email</th>
                            <th class="text-center">Role</th>
                            <th class="text-center">Nb de commentaires</th>
                            <th></th>
                            <!-- Actions column -->
                        </tr>
                        </thead>
                        <s:iterator value="listUtilisateur">
                            <tr>
                                <td>
                                    <s:a action="utilisateur_detail">
                                        <s:param name="utilisateurId" value="id" />
                                        <s:property value="nom" /> <s:property value="prenom" />
                                    </s:a>
                                </td>
                                <td class="text-center"><s:property value="email" /></td>
                                <td class="text-center"><s:property value="profil" /></td>
                                <td class="text-center"><s:property value="nbCommentaires" /></td>
                                <td class="text-center">
                                    <s:a action="utilisateur_modifier" class="btn btn-info btn-xs" title="Editer">
                                        <s:param name="utilisateurId" value="id" />
                                        <i class="fa fa-edit"></i>
                                    </s:a>
                                    <button type="button" class="btn btn-danger btn-xs" title="Supprimer" data-toggle="modal" data-target="#utilisateurDialog${id}"><i class="fa fa-close"></i>
                                    </button>
                                    <div class="modal fade" id="utilisateurDialog${id}" tabindex="-1"
                                         role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                </div>
                                                <div class="modal-body">Supprimer l'utilisateur <s:property value="prénom" /> <s:property value="nom" /> ?</div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                                    <s:a action="utilisateur_supprimer" class="btn btn-danger">
                                                        <s:param name="utilisateurId" value="id" />
                                                        Confirmer
                                                    </s:a>
                                                </div>
                                            </div>
                                            <!-- /.modal-content -->
                                        </div>
                                        <!-- /.modal-dialog -->
                                    </div>
                                    <!-- /.modal -->
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                <div class="alert alert-warning">Pas d'utilisateurs.</div>
            </s:else>
            <s:a action="register" class="btn btn-primary">
                <i class="fa fa-plus"></i>
                Ajouter un utilisateur
            </s:a>
        </div>






    </div>



</div>
<!-- /.container -->

<%@ include file="../include/script.jsp" %>

</body>

</html>