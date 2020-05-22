<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html><meta charset='utf-8'>
    <body>
        <c:if test="${Lista_Alumnos.size() gt 0}">
            <c:forEach var = "galumns" items="${Lista_Alumnos}">
                <p>${galumns.getNombreAlumno()}&nbsp;&nbsp;${galumns.getPrimerApellidoAlumno()} &nbsp;&nbsp;${galumns.getSegundoApellidoAlumno()}</p>
             </c:forEach>
        </c:if>
    </body>
</html>