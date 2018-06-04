<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="../include/head.jsp" %>
</head>

<body>
    <%@ include file="../include/header.jsp" %>


<header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active" style="background-image: url('../../ressources/images/slide2.jpg')">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item" style="background-image: url('../../ressources/images/slide1.jpg')">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
            <div class="carousel-item" style="background-image: url('../../ressources/images/slide3.jpg')">
                <div class="carousel-caption d-none d-md-block">

                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Précédent</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Suivant</span>
        </a>
    </div>
</header>

<!-- Page Content -->
<div class="container">

    <h1 class="my-4">Bienvenue sur votre bibliothèque virtuelle</h1>


    <!-- Call to Action Section -->
    <s:if test="!#session.escalade_user">
        <div class="row mb-4">
            <div class="col-md-8">
                <p>Enregistre toi pour profiter pleinement de ce site</p>
            </div>
            <div class="col-md-4">
                <s:a action="register" class="btn btn-lg btn-secondary btn-block">S'enregistrer</s:a>
            </div>
        </div>
    </s:if>

</div>
<!-- /.container -->

    <%@ include file="../include/footer.jsp" %>

    <%@ include file="../include/script.jsp" %>

</body>

</html>

