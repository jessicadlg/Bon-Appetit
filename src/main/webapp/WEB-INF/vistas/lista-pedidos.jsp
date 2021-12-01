<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>


<c:if test="${not empty listaPedidos}">
    <div class="container-fluid">
        <div class="row">
            <div class="col">
                <table class="table table-active table-hover">
                    <tr>
                        <td>Id</td>
                        <td>Tiempo de preparación</td>
                        <td>Total</td>
                        <td>Estado del pedido</td>
                        <td>Localidad</td>
                        <td>Calle</td>
                        <td>Altura</td>
                        <td>Cambiar estado del pedido</td>
                        <td>
                            <div class="btn-group">
                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                    Filtros
                                </button>
                                <div class="dropdown-menu">
                                    <a href="/Bon_Appetit/lista-pedidos/PREPARANDO" class="dropdown-item">PREPARANDO</a>
                                    <a href="/Bon_Appetit/lista-pedidos/VIAJANDO" class="dropdown-item">VIAJANDO</a>
                                    <a href="/Bon_Appetit/lista-pedidos/FINALIZADO" class="dropdown-item">FINALIZADO</a>
                                    <a href="/Bon_Appetit/lista-pedidos" class="dropdown-item">Quitar filtros</a>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:forEach items="${listaPedidos}" var="pedidos">
                        <tr>
                            <td>${pedidos.id}</td>
                            <c:choose>
                                <c:when test="${pedidos.tiempoPreparacion == 0.0}">
                                    <td>Sin definir</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${pedidos.tiempoPreparacion}</td>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${pedidos.tiempoPreparacion == 0.0}">
                            </c:if>
                            <c:choose>
                                <c:when test="${pedidos.total == 0.0}">
                                    <td>Sin definir</td>
                                </c:when>
                                <c:otherwise>
                                    <td>$${pedidos.total}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${pedidos.estadoPedido}</td>
                            <td>${pedidos.localidad}</td>
                            <td>${pedidos.calle}</td>
                            <td>${pedidos.altura}</td>
                            <c:choose>
                                <c:when test="${pedidos.estadoPedido == 'FINALIZADO'}">
                                    <td>
                                        <form:form action="cambiar-estado" method="post">
                                            <div class="row">
                                                <div class="col-9">
                                                    <select name="estado" disabled id="estado" class="form-control">
                                                        <option value="PREPARANDO">PREPARANDO</option>
                                                        <option value="VIAJANDO">VIAJANDO</option>
                                                        <option selected value="FINALIZADO">FINALIZADO</option>
                                                    </select>
                                                </div>
                                                <div class="col-3">
                                                    <input type="hidden" name="idPedido" value="${pedidos.id}">
                                                    <input type="submit" disabled class="btn btn-danger btn-block" value="Cambiar">
                                                </div>
                                            </div>
                                        </form:form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <form:form action="cambiar-estado" method="post">
                                            <div class="row">
                                                <div class="col-9">
                                                    <select name="estado" id="estado" class="form-control">
                                                        <option value="PREPARANDO">PREPARANDO</option>
                                                        <option value="VIAJANDO">VIAJANDO</option>
                                                        <option value="FINALIZADO">FINALIZADO</option>
                                                    </select>
                                                </div>
                                                <div class="col-3">
                                                    <input type="hidden" name="idPedido" value="${pedidos.id}">
                                                    <input type="submit" class="btn btn-danger btn-block" value="Cambiar">
                                                </div>
                                            </div>
                                        </form:form>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty listaPedidosVacia}">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 class="text-center text-danger">${listaPedidosVacia}</h1>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty pedidoError}">
    <div class="container">
        <div class="row">
            <div class="col">
                <h1 class="text-center text-danger">${pedidoError}</h1>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>