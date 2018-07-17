<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- Navigation -->
<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
        <s:a action="index" class="navbar-brand">Library</s:a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <s:if test="#session.library_user">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownLogin" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user"> <s:property value="#session.library_user.pseudo" /></i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                            <s:a action="logout" class="dropdown-item">Déconnexion</s:a>
                            <s:a action="utilisateur_detail" class="dropdown-item">
                                <s:param name="utilisateurId" value="#session.library_user.id" />
                                Profil
                            </s:a>
                        </div>
                    </li>
                </s:if>
                <s:else>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownConnexion" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fa fa-user"> Non connecté</i>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownPortfolio">
                            <s:a action="login" class="dropdown-item">Se connecter</s:a>
                            <s:a action="register" class="dropdown-item">S'enregistrer</s:a>
                        </div>
                    </li>
                </s:else>
            </ul>
        </div>
    </div>
</nav>