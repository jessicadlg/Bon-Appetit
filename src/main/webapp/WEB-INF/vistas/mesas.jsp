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
                <h1 class="h2">Mesas</h1>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <a href="http://localhost:8081/Bon_Appetit_war/nuevoPedido" class="btn btn-sm btn-outline-secondary">
                        <!--span data-feather="calendar"></span-->
                        Cargar Mesa
                    </a>
                </div>
            </div>
            <!--h2>Section title</h2-->
            <c:if test="${not empty error}">
                <div class="alert alert-warning" role="alert">
                    ${error}
                </div>
            </c:if>
            <c:if test="${not empty mesas}">
                <div class="table-responsive">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th scope="col">Numero</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${mesas}" var="mesa">
                            <tr>
                                <td>${mesa.id}</td>
                                <td>${mesa.estado}</td>
                                <td>
                                    <button class="btn btn btn-outline-warning btn-sm">Agregar Producto</button>
                                    <button class="btn btn btn-outline-warning btn-sm">Pedir Precuenta</button>
                                    <button class="btn btn btn-outline-warning btn-sm">Cerra Mesa</button>
                                    <button class="btn btn btn-outline-warning btn-sm">Modificar Mesa</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
