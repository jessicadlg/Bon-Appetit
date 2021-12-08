<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<section class="fondo">
        <div class="form">
            <h1 class="titulo text-center">Registro</h1>
            <form:form action="registrarme" method="POST" modelAttribute="datosRegistro">
                <div class="form-group mx-2 ">
                    <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario:"/>
                    <c:if test="${not empty validaciones.get('emailIncompleto')}">
                        <p class="text-danger font-weight-bold py-2">${validaciones.get('emailIncompleto')}</p>
                    </c:if>
                </div>
                <div class="form-group mx-2 ">
                    <form:input path="password" type="text" id="password" class="form-control"
                                placeholder="Contraseña:"/>
                    <c:if test="${not empty validaciones.get('passwordIncompleto')}">
                        <p class="text-danger font-weight-bold  py-2">${validaciones.get('passwordIncompleto')}</p>
                    </c:if>
                </div>
                <div class="form-group mx-2">
                    <form:input path="repitePassword" type="text" id="clave" class="form-control"
                                placeholder="Confirmar contraseña:"/>
                    <c:if test="${not empty validaciones.get('passwordIncompleto')}">
                        <p class="text-danger font-weight-bold py-2">${validaciones.get('passwordIncompleto')}</p>
                    </c:if>
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