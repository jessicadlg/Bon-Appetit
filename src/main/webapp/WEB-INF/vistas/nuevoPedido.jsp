<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<jsp:include page="/WEB-INF/includes/head.jsp"/>
<jsp:include page="/WEB-INF/includes/headerApp.jsp"/>
<div class="container-fluid">
    <div class="row">
        <jsp:include page="/WEB-INF/includes/sideBarMenu.jsp"/>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 class="h2">Cargar Pedido</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="#" class="btn btn-sm btn-outline-secondary">
                        <!--span data-feather="calendar"></span-->
                        Confirmar
                    </a>
                </div>
            </div>
            <!--h2>Section title</h2-->
            <div>
                <form action="http://localhost:8081/Bon_Appetit_war/pedido" method="get">
                    <div class="mb-3">
                        <input type="hidden" value="${pedido.id}" name="pedido">
                        <label for="formGroupExampleInput" class="form-label">Seleccione los Productos</label>
                        <select  name="producto" class="form-select" id="formGroupExampleInput">
                            <c:forEach items="${productos}" var="producto">
                                <option value="${producto.id}">${producto.codigo}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-sm btn-outline-secondary">Agregar</button>
                </form>
            </div>
            <c:if test="${not empty pedido.productosPedidos}">
                <h2>Productos Agregados</h2>
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Codigo</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Cantidad</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pedido.productosPedidos}" var="producto">
                            <tr>
                                <td>${producto.codigo}</td>
                                <td>${producto.precio}</td>
                                <td></td>
                                <td>
                                    <button class="btn btn btn-outline-warning">Eliminar</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <h3>Total</h3>
                </div>
            </c:if>
        </main>
    </div>
</div>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
<script src="js/dashboard.js"></script>
</body>
</html>
