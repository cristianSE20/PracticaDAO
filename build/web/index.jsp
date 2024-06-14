<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Entidades</title>
</head>
<body>
    <h1>Lista de Entidades</h1>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre de la Entidad</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listEntidad}" var="entidad">
                <tr>
                    <td>${entidad.idEntidad}</td>
                    <td>${entidad.nombreEntidad}</td>
                    <td>
                        <a href="EntidadServlet?action=edit&id=${entidad.idEntidad}">Editar</a>
                        <a href="EntidadServlet?action=delete&id=${entidad.idEntidad}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="form.jsp">Agregar nueva entidad</a></p>
</body>
</html>
