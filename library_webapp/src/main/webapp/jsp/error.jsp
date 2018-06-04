<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="./include/head.jsp" %>
</head>

<body>
<%@ include file="./include/header.jsp" %>

    <!-- Page Content -->
    <div class="container">

        <!-- Page Heading/Breadcrumbs -->
        <h1 class="mt-4 mb-3">Une erreur s'est produite</h1>

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <s:a action="index" >Accueil</s:a>
            </li>
            <li class="breadcrumb-item active">erreur</li>
        </ol>

        <div class="jumbotron">

            <s:actionerror />

        </div>
        <!-- /.jumbotron -->

    </div>
    <!-- /.container -->

<%@ include file="./include/script.jsp" %>
</body>
</html>
