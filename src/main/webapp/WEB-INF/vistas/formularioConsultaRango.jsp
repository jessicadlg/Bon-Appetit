<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/includes/header.jsp"/>

<div class="container">
    <form action="consultarRango" method="POST">
        <div class="row">
            <div class="col">
                <select name="calle" id="calle" class="form-control">
                    <option value="" selected disabled="disabled">Ingrese una opcion...</option>
                    <option value=""></option>
                    <option value=""></option>
                    <option value=""></option>
                </select>
            </div>
            <div class="col">
                <input type="text" class="form-control" placeholder="Altura">
            </div>
        </div>
        <div class="row">
            <div class="col-6 mx-auto mt-3">
                <input type="submit" value="consultarRango"
                       class="btn btn-block form-control btn-outline-info">
                <c:if test="${ not empty errorConsultaRango}">
                    <p>${errorConsultaRango}</p>
                </c:if>
            </div>

        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/includes/footer.jsp"/>

