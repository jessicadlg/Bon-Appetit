<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="col">
            <h1 class="text-danger text-center">¡Confirme su pedido!</h1>
        </div>
    </div>
    <form action="" method="post">
        <div class="row">
            <div class="col">
                <input type="text" class="form-control" placeholder="Nombre">
            </div>

            <div class="col">
                <input type="text" class="form-control" placeholder="Telefono">
            </div>
        </div>

        <%--        <table class="table table-primary">--%>
        <%--            <tr>--%>
        <%--                <h1 class="text-center">Sus productos</h1>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>Nombre</td>--%>
        <%--                <td>Precio</td>--%>
        <%--                <td>Cantidad</td>--%>

        <%--            </tr>--%>
        <%--            --%>
        <%--           &lt;%&ndash; <c:forEach items="${pedido.listaProductos}" var="productos">--%>
        <%--                <tr>--%>
        <%--                    <div class="row">--%>
        <%--                        <div class="col">--%>
        <%--                            <td>${productos.nombre}</td>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                    <div class="col">--%>
        <%--                        <td>${productos.precio}</td>--%>
        <%--                    </div>--%>
        <%--                </tr>--%>
        <%--            </c:forEach>&ndash;%&gt;--%>
        <%--        </table>--%>
        <div class="row">
            <div class="col">
                <p class="text-dark">Total:</p>
                <input type="number" disabled class="form-control" value="total">
            </div>
            <div class="col">
                <p class="text-dark">Tiempo de preparación:</p>
                <input type="text" disabled class="form-control" value="tiempoPreparacion">
            </div>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>