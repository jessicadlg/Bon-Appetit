<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty pedido}">
    <div class="container mt-5">
        <div class="row">
            <div class="col">
                <h1 class="text-center">Sus productos</h1>
            </div>
            <div class="col">
                <h1 class="text-info text-center">¡Confirme sus datos!</h1>
            </div>
        </div>
        <form:form action="procesarCompra" method="POST" modelAttribute="datosConfirmacion">
            <div class="row">
                <div class="col">
                    <table class="table table-active table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Producto</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${itemsPedido}" var="productos">
                            <tr>
                                <td>${productos.producto.nombre}</td>
                                <td>$${productos.producto.precio}</td>
                                <td>${productos.cantidad}</td>
                                <td class="col-2"><img class="rounded img-thumbnail img-fluid"
                                                       src="images/${productos.producto.nombreImagen}" alt="" srcset="">
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="row">
                        <div class="col">
                            <p class="text-dark">Total:</p>
                            <input type="hidden" name="tiempoPreparacion" value="${Math.round(pedido.tiempoPreparacion + viaje.duration)}">
                            <input type="hidden" name="idPedido" value="${pedido.id}">
                            <input type="number" readonly name="total" class="form-control" value="${pedido.total}">
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row mt-3">
                        <div class="col">
                            <label for="calle">Calle:</label>
                            <input type="text" name="calle" id="calle" readonly class="form-control" value="${viaje.calle}">
                        </div>
                        <div class="col">
                            <label for="altura">Altura:</label>
                            <input type="text" name="altura" id="altura" readonly class="form-control" value="${viaje.altura}">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col">
                            <label for="localidad">Localidad:</label>
                            <input type="text" name="localidad" id="localidad" readonly class="form-control" value="${viaje.localidad}" placeholder="Nombre">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col">
                            <label for="nombre">*Nombre:</label>
                            <input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre" value="${validacionesCompra.get('nombreDefault')}">
                            <c:if test="${not empty validacionesCompra.get('nombreError')}">
                                <p class="text-danger">${validacionesCompra.get('nombreError')}</p>
                            </c:if>
                        </div>
                        <div class="col">
                            <label for="telefono">*Telefono:</label>
                            <input type="number" name="telefono" id="telefono" class="form-control" placeholder="Telefono" value="${validacionesCompra.get('telefonoDefault')}">
                            <c:if test="${not empty validacionesCompra.get('telefonoError')}">
                                <p class="text-danger">${validacionesCompra.get('telefonoError')}</p>
                            </c:if>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col mb-2">
                            <div class="alert alert-info" role="alert">
                                <p class="font-weight-bold">Su pedido será entregado en el período de ${Math.round(pedido.tiempoPreparacion + viaje.duration)} min!</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col">
                            <input type="submit" value="Confirmar Compra" class="btn btn-block btn-primary">
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</c:if>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>