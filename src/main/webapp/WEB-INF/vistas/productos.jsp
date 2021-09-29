<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="#page-top"><img src="images/logoB.png" class="img-logo" alt="Bon_appetit"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars ms-1"></i>
        </button>
    </div>
</nav>
<!-- Masthead-->
<header class="masthead">
    <div class="masthead-heading">Bienvenido a Bon-Appetit!</div>
    <!-- Boton del modal -->
    <button type="button" class="btn btn-dark btn-lg" data-toggle="modal"
            data-target="#mimodal">Ver menú
    </button>
</header>
<div class="modal fade" id="mimodal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!--                                 header o cabecera -->
            <div class="modal-header">
                <h4 class="modal-title">Menu</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!--                              body o cuerpo -->
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
                        <div class="row">
                            <div class="col-6">
                                <img class="img-thumbnail img-fluid" src="images/${productos.nombreImagen}" alt="..">
                            </div>
                            <div class="col-6 ml-auto display-4">
                                <p>$${productos.precio}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:forEach items="${listaCategorias}" var="categorias">
                <div class="accordion" id="accordionExample">
                    <div class="card">
                        <div class="card-header" id="headingOne">
                            <h2 class="mb-0">
                                <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                        data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        ${categorias.nombreCategoria}
                                </button>
                            </h2>
                        </div>
                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                             data-parent="#accordionExample">
                            <div class="card-body">
                                <div class="img-prod">
                                    <img class="img-logo" src="images/${c}" alt="..">
                                </div>
                                <div class="title-nombre">
                                        <%--                                    <h4>${categorias.nombreCategoria}</h4>--%>
                                        <%--                                    <c:forEach items="${categorias.listaProductos}" var="productos">--%>
                                        <%--                                        <h4>${productos.nombre}</h4>--%>
                                        <%--                                    </c:forEach>--%>
                                    <h4>Yo puse mis productos aqui.jpg</h4>
                                    <img src="images/gatito.jpg" alt="">
                                </div>
                                <small>Some placeholder content for the first accordion panel. This panel is shown by
                                    default,
                                    thanks to the</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>