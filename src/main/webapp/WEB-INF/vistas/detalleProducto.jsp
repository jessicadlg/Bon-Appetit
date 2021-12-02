<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<section class="bg-light pb-5">

<c:if test="${not empty productoDetalles}">
    <div class="container py-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-4 offset-1">
                <img src="images/${productoDetalles.nombreImagen}"
                     class=" productoImg img-thumbnail img-fluid mb-5 mb-md-0" alt="...">
            </div>
            <div class="col-md-6">
                <div class="d-flex mb-4 justify-content-between align-items-center">
                    <h3 class="text-start">${productoDetalles.nombre}</h3>
                    <div class="d-flex">
                        <span class="text-success font-weight-bold pr-2">$${productoDetalles.precio}</span>
                        <small class="badge bg-warning  d-flex justify-content-between align-items-center">
                            <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12"
                                 fill="currentColor" class="bi bi-star-fill"
                                 viewBox="0 0 16 16">
                                <path d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"/>
                            </svg>
                            4.5</small>

                    </div>
                </div>
                <cite title="Source Title">${productoDetalles.descripcion} </cite>

                <div class="row align-items-center">
                    <div class="d-flex my-3 mx-auto">
                        <a href="#" class="d-sm-block btn btn-sm btn-info m-3">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-cart4 m-1" viewBox="0 0 16 16">
                                <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                            </svg>
                            Agregar al carrito
                        </a>
                        <!-- boton static toma el numerito-->
                        <a href="darMeGustaDetalle?id=${productoDetalles.id}"
                           class="d-sm-block btn btn-sm btn-primary m-3 ">
                            Me gusta <span class="badge badge-light">${productoDetalles.cantidadMeGusta}</span>
                        </a>

                    </div>
                </div>
                <div class="row justify-content-center">
                    <a href="listarProductos" class="btn btn-secondary ">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
                                  d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                            <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                        </svg>
                        Volver
                    </a>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    <c:if test="${not empty productoNoEncontrado}">
        <div class=" container-fluid p-5 m-5" role="main">
            <div class="row">
                <div class="col-md-10 col-sm-12 mx-auto">
                    <div class="alert alert-danger alert-dismissible " role="alert">
                        <p class="text-center">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-x-octagon-fill" viewBox="0 0 16 16">
                                <path d="M11.46.146A.5.5 0 0 0 11.107 0H4.893a.5.5 0 0 0-.353.146L.146 4.54A.5.5 0 0 0 0 4.893v6.214a.5.5 0 0 0 .146.353l4.394 4.394a.5.5 0 0 0 .353.146h6.214a.5.5 0 0 0 .353-.146l4.394-4.394a.5.5 0 0 0 .146-.353V4.893a.5.5 0 0 0-.146-.353L11.46.146zm-6.106 4.5L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z"/>
                            </svg>
                                ${productoNoEncontrado}
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class=" mx-auto">
                    <a href="listarProductos" class="btn btn-secondary">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                            <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                        </svg>
                        Volver
                    </a>
                </div>
            </div>
        </div>
    </c:if>
</section>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>
