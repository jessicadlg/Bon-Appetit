<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<!-- page content -->
<div class="container">
    <div class="card-header bg-white card shadow-lg">
        <div class="row">
            <div class="col">
                <h1 class="bg-info text-center">Reserva de mesa</h1>
            </div>
        </div>
        <form:form action="consultarDisponibilidad" method="GET">
            <div class="row">
                <div class="col">
                    <p>Fecha de reserva:</p>
                    <input type="date" name="fecha" id="fecha" class="form-control" value="${fechaConsulta}">
                    <c:if test="${not empty fechaInvalida}">
                        <p class="text-danger">${fechaInvalida}</p>
                    </c:if>
                    <c:if test="${not empty validacionesConsulta.get('fechaConsultaVacia')}">
                        <p class="text-danger">${validacionesConsulta.get('fechaConsultaVacia')}</p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <p class="mt-3">Hora: </p>
                    <c:if test="${empty horariosDisponibles}">
                        <select name="hora" id="hora" class="form-control">
                            <c:if test="${not empty horaConsulta}">
                                <optgroup label="Hora elegida">
                                    <option value="${horaConsulta}" selected>${horaConsulta}</option>
                                </optgroup>
                            </c:if>
                            <c:if test="${empty horaConsulta}">
                            <option value="" selected disabled="disabled">Ingrese una opcion...</option>
                            </c:if>
                            <option value="12:00">12:00</option>
                            <option value="14:00">14:00</option>
                            <option value="16:00">16:00</option>
                            <option value="18:00">18:00</option>
                            <option value="20:00">20:00</option>
                            <option value="22:00">22:00</option>
                        </select>
                    </c:if>
                    <c:if test="${not empty horariosDisponibles}">

                        <select name="hora" id="hora" class="form-control">
                            <option value="" selected disabled="disabled">Ingrese una opcion...</option>
                            <c:forEach items="${horariosDisponibles}" var="horarios">
                                <option value="${horarios}">${horarios}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${not empty validacionesConsulta.get('horaConsultaVacia')}">
                        <p class="text-danger">${validacionesConsulta.get('horaConsultaVacia')}</p>
                    </c:if>
                </div>
                <div class="col">
                    <p class="mt-3">Comensales: </p>
                    <input type="number" name="cantidadComensales" id="comensales" class="form-control"
                           placeholder="Cantidad" value="${comensalesConsulta}">
                    <c:if test="${not empty mnsjCantidadComensalesInvalida}">
                        <p><span class="text-danger">${mnsjCantidadComensalesInvalida}</span></p>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-6 mx-auto mt-3">
                    <input type="submit" value="Consultar disponibilidad"
                           class="btn btn-block form-control btn-outline-info">
                </div>
            </div>
        </form:form>
        <div class="row">
            <div class="col">
                <c:if test="${not empty reservaNoDisponible}">
                    <p class="text-center text-danger">${reservaNoDisponible}</p>
                </c:if>
                <c:if test="${not empty reservaConfirmada}">
                    <p class="text-success text-center">${reservaConfirmada}</p>
                </c:if>
            </div>
        </div>
        <c:if test="${not empty reservaDisponible or not empty validaciones}">
        <p class="text-center text-success">${reservaDisponible}</p>
        <form:form action="confirmarReserva" method="POST" modelAttribute="datosReserva">
        <div class="row">
            <div class="col">
                <input type="text" name="nombre" placeholder="Nombre" class="form-control" value="${nombrePuesto}"/>
                <c:if test="${not empty validaciones.get('nombreIncompleto')}">
                    <p class="text-danger">${validaciones.get('nombreIncompleto')}</p>
                </c:if>
            </div>
            <div class="col">
                <input type="text" name="celular" placeholder="Celular" class="form-control" value="${celularPuesto}"/>
                <c:if test="${not empty validaciones.get('celularIncompleto')}">
                    <p class="text-danger">${validaciones.get('celularIncompleto')}</p>
                </c:if>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-4">
                <input type="date" name="fecha" placeholder="fecha" class="form-control" value="${fechaConsulta}"
                       readonly/>
                <c:if test="${not empty validaciones.get('fechaIncompleta')}">
                    <p class="text-danger">${validaciones.get('fechaIncompleta')}</p>
                </c:if>
            </div>
            <c:if test="${not empty horaElegida or not empty validaciones}">
                <div class="col-4">
                    <select name="hora" class="form-control" aria-readonly="true">
                        <option value="${horaElegida}" selected>${horaElegida}</option>
                    </select>
                    <c:if test="${not empty validaciones.get('horaIncompleta')}">
                        <p class="text-danger">${validaciones.get('horaIncompleta')}</p>
                    </c:if>
                </div>
            </c:if>
                <%--            <c:if test="${empty horaElegida}">--%>
                <%--                <div class="col-4">--%>
                <%--                    <select name="hora" class="form-control">--%>
                <%--                        <c:forEach items="${horariosDisponibles}" var="horarios">--%>
                <%--                            <option disabled selected>Ingrese un horario...</option>--%>
                <%--                            <option value="${horarios}">${horarios}</option>--%>
                <%--                        </c:forEach>--%>
                <%--                    </select>--%>
                <%--                </div>--%>
                <%--            </c:if>--%>

            <div class="col-4">
                <input type="number" name="cantidadComensales" placeholder="cantidad" class="form-control"
                       value="${comensalesElegido}" readonly/>
                <c:if test="${not empty validaciones.get('cantidadIncompleta')}">
                    <p class="text-danger">${validaciones.get('cantidadIncompleta')}</p>
                </c:if>
            </div>
        </div>
        <div class="row">
            <div class="col-6 mx-auto mt-3">
                <input type="submit" value="Confirmar reserva" class="btn btn-block form-control btn-secondary">
            </div>
            </form:form>
            </c:if>
        </div>
    </div>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>