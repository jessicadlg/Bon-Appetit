<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<!-- page content -->
<div class="container  mb-2 ">
    <div class="row p-5">
        <div class="col-md-12 col-sm-12 mx-auto">
            <div class="card shadow-lg p-3 mb-3 bg-white ">
                <div class="card-header text-center bg-info">Reserva de mesa</div>
                <div class="card-body">
                    <form:form action="consultarDisponibilidad" method="GET">
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <p class="text-dark">Fecha de reserva:</p>
                                <input type="date" name="fecha" class="form-control"/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="hora">Hora:</label>
                                    <%--<input name="hora" type="text" class="form-control" id="hora" placeholder="20"
                                           required>--%>
                                <select name="hora" id="hora" class="form-control">
                                    <option value="" selected disabled="disabled">Ingrese una opcion..</option>
                                    <option value="12:00">12hs</option>
                                    <option value="14:00">14hs</option>
                                    <option value="16:00">16hs</option>
                                    <option value="18:00">18hs</option>
                                    <option value="20:00">20hs</option>
                                    <option value="22:00">22hs</option>
                                </select>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label>Cantidad de Comensales:</label>
                                <input name="cantidadComensales" type="number" class="form-control" id="comensales"
                                       placeholder=""
                                       value="" required>
                                <c:if test="${not empty mnsjCantidad}">
                                    <p><span class="text-danger">${mnsjCantidad}</span></p>
                                    <br>
                                </c:if>
                            </div>
                            <div class="mx-auto">
                                <button class="btn btn-outline-info btn-block" type="submit">Consultar disponibilidad
                                </button>
                            </div>
                        </div>
                    </form:form>
                    <c:if test="${not empty mnsjError}">
                        <h4 class="mb-5"><span class="text-center text-danger">${mnsjError}</span></h4>
                        <br>
                    </c:if>
                </div>
                <c:if test="${not empty mnsj}">
                <div class="card-body">
                    <p class="text-center text-success">${mnsj}</p>
                    <form:form action="confirmarReserva" method="POST" modelAttribute="reserva">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nombre">Nombre:</label>
                                <input name="nombre" type="text" class="form-control" id="nombre"
                                       required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="celular">Celular:</label>
                                <input name="celular" type="text" class="form-control" id="celular"
                                       required>
                            </div>

                            <div class="mx-auto">
                                <button class="btn btn-secondary" type="submit">Confirmar reserva</button>
                                <c:if test="${not empty reservaConfirmada}">
                                    <p><span class="text-success text-centerr">${reservaConfirmada}</span></p>
                                    <br>
                                </c:if>
                            </div>
                        </div>
                    </form:form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>



<jsp:include page="/WEB-INF/includes/footer.jsp"/>