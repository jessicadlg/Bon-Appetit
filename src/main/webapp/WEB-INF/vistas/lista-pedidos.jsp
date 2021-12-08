<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<c:if test="${not empty listaPedidos}">
    <div class="container mt-5  py-5">
        <div class="row my-2">
            <div class="col-10">
                <h1 class="">Lista de Pedidos:</h1>
            </div>
            <div class="col">
                <div class="btn-group">
                    <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                        Filtros
                    </button>
                    <div class="dropdown-menu">
                        <a href="/Bon_Appetit/lista-pedidos/PREPARANDO" class="dropdown-item">PREPARANDO</a>
                        <a href="/Bon_Appetit/lista-pedidos/VIAJANDO" class="dropdown-item">VIAJANDO</a>
                        <a href="/Bon_Appetit/lista-pedidos/FINALIZADO" class="dropdown-item">FINALIZADO</a>
                    </div>
                </div>
                <a href="/Bon_Appetit/lista-pedidos" class="btn btn-danger">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                         class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                        <path fill-rule="evenodd"
                              d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                        <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                    </svg>
                </a>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <table class="table table-active table-hover">
                    <tr>
                        <td class="font-weight-bold text-center align-middle">Nro Pedido</td>
                        <td class="font-weight-bold text-center">Tiempo de preparación</td>
                        <td class="font-weight-bold text-center">Total</td>
                        <td class="font-weight-bold text-center">Estado del pedido</td>
                        <td class="font-weight-bold text-center">Localidad</td>
                        <td class="font-weight-bold text-center">Calle</td>
                        <td class="font-weight-bold text-center">Altura</td>
                        <td class="font-weight-bold text-center">Acción</td>
                    </tr>

                    <c:forEach items="${listaPedidos}" var="pedidos">
                        <tr>
                            <td class="text-center ">${pedidos.id}</td>
                            <c:choose>
                                <c:when test="${pedidos.tiempoPreparacion == 0.0}">
                                    <td class="text-center ">Sin definir</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="text-center ">${pedidos.tiempoPreparacion} min</td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${pedidos.total == 0.0}">
                                    <td class="text-center ">Sin definir</td>
                                </c:when>
                                <c:otherwise>
                                    <td class="text-center">$${pedidos.total}</td>
                                </c:otherwise>
                            </c:choose>
                            <td class="text-center">${pedidos.estadoPedido}</td>
                            <td class="text-center">${pedidos.localidad}</td>
                            <td class="text-center">${pedidos.calle}</td>
                            <td class="text-center">${pedidos.altura}</td>
                            <c:choose>
                                <c:when test="${pedidos.estadoPedido == 'FINALIZADO'}">
                                    <td class="text-center">
                                        <form:form action="cambiar-estado" method="post">
                                            <div class="row">
                                                <div class="col-9 mx-0 px-0">
                                                    <select name="estado" disabled id="estado" class="form-control">
                                                        <option selected value="">Cambiar a ..</option>
                                                        <option value="PREPARANDO">PREPARANDO</option>
                                                        <option value="VIAJANDO">VIAJANDO</option>
                                                        <option selected value="FINALIZADO">FINALIZADO</option>
                                                    </select>
                                                </div>
                                                <div class="col mx-0 px-0">
                                                    <input type="hidden" name="idPedido" value="${pedidos.id}">
                                                    <input type="submit" class="btn btn-info" value="Ok">

                                                </div>
                                            </div>
                                        </form:form>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td  class="text-center">
                                        <form:form action="cambiar-estado" method="post">
                                            <div class="row">
                                                <div class="col-9 mx-0 px-0">
                                                    <select name="estado" id="estado" class="form-control">
                                                        <option selected value="">Cambiar a ..</option>
                                                        <option value="PREPARANDO">PREPARANDO</option>
                                                        <option value="VIAJANDO">VIAJANDO</option>
                                                        <option value="FINALIZADO">FINALIZADO</option>
                                                    </select>
                                                </div>
                                                <div class="col mx-0 px-0">
                                                    <input type="hidden" name="idPedido" value="${pedidos.id}">
                                                    <input type="submit" class="btn btn-info" value="Ok">
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
    <div class="container mt-5">
        <div class="alert alert-danger mt-3 " role="alert">
            <div class="row">
                <div class="col">
                    <p class="text-danger text-center">${listaPedidosVacia}</p>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${not empty pedidoError}">
    <div class="container mt-5">
        <div class="alert alert-danger" role="alert">
            <div class="row">
                <div class="col">
                    <p class="text-danger text-center">${pedidoError}</p>
                </div>
            </div>
        </div>
    </div>
</c:if>


<jsp:include page="/WEB-INF/includes/footer.jsp"/>