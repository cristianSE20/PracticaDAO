<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario de Entidad</title>
</head>
<body>
    <h1>Formulario de Entidad</h1>
    <form action="EntidadServlet" method="post">
        <c:if test="${entidad != null}">
            <input type="hidden" name="idEntidad" value="${entidad.idEntidad}" />
        </c:if>
        <div>
            <label>Nombre de la Entidad:</label>
            <input type="text" name="nombreEntidad" value="${entidad != null ? entidad.nombreEntidad : ''}" required />
        </div>
        <div>
            <input type="submit" value="Guardar" />
            <a href="EntidadServlet?action=list">Cancelar</a>
        </div>
        <c:if test="${entidad != null}">
            <input type="hidden" name="action" value="update" />
        </c:if>
        <c:if test="${entidad == null}">
            <input type="hidden" name="action" value="insert" />
        </c:if>
    </form>
</body>
</html>