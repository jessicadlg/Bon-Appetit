<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container-fluid producto">
    <!-- Product Aquarius-->
    <section class="container py-5">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-4">
                <img src="images/${productoDetalles.nombreImagen}"
                     class=" productoImg img-thumbnail img-fluid mb-5 mb-md-0" alt="...">
            </div>
            <div class="col-md-6">
                <h1 class="display-5 fw-bolder">${productoDetalles.nombre}</h1>
                <div class="mb-5">
                    <span class="tachado">$35.00</span>
                    <span class="fw-bolder">$${productoDetalles.precio}</span>
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
                        <a href="darMeGusta?id=${productoDetalles.id}" class="d-sm-block btn btn-sm btn-primary m-3 ">
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

    <!-- Testimonio footeer-->
    <div class=" bg-secondary py-1">
        <div class="container">
            <div class="row gx-5 justify-content-center">
                <div class="col-lg-10 col-xl-7">
                    <div class="text-center">
                        <div class="fs-4 mb-4 fst-italic">"Working with Start Bootstrap templates has saved me tons of
                            development time when building new projects! Starting with a Bootstrap template just makes
                            things easier!"
                        </div>
                        <div class="d-flex align-items-center justify-content-center">
                            <img class="rounded-circle me-3" src="images/testimonial.png" alt="..."/>
                            <div class="fw-bold">
                                Tom Ato
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>