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
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb bg-info">
			<li class="breadcrumb-item active text-white" id="Barra" aria-current="page">
			    <img id='ImagenAlumnos' src='<c:url value="/estaticos/img/alumnos.png" />'/>

			    <div id='Texto'>
                <c:if test="${UsuarioConectado.getUsuarioAlumno() ==null}">
                    <script>
                    location.href="${pageContext.request.contextPath}/";
                    </script>
                </c:if>
			    <label id="UsuarioAlumnos">${UsuarioConectado.getUsuarioAlumno()}</label>

					<a href="${pageContext.request.contextPath}/logout" class="text-danger" >Cerrar Sesión</a>
				</div>
			</li>
		  </ol>
		</nav>
	</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<div class="card text-white bg-info mb-3" style="max-width: 18rem;min-height:20rem;">
					<div class="card-header bg-success ">Mis Materias</div>
						<div class="card-body bg-secondary">
							<p class="card-text ">
                                <div>
                                     <c:forEach var = "Ag" items="${Asignaturas_grupo}">
                                      <div id='Grupos'>
                                        <label>
                                           Nombre de la Asignatura:${Ag.getMateriaNombre()}
                                        </label>

                                      </div>

                                     </c:forEach>
                                </div>
							</p>

						</div>
					</div>

				</div>

			<div class="col-sm">
				<div class="card text-white  mb-3" style="background-color:blue;max-width: 18rem;min-height:20rem;">
					<div class="card-header">Mis Temarios</div>
						<div class="card-body "style="background-color:#87CEFA;">
							<p class="card-text " >

							</p>
						</div>
						
					</div>
			</div>
			<div class="col-sm">
				<div class="card text-white  mb-3" style="background-color:#778899;max-width: 18rem;min-height:20rem;">
					<div class="card-header">Mis Cuestionarios</div>
					<div class="card-body" style="background-color:#FFA07A;">
						<p class="card-text text-dark">Info card title</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>