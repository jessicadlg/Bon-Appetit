<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<c:if test="${not empty productoDetalles}">
<div class="container-fluid">
    <!-- Product Aquarius-->
    <section class="container py-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-4">
                <img src="images/${productoDetalles.nombreImagen}"
                     class=" productoImg img-thumbnail img-fluid mb-5 mb-md-0" alt="...">
            </div>
            <div class="col-md-6">
                <h1 class="display-5 fw-bolder text-center">${productoDetalles.nombre}</h1>
                <div class="mb-5 text-center">
                    <span class="tachado text-danger">$35.00</span>
                    <span class="fw-bolder text-success">$${productoDetalles.precio}</span>
                </div>
                <p class="lead">Lorem ipsum dolor sit amet consectetur adipisicing elit. Praesentium at dolorem
                    quidem
                    modi. Nam sequi consequatur obcaecati excepturi alias magni, accusamus eius blanditiis delectus
                    ipsam minima ea iste laborum vero?</p>
                <div class="row align-items-center">
                    <div class="d-flex mx-auto">
                        <a href="#" class="d-sm-block btn btn-sm btn-info m-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-cart4 m-1" viewBox="0 0 16 16">
                                <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                            </svg>
                            Agregar al carrito
                        </a>
                        <!-- boton static toma el numerito-->
                        <a href="darMeGustaDetalle?id=${productoDetalles.id}" class="d-sm-block btn btn-sm btn-primary m-3 ">
                            Me gusta <span class="badge badge-light">${productoDetalles.cantidadMeGusta}</span>
                        </a>
                        <%--<a href="#" class="d-sm-block btn btn-sm btn-success m-3">
                            Me gusta con contador <span class="badge badge-light">${productoDetalles.cantidadMeGusta}</span>
                        </a>--%>
                    </div>
                </div>
            </div>
        </div>
    </section>
    </div>
</c:if>
<div class="container">
    <div class="row">
        <div class="col">
            <p class="text-center display-4"><c:if test="${not empty productoNoEncontrado}">${productoNoEncontrado}</c:if></p>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>