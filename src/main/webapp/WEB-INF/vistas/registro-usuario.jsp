<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="#"><img src="images/logoB.png" class="img-logo" alt="Bon_appetit"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
    </div>
</nav>
<section class="fondo">
        <div class="form">
            <h1 class="titulo text-center">Registro</h1>
            <form:form action="registrarme" method="POST" modelAttribute="datosRegistro">
                <div class="form-group mx-2 ">
                    <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario"/>
                </div>
                <div class="form-group mx-2 ">
                    <form:input path="password" type="text" id="password" class="form-control"
                                placeholder="Contraseña"/>
                </div>
                <div class="form-group mx-2">
                    <form:input path="repitePassword" type="text" id="clave" class="form-control"
                                placeholder="Confirmar contraseña"/>
                </div>
                <div class="form-group mx-2">
                    <button class="btn btn-block btn-info " Type="Submit"/>
                    Enviar</button>
                </div>
                <div class="form-group mx-2">
                    <a href="login" class=" btn btn-outline-danger btn-block">Cancelar</a>
                </div>
            </form:form>
            <c:if test="${not empty error}">
                <h4><span class="text-light">${error}</span></h4>
                <br>
            </c:if>
        </div>
</section>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>