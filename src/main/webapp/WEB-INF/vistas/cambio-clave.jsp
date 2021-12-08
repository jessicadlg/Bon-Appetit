<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="fondo">
    <div class="form">
        <h1 class="titulo text-center p-2">Cambiar clave</h1>
        <form:form action="cambiar-clave" method="POST" modelAttribute="datos">
        <div class="form-group mx-2">
            <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario:"/>
            <c:if test="${not empty validaciones.get('emailIncompleto')}">
                <p class=" text-danger py-1">${validaciones.get('emailIncompleto')}</p>
            </c:if>
        </div>
        <div class="form-group mx-2">
            <form:input path="claveActual" type="text" id="password" class="form-control"
                        placeholder="Clave actual:"/>
            <c:if test="${not empty validaciones.get('passwordActualIncompleto')}">
                <p class="text-danger font-weight-bold py-1">${validaciones.get('passwordActualIncompleto')}</p>
            </c:if>
        </div>
        <div class="form-group mx-2">
            <form:input path="claveNueva" type="text" id="password" class="form-control"
                        placeholder="Clave nueva:"/>
            <c:if test="${not empty validaciones.get('passwordNuevaIncompleto')}">
                <p class="text-danger font-weight-bold py-2">${validaciones.get('passwordNuevaIncompleto')}</p>
            </c:if>
        </div>
        <div class="form-group mx-2">
            <form:input path="repiteClaveNueva" type="text" id="repite-password" class="form-control"
                        placeholder="Confirme clave nueva:"/>
            <c:if test="${not empty validaciones.get('passwordRepiteIncompleto')}">
                <p class="text-danger font-weight-bold py-2">${validaciones.get('passwordRepiteIncompleto')}</p>
            </c:if>
        </div>

        <div class="form-group ">
            <div class="row d-flex justify-content-around align-items-center">
                <div class="col-4">
                    <button class="btn btn-block btn-info " Type="Submit"/>
                    Modificar</button>
                </div>
                <div class="col-4">
                    <a href="login" class=" btn btn-outline-danger btn-block">Cancelar</a>
                </div>
            </div>
        </div>
            </form:form>
            <c:if test="${not empty error}">
                <h4><span class="text-light">${error}</span></h4>
                <br>
            </c:if>
        </div>
    </div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>