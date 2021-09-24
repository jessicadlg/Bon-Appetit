<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 21/9/2021
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="card-header w-50 m-auto">
            <div class="col">
                <h1>Bienvenido a Bon-Appetit</h1>
                <!-- Boton del modal -->
                <button type="button" class="btn btn-primary" data-toggle="modal"
                        data-target="#mimodal">Ver menu
                </button>
                <!--El modal -->
                <div class="modal fade" id="mimodal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <!-- header o cabecera -->
                            <div class="modal-header">
                                <h4 class="modal-title">Menu</h4>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <!-- body o cuerpo -->
                            <div class="modal-body">
                                <form action="buscar-producto" method="get">
                                    <div class="row">
                                        <div class="col-7">
                                            <input type="text" class="form-control" name="nombreProducto"
                                                   id="nombreProducto">
                                        </div>
                                        <div class="col-2">
                                            <input type="submit" class="btn btn-success" value="Buscar">
                                        </div>
                                        <div class="col-3">
                                            <button type="button" class="btn btn-success dropdown-toggle"
                                                    data-toggle="dropdown">Filtros
                                            </button>
                                            <div class="dropdown-menu">
                                                <a href="productos-activos" class="dropdown-item">Listar productos
                                                    activos</a>
                                                <a href="#" class="dropdown-item">Listar productos inactivos</a>
                                                <a href="#" class="dropdown-item">Que se yo</a>
                                                <button type="button" class="btn dropdown-toggle ml-2"
                                                        data-toggle="dropdown">Listar por categoria
                                                </button>
                                                <div class="dropdown-menu">
                                                    <a href="productos-activos" class="dropdown-item">Vegano</a>
                                                    <a href="#" class="dropdown-item">Carne</a>
                                                    <a href="#" class="dropdown-item">Variado</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <c:if test="${not empty msgErrorProducto}">
                                    <h4><span>${msgErrorProducto}</span></h4>
                                    <br>
                                </c:if>
                                <c:if test="${not empty msgError}">
                                    <h4><span>${msgError}</span></h4>
                                    <br>
                                </c:if>
                                <c:if test="${not empty productoBuscado}">
                                    <h4><span>${productoBuscado.nombre}</span></h4>
                                    <br>
                                </c:if>
                                <c:forEach items="${listaProductos}" var="productos">
                                    <div class="card-header">
                                        <p>${productos.nombre}</p>
                                    </div>
                                </c:forEach>
                            </div>
                            <!-- footer o pie de modal -->
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"
        integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF"
        crossorigin="anonymous"></script>
</body>
</html>
