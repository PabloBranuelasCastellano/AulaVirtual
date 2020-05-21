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
		<p><h1 id="Cabecera"><u>INICIO DE SESIÓN</u></h1></p>

		<form action="${pageContext.request.contextPath}/Comprobar" method="post">
		  <div class="form-group">
			<p id="Encabezado">Correo del Usuario</p>
			<input type="text" id="email" name="Email_Acceso" class="form-control Entrada_Datos">

		  </div>

		  <div class="form-group">
			<p id="Encabezado">Password</p>
			<input type="password" id="pass" name="Clave_Acceso" class="form-control Entrada_Datos">
		  </div>

		  <button type="submit" name="Enviar" 	class="btn btn-primary btn-lg btn-block" >Submit</button>
		</form>
	</body>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

	<script>
        $( document ).ready(function() {

            if('${errorLogin}'!==""){
                alert('${errorLogin}');
            }
            if('${fromLogout}'==="1"){
                window.location.href="${pageContext.request.contextPath}/";
            }


        });
    </script>



</html>