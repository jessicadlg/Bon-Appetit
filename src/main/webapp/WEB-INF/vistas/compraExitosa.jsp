<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container py-5">
    <div class="row">
        <div class="col">
            <div class="modal fade d-block show" id="mimodal">
                <div class="modal-dialog modal-dialog-centered modal-lg  display-4">
                    <div class="modal-content ">
                        <!-- header o cabecera -->
                        <div class="modal-header bg-success">
                            <h4 class="small modal-title text-light">¡Compra realizada exitosamente!
                                <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="currentColor"
                                     class="bi bi-check2-circle" viewBox="0 0 16 16">
                                    <path d="M2.5 8a5.5 5.5 0 0 1 8.25-4.764.5.5 0 0 0 .5-.866A6.5 6.5 0 1 0 14.5 8a.5.5 0 0 0-1 0 5.5 5.5 0 1 1-11 0z"/>
                                    <path d="M15.354 3.354a.5.5 0 0 0-.708-.708L8 9.293 5.354 6.646a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l7-7z"/>
                                </svg>
                            </h4>
                        </div>
                        <!-- body o cuerpo -->
                        <div class="modal-body">
                            <p class="small ml-3">¡Su pedido ha sido despachado!</p>
                            <p class="small ml-5"> ¡Muchas gracias por elegirnos! <i
                                    class="nav-icon fas fa-handshake"></i></p>
                            <p class="text-center text-warning">¡¡Bon Appetit!!</p>
                        </div>
                        <!-- footer o pie de modal -->
                        <div class="modal-footer">
                            <a href="home" type="button" class="btn btn-secondary" data-dismiss="modal">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-arrow-counterclockwise" viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                          d="M8 3a5 5 0 1 1-4.546 2.914.5.5 0 0 0-.908-.417A6 6 0 1 0 8 2v1z"/>
                                    <path d="M8 4.466V.534a.25.25 0 0 0-.41-.192L5.23 2.308a.25.25 0 0 0 0 .384l2.36 1.966A.25.25 0 0 0 8 4.466z"/>
                                </svg>
                                Volver al inicio</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>