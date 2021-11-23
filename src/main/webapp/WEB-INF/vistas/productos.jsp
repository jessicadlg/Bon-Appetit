<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <h1 class="text-center mt-3">Nuestro menú</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-6 offset-1">
            <h3 class="py-4">Nuestras Categorias</h3>
            <div class="accordion" id="accordion">
                <c:forEach items="${listaCategorias}" var="categorias">
                    <div class="row">
                        <div class="col">
                            <!-- <botones categorias > -->
                            <div class="card-header" id="headingThree">
                                <button class="btn btn-link collapsed fw-bolder" data-toggle="collapse"
                                        data-target="#${categorias.id}" aria-expanded="false"
                                        aria-controls="${categorias.id}">
                                    <h3><a class="text-dark">${categorias.nombreCategoria}</a></h3>
                                </button>
                            </div>
                        </div>
                    </div>

                    <!-- categoria desplegables de productos -->
                    <div class="row">
                        <div class="col">
                            <div id="${categorias.id}" class="collapse" aria-labelledby="headingThree">
                                <c:forEach items="${listaProductos}" var="productos">
                                    <c:if test="${productos.categoria.id == categorias.id}">
                                        <div class="card-body border">
                                            <div class="row">
                                                <div class="col-4">
                                                    <img src="images/${productos.nombreImagen}" alt=""
                                                         class="rounded img-thumbnail img-fluid"
                                                    >
                                                </div>
                                                <div class="col-8 d-flex justify-content-between">
                                                    <h3 class="card-title text-dark  text-italic">${productos.nombre}</h3>
                                                    <div class="row">
                                                        <div class="col">
                                                            <h4 class="card-title text-dark">
                                                                $${productos.precio}</h4>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row  mt-3">
                                                <div class="col">
                                                    <div class="card-footer pt-0 border-top-0 bg-transparent">
                                                        <a class="d-sm-block btn btn-sm btn-outline-dark "
                                                           href="detalleProducto?id=${productos.id}">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                 height="16"
                                                                 fill="currentColor" class="bi bi-arrow-up-right"
                                                                 viewBox="0 0 16 16">
                                                                <path fill-rule="evenodd"
                                                                      d="M14 2.5a.5.5 0 0 0-.5-.5h-6a.5.5 0 0 0 0 1h4.793L2.146 13.146a.5.5 0 0 0 .708.708L13 3.707V8.5a.5.5 0 0 0 1 0v-6z"/>
                                                            </svg>
                                                            Ir
                                                            al producto</a>
                                                    </div>
                                                </div>
                                                <div class="col">
                                                    <c:if test="${not empty idPedido}">
                                                        <a href="darMeGustaPedido?id=${productos.id}&idPedido=${idPedido}"
                                                           class="d-sm-block btn btn-sm btn-primary">
                                                            Me gusta <span
                                                                class="badge badge-light">${productos.cantidadMeGusta}</span>
                                                        </a>
                                                    </c:if>
                                                    <c:if test="${empty idPedido}">
                                                        <a href="darMeGusta?id=${productos.id}"
                                                           class="d-sm-block btn btn-sm btn-primary">
                                                            Me gusta <span
                                                                class="badge badge-light">${productos.cantidadMeGusta}</span>
                                                        </a>
                                                    </c:if>
                                                </div>
                                                <c:if test="${not empty idPedido}">
                                                    <div class="col">
                                                        <a href="agregar-producto?idProducto=${productos.id}&idPedido=${idPedido}"
                                                           class="d-sm-block btn btn-sm btn-info">
                                                            <svg xmlns="http://www.w3.org/2000/svg" width="16"
                                                                 height="16" fill="currentColor"
                                                                 class="bi bi-cart4 mx-1" viewBox="0 0 16 16">
                                                                <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                                                            </svg>
                                                            Agregar al carrito
                                                        </a>
                                                    </div>
                                                </c:if>
                                            </div>


                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="col-2">
            <h3 class="text-center py-4">Destacados</h3>
            <aside>
                <c:forEach items="${destacados}" var="productos">
                    <div class="row  justify-content-center">
                        <div class="col mb-4">
                            <div class="card h-100">
                                <!-- Product image -->
                                <img src="images/${productos.nombreImagen}"
                                     class="img-thumbnail img-fluid"
                                     alt="..."/>
                                <!-- Product details-->
                                <div class="card-body p-3">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="">${productos.nombre}</h5>
                                        <!-- Product price-->
                                        <span class="text-success">$${productos.precio}</span>
                                    </div>
                                </div>
                                <!-- accion ir a ver detalle-->
                                <div class="card-footer p-2 pt-0 border-top-0 bg-transparent  mx-auto">
                                    <a class="btn btn-outline-success"
                                       href="detalleProducto?id=${productos.id}">Ver detalle</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </aside>
        </div>
        <c:if test="${not empty pedido}">
            <div class="col-3">
                <h3 class="py-4">Su Pedido:</h3>
                <c:if test="${not empty pedidoVacio}">
                    <div class="row">
                        <div class="col">
                            <div class="card" style="width: 20rem;">
                                <h3 class="">${pedidoVacio}</h3>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty pedidoVacio}">
                    <div class="row">
                        <div class="col">
                            <c:forEach items="${itemsPedido}" var="productos">
                                <div class="card">
                                    <div class="row">
                                        <div class="col-5">
                                            <!-- Product image -->
                                            <img src="images/${productos.producto.nombreImagen}"
                                                 class="img-thumbnail img-fluid"
                                                 alt="..."/>
                                            <div class="row">
                                            </div>
                                        </div>
                                        <div class="col-5">
                                            <!-- Product name-->
                                            <h5 class="">${productos.producto.nombre}</h5>
                                            <!-- Product details-->
                                            <span class="font-weight-light">Cantidad: ${productos.cantidad}</span>
                                            <span class="font-weight-light">Precio: $${productos.producto.precio}</span>
                                        </div>
                                        <div class="col-1">
                                            <a href="eliminar-producto?idProducto=${productos.producto.id}&idPedido=${pedido.id}">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            <div class="row">
                                <div class="col">
                                    <h4>Su total: ${pedido.total}</h4>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">
                                    <a href="confirmarPedido?idPedido=${pedido.id}" class="btn btn-primary btn-block">Confirmar
                                        pedido</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </c:if>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>