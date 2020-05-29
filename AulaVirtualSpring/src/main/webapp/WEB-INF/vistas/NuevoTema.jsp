<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html><meta charset='utf-8'>
    	<head>
    		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    		<link rel="stylesheet" type="text/css" href="<c:url value="/estaticos/estilos/diseño.css"/>">
    	</head>
    <body>

        <form action='${pageContext.request.contextPath}/NuevoTema' method='post' style='margin-left:10rem;'>
    		  <div class="form-group">
    			<label >Titulo del Tema</label>
    			<input type="text" class="form-control" name="tema_Name">

    		  </div>
    		   <div class="form-group">
    				<label >Resumen del Tema</label>
    				<textarea class="form-control"  rows="15"name="tema_Resumen" ></textarea>
    			</div>

    			<div class="container">
    				<div class="row">
    					<div class="col">
    						<label>Activar Tema</label>
    						<div class="form-check">
    							  <input class="form-check-input" type="radio" name="activar_Tema" id="exampleRadios1" value="true">
    							  <label class="form-check-label" for="exampleRadios1">
    								Si
    							  </label>

    						</div>
    						<br>
    						<div class="form-check">
    							  <input class="form-check-input" type="radio" name="activar_Tema" id="exampleRadios2" value="false">
    							  <label class="form-check-label" for="exampleRadios2">
    								No
    							  </label>
    						</div>

    					</div>
    					<div class="col">
    						<p>Numero de Tema:</p>
    						<p><input type='number' name="tema_Number"/></p>
    					</div>
    					<div class="col">
    						<p>Orden del Tema:</p>
    						<p><input type='number' name="tema_order"/></p>
    					</div>

    				</div>

    			</div>
    			<br>
    			<br>

    			<p><button type="submit" name="Agregar" 	class="btn btn-primary btn-lg" style='margin-left:10rem;width:20rem;'>Registrar Tema</button>
    			&nbsp;&nbsp;

    			<a class="btn btn-secondary btn-lg " href="${pageContext.request.contextPath}/homeProfesores" role="button" style="width:20rem;">Atras</a></p>
    		</form>
    	</body>
</html>