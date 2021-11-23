<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container mask">
    <div class="row">
        <div class="col">
            <div class="modal fade d-block show bg-dark" id="mimodal">
                <div class="modal-dialog modal-dialog-centered modal-lg display-4">
                    <div class="modal-content">
                        <!-- header o cabecera -->
                        <div class="modal-header">
                            <h4 class="small modal-title text-success">¡Compra realizada con exito!</h4>
                        </div>
                        <!-- body o cuerpo -->
                        <div class="modal-body">
                            <p class="small ml-3">¡Su compra fue realizada con exito!</p>
                               <p class="small ml-5"> ¡Muchas gracias por elegirnos!</p>
                        <p class="text-center text-warning">¡¡Bon Appetit!!</p>
                        </div>
                        <!-- footer o pie de modal -->
                        <div class="modal-footer">
                            <a href="home"type="button" class="btn btn-danger" data-dismiss="modal">Cerrar compra</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>