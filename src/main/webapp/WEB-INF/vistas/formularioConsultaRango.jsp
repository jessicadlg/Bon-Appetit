<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mt-5">
    <div class="card-header bg-white card shadow-lg">
        <div class="row mb-5">
            <div class="col">
                <h1 class="text-center">¡Ingresa tu direccion!</h1>
            </div>
        </div>
        <form action="consultarRango" method="POST">
            <div class="row mt-5">
                <div class="col">
                    <select name="calle" id="calle" class="form-control">
                        <option value="" selected disabled>Ingrese una calle</option>
                        <c:forEach items="${calles.calles}" var="calles">
                            <option value="${calles.nombre}">${calles.nombre}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty validacionesRango.get('calleError')}">
                        <p class="text-danger">${validacionesRango.get('calleError')}</p>
                    </c:if>
                </div>
                <div class="col">
                    <input type="number" maxlength="5" name="altura" pattern="[0-9]" class="form-control" placeholder="Altura" required>
                    <c:if test="${not empty validacionesRango.get('alturaError')}">
                        <p class="text-danger">${validacionesRango.get('alturaError')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col">
                    <select name="localidad" name="localidad" class="form-control" id="localidad">
                        <option value="" selected disabled>Ingrese una localidad</option>
                        <c:forEach items="${calles.localidad.localidades}" var="localidades">
                            <option value="${localidades.id}">${localidades.nombre}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${not empty validacionesRango.get('localidadError')}">
                        <p class="text-danger">${validacionesRango.get('localidadError')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-6 mx-auto mt-5">
                    <input type="submit" value="Enviar"
                           class="btn btn-block form-control btn-outline-info">
                    <c:if test="${ not empty errorConsultaRango}">
                        <div class="alert alert-danger mt-3" role="alert">
                            <div class="row">
                                <div class="col">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <p class="text-danger text-center">${errorConsultaRango}</p>
                                </div>
                            </div>
                        </div>
                            <div class="row my-2">
                                <div class="col mx-auto">
                                    <a href="home" class="btn btn-block btn btn-secondary">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                             class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd"
                                                  d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                                            <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                                        </svg>
                                        Volver al inicio
                                    </a>
                                </div>
                            </div>
                    </c:if>
                </div>

            </div>
        </form>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>

