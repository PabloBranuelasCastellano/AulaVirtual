<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html><meta charset='utf-8'>
    <head>
    		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    		<link rel="stylesheet" type="text/css" href="<c:url value="/estaticos/estilos/diseño.css"/>">
    	</head>
    <body>
        <c:choose>
            <c:when test="${PreguntasCuestionario.size() gt 0}">
                <ul class='list-group'>
                    <c:forEach var = "ListPreguntas" items="${PreguntasCuestionario}">

                        <li class='list-group-item list-group-item-action list-group-item-primary'>${ListPreguntas.getTexto()}<a class='btn btn-primary'  href='${pageContext.request.contextPath}/AgregarOpciones/${ListPreguntas.getPreguntaId()}' role='button'  style='margin-left:-12%;margin-top:-3%;'>Añadir Respuestas</a>&nbsp;&nbsp;<a class='btn btn-primary' role='button' href='${pageContext.request.contextPath}/ActivarPregunta/${ListPreguntas.getPreguntaId()}' role='button' aria-expanded='false' aria-controls='multiCollapseExample1' style='margin-left:50%;margin-top:3%;'>Activar Pregunta Alumnos</a> &nbsp;&nbsp;<a class='btn btn-primary' href='${pageContext.request.contextPath}/DesactivarPregunta/${ListPreguntas.getPreguntaId()}' role='button' style='width:20%;margin-left:45%;margin-top:-3%;'>Desactivar Pregunta Alumnos</a>
                        </li>

                    </c:forEach>
                </ul>
            </c:when>

            <c:otherwise>
                <h1>No hay Preguntas para mostrar</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>