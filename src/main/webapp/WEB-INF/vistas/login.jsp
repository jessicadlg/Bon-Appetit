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
    <div class="fondo">
        <div class="form">
            <h1 class="titulo text-center">Login</h1>
            <form:form action="validar-login" method="POST" modelAttribute="datosLogin">
                <div class="form-group mx-2">
                    <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario:"/>
                </div>
                <div class="form-group mx-2">
                    <form:input path="password" type="text" id="password" class="form-control"
                                placeholder="Contraseña"/></div>
                <div class="form-group mx-2">
                    <button class="btn btn-block btn-info " Type="Submit"/>
                    Enviar</button>
                </div>
                <div class="form-group mx-2">
                    <a href="ir-a-registro" class="btn btn-block btn-outline-info ">Registrarme</a>
                </div>
                <div class="form-group mx-2">
                    <a href="ir-a-cambio-clave" class=" btn btn-outline-primary btn-block">Cambiar Contraseña</a>
                </div>
            </form:form>

            <c:if test="${not empty error}">
                <h4><span class="text-light">${error}</span></h4>
                <br>
            </c:if>
            ${msg}
        </div>
    </div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>