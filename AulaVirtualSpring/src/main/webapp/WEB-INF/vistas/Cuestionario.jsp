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
	    <form action="${pageContext.request.contextPath}/AgregarCuestionario/" method='post' style='margin-left:10rem;margin-top:1rem;'>
	        <div class="form-group">
                <label>Titulo del Examen</label>
                <p><input type="text" class="form-control" name="Title_Examen"></p>
            </div>
            <div class="form-group">
                <label >Instrucciones del Examen</label>
                <textarea class="form-control" rows="5" name="Instrucciones_Examen" ></textarea>
            </div>
            <p><button type="submit" name="Agregar" 	class="btn btn-primary btn-lg" style='margin-left:10rem;width:20rem;'>Registrar Cuestionario</button>
                &nbsp;&nbsp;
            <a class="btn btn-secondary btn-lg " href="${pageContext.request.contextPath}/homeProfesores" role="button" style="width:20rem;">Atras</a></p>
	    </form>
	</body>
</html>