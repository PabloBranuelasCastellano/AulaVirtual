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
		<form action='${pageContext.request.contextPath}/AgregarPuntos' method='post' style='margin-left:10rem;'>
		  <div class="form-group">
			<label >Titulo del Punto</label>
			<input type="text" class="form-control" name="point_Name">

		  </div>
		   <div class="form-group">
				<label >Resumen del Punto</label>
				<textarea class="form-control"  rows="15"name="point_Resumen" ></textarea>
			</div>

			<div class="form-group">
				<label >Texto del Punto</label>
				<textarea class="form-control"  rows="15"name="point_text" ></textarea>
			</div>
			<div class="container">
				<div class="row">
					<div class="col">
						<label>Activar Punto</label>
						<div class="form-check">
							  <input class="form-check-input" type="radio" name="activar_Punto" id="exampleRadios1" value="true">
							  <label class="form-check-label" for="exampleRadios1">
								Si
							  </label>

						</div>
						<br>
						<div class="form-check">
							  <input class="form-check-input" type="radio" name="activar_Punto" id="exampleRadios2" value="false">
							  <label class="form-check-label" for="exampleRadios2">
								No
							  </label>
						</div>

					</div>
					<div class="col">
						<p>Numero de Punto:</p>
						<p><input type='number' name="point_Number"/></p>
					</div>
					<div class="col">
						<p>Orden del Tema:</p>
						<p><input type='number' name="point_order"/></p>
					</div>

				</div>

			</div>
			<br>
			<br>


			<button type="submit" name="Agregar" 	class="btn btn-primary btn-lg btn-block" >Registrar Punto</button>
		</form>
	</body>


</html>