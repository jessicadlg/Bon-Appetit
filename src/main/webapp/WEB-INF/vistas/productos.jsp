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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <!-- Boton del modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal"
                    data-target="#mimodal">CONSULTA
            </button>
            <!--El modal -->
            <div class="modal fade" id="mimodal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <!-- header o cabecera -->
                        <div class="modal-header">
                            <h4 class="modal-title">Cabecera</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <!-- body o cuerpo -->
                        <div class="modal-body">
                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Filtros</button>
                            <div class="dropdown-menu">
                                <!-- creamos otro div donde definimos que será el contenedor del menu con la clase dropdown-menu
                                y agregamos los items que querramos definiendo que son items con la clase dropdown-item -->
                                <a href="productos-activos" class="dropdown-item">Listar productos activos</a>
                                <a href="#" class="dropdown-item">Listar productos inactivos</a>
                                <a href="#" class="dropdown-item">Que se yo</a>
                            </div>
                            <!-- todo lo del modal-body seria el contenido del modal -->
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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
</body>
</html>
