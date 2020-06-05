<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		// $Ver_Dia="select curdate()";
		// $Mostrar=mysqli_query($conexion,$Ver_Dia);
		// $fecha=mysqli_fetch_row($Mostrar);
		// echo($fecha[0]);
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Actual=$_SESSION["ProfesorId"];
		if($Usuario_Actual=="" or $Usuario_Actual==null){
			header("Location:index.php");
		}
		
	?>
		
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Estilos/diseño.css">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb bg-info">
			<li class="breadcrumb-item active text-white" id="Barra" aria-current="page"><?PHP 
				echo("<img id='Imagen' src='Estilos/profesores.png'/>");
				echo("<div id='Texto'>");
				echo("<p>$Usuario_Actual</p>");
				?>
				<form action='PanelProfesores.php'method="post">
					<a href="?Cerrar=true" class="text-danger" >Cerrar Sesión</a>
				</form>
				<?php
					echo("</div>");
				?>
			</li>
		  </ol>
		</nav>	
	</head>
	
	<?php
		
		if(isset($_GET['Cerrar'])){
			session_unset();
			session_destroy();
			header("Location:index.php");
		}
		// if(isset($_GET['Ocultar'])){
			// $Id_Materia=$_GET['Id_Activo'];
			// echo($Id_Materia);
			// $Cambiar_Estado="update materias set EsActiva=false where MateriaId=$Id_Materia";
			// $Desactivar=mysqli_query($conexion,$Cambiar_Estado);
			// header("Location:PanelProfesores.php");
			
		}
		if(isset($_GET['Quitar'])){
			$Id_Materia=$_GET['Id_Activo'];
			$Eliminar_Tema="delete from materias where MateriaId=$Id_Materia";
			$Quitar_Tema=mysqli_query($conexion,$Eliminar_Tema);
			header("Location:PanelProfesores.php");
			
		}
	?>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<div class="card text-white bg-info mb-3" style="max-width: 18rem;min-height:20rem;">
					<div class="card-header bg-success ">Mis Grupos</div>
						<div class="card-body bg-secondary">
							<p class="card-text ">
							<?php
								$VerGrupos="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura ,N.Denominacion as NivelEducativo ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and m.EsActiva=true and P.ProfesorId=$Id_Actual )";
								$Grupos=mysqli_query($conexion,$VerGrupos);
								echo("<div>");
								while($fila=mysqli_fetch_array($Grupos)){
									echo("<p> Año:".$fila["AnioEscolar"]."</p><p>".$fila["NombreGrupo"]."&nbsp;".
										$fila["Asignatura"]."&nbsp;".$fila["NivelEducativo"]."\r\n </p>");
										echo("<a href='Alumnos_Grupo.php?Id_Activo=".$fila["GrupoId"]."'><img src='Estilos/flecha.png' id='flecha' title='Ver Lista de Alumnos del Grupo'/></a>");
								}
								
								echo ("</div>");
							?>
							</p>
							
						</div>
						<p class="card-text bg-secondary"><a href="Grupos_no_activos.php"class="text-white"style="margin-left:40%;"><u>Ver grupos no activos</a></u></p>
							
						
					</div>
					
				</div>
			<div class="col-sm">
				<div class="card text-white  mb-3" style="background-color:blue;max-width: 18rem;min-height:20rem;">
					<div class="card-header">Mis Temarios</div>
						<div class="card-body "style="background-color:#87CEFA;">
							<p class="card-text " >
							<?php
								$VerMaterias="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura,M.MateriaId ,N.Denominacion as NivelEducativo,n.NivelId ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and m.EsActiva=true and P.ProfesorId=$Id_Actual)";
								$Temario=mysqli_query($conexion,$VerMaterias);
								
								echo("<div>");
								while($fila=mysqli_fetch_array($Temario)){
									echo("<p style='color:black;'><a href='VerTema.php?Id_Activo=".$fila["MateriaId"]."'style='color:black;'>Asignatura:".$fila["Asignatura"]."</p></a><p style='color:black;'>".$fila["NivelEducativo"]."</p>");
									echo("<a href='Editar_Tema.php?Id_Materia=".$fila["MateriaId"]."&Id_Nivel=".$fila["NivelId"]."'><img src='Estilos/lapiz.png' id='lapiz' title='Editar Tema'/>");
									// echo("</a><a href='?Ocultar=true&Id_Activo=".$fila["MateriaId"]."'><img src='Estilos/ocultar.png' id='ocultar' title='Ocultar Tema'/></a>");
									echo("<a href='?Quitar=true&Id_Activo=".$fila["MateriaId"]."&Nivel_Id=".$fila["NivelId"]."'><img src='Estilos/papelera.png' id='papelera' title='Eliminar Tema'/></a>");
								}
								echo("<a href='Agregar_Tema.php'style='color:red;'>Añadir nuevo temario</a>");
								
								echo ("</div>");
							?>
							</p>
						</div>
						<p class="card-text" style="background-color:#87CEFA;"><a href="materias_no_activas.php" style="margin-left:40%;color:black;"><u>Ver temario no activo</a></u></p>
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