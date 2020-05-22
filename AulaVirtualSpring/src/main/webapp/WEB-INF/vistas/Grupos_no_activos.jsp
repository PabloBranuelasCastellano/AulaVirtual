<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html><meta charset='utf-8'>
    <head>
    	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    		<link rel="stylesheet" type="text/css" href="<c:url value="/estaticos/estilos/diseño.css"/>">
    </head>
    <body>
        <c:choose>

            <c:when test="${GruposDesactivados.size() gt 0}">
                <table class='table table-bordered'>
                    <thead>
                        <tr class='bg-primary'>
                            <th scope='col'>Año Academico</th>
                            <th scope='col'>Nombre Grupo</th>
                            <th scope='col'>Asignatura</th>
                            <th scope='col'>Nivel Educativo</th>
                            <th scope='col'>Profesor que lo imparte</th>
                        </tr>
                    </thead>
                    <tbody class='bg-info'>
                        <c:forEach var = "gruposfalse" items="${GruposDesactivados}">
                            <tr>
                                <td>${gruposfalse.getCursoAcademicoGrupo()}</td>
                                <td>${gruposfalse.getNombreGrupo()}</td>
                                <td>${gruposfalse.getNombreMateria()}</td>
                                <td>${gruposfalse.getNivelGrupo()}</td>
                                <td>${gruposfalse.getProfesorGrupo()}</td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>NO hay ningun grupo desactivado</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>