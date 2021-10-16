<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container-fluid pt-5">
    <!-- seccion listado y destacados-->
    <div class="container p-5">
        <h1 class="text-center py-4">Nuestro menú</h1>
        <div class="row">
            <div class="col-md-9">
                <h3 class="py-4 ">Nuestras Categorias</h3>
                <%-- Desplegable--%>
                <div class="accordion" id="accordion">
                <c:forEach items="${listaCategorias}" var="categorias">
                <!-- <botones categorias > -->
                    <div class="card-header" id="headingThree">
                        <button class="btn btn-link collapsed fw-bolder" data-toggle="collapse"
                                data-target="#${categorias.id}" aria-expanded="false" aria-controls="${categorias.id}">
                            <h3><a class="text-dark">${categorias.nombreCategoria}</a></h3>
                        </button>
                    </div>
                    <!-- categoria desplegables de productos -->

                    <div id="${categorias.id}" class="collapse col-md-12 my-4 " aria-labelledby="headingThree"
                         data-parent="#accordion">
                        <div class="row d-flex">
                            <section class="col-md-6 p-2">
                                <c:forEach items="${listaProductos}" var="productos">
                                    <c:if test="${productos.categoria.id == categorias.id}">
                                        <div class="card" style="width: 18rem;">
                                            <div class="card-body d-inline-flex">
                                                <img src="images/${productos.nombreImagen}" alt="" class="rounded"
                                                     width="100" height="100">
                                                <div class="d-flex flex-column">
                                                    <h5 class="card-title text-center">${productos.nombre}</h5>
                                                    <!-- Product price-->
                                                    <span class="text-center p-1 ">$${productos.precio}</span>
                                                </div>
                                            </div>
                                            <!-- accion ir a ver mas-->
                                            <div class="card-footer pt-0 border-top-0 bg-transparent">
                                                <a class="btn btn-block btn-outline-dark mt-auto"
                                                   href="detalleProducto?id=${productos.id}">Ir
                                                    al producto</a>
                                            </div>
                                            <a href="darMeGusta?id=${productos.id}" class="d-sm-block btn btn-sm btn-primary m-3 ">
                                                Me gusta <span class="badge badge-light">${productos.cantidadMeGusta}</span>
                                            </a>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </section>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-md-3 ">
                <!-- seccion destacados-->
                <section class="container">
                    <aside>
                        <h3 class="py-4">Destacados</h3>
                        <c:forEach items="${destacados}" var="productos">
                            <div class="row  justify-content-center">
                                <div class="col mb-4">
                                    <div class="card h-100">
                                        <!-- Product image -->
                                        <img src="images/${productos.nombreImagen}" class=" img-thumbnail img-fluid"
                                             alt="..."/>
                                        <!-- Product details-->
                                        <div class="card-body p-3">
                                            <div class="text-center">
                                                <!-- Product name-->
                                                <h5 class="">${productos.nombre}</h5>
                                                <!-- Product price-->
                                                <span class="tachado text-danger">$35.00</span>
                                                <span class="fw-bolder text-success">$${productos.precio}</span>
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
                </section>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>