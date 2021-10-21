<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<!-- page content -->
<div class="container">
    <div class="row p-5">
        <div class="col-md-12 col-sm-12 mx-auto">
            <div class="card shadow-lg p-3 mb-5 bg-white ">
                <div class="card-header text-center">Reserva de mesa</div>
                <div class="card-body">
                    <form:form action="" method="POST" modelAttribute="reserva">
                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="fecha">Fecha de reserva:</label>
                            <input name="fecha" type="date" class="form-control" id="fecha" required>
                        </div>
                        <div class="col-md-12 mb-3">
                            <label for="">Hora:</label>
                            <input name="hora" type="text" class="form-control" id="hora"
                                   required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Cantidad de Comensales:</label>
                            <input name="cantidadComensales" type="text" class="form-control" id="comensales"
                                   placeholder=""
                                   value="" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label>Mesa disponible:</label>
                            <input name="cantidadMesa" type="text" class="form-control" id="" placeholder=""
                                   value="" required>
                        </div>

                        <div class="mx-auto">
                            <button class="btn btn-secondary" type="submit">Enviar</button>
                        </div>
                    </div>
                </div>

                </form:form>
            </div>
        </div>
    </div>
</div>
</div>
<%--<div class="fondo">
    <div class="form">
        <h1 class="titulo text-center p-2">Reservar</h1>
        <form:form action="" method="POST" modelAttribute="datos">
            <div class="form-group mx-2">
                <form:input path="email" id="email" type="email" class="form-control" placeholder="Usuario:"/><br/>
                <form:input path="claveActual" type="text" id="password" class="form-control"
                            placeholder="Clave actual:"/><br/>
                <form:input path="claveNueva" type="text" id="password" class="form-control"
                            placeholder="Clave nueva:"/><br/>
                <form:input path="repiteClaveNueva" type="text" id="repite-password" class="form-control"
                            placeholder="Confirme clave nueva:"/>
            </div>
            <div class="row  mx-auto">
                <div class="col-4">
                    <button class="btn btn-block btn-info " Type="Submit"/>
                    Modificar</button>
                </div>
                <div class="col-4">
                    <a href="login" class=" btn btn-outline-danger btn-block">Cancelar</a>
                </div>
            </div>
        </form:form>--%>
<c:if test="${not empty error}">
    <h4><span class="text-dark">${error}</span></h4>
    <br>
</c:if>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>