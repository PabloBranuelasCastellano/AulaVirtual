<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Actual=$_SESSION["ProfesorId"];
		if($Usuario_Actual=="" or $Usuario_Actual==null){
			header("Location:index.php");
		}
		
		
	?>
		
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Estilos/diseño.css">
	</head>
	<body>
		<?php
			// $VerTemas="select t.TemaId,t.Titulo ,n.Denominacion from materias m,temas t,niveles n,profesores p where (t.NivelId =n.NivelId and p.ProfesorId =t.ProfesorId and m.EsActiva=false and P.ProfesorId=$Id_Actual )";
			$VerMaterias="select g.GrupoId, ca.Denominacion as AnioEscolar ,G.Nombre as NombreGrupo ,M.Nombre as Asignatura,M.MateriaId ,N.Denominacion as NivelEducativo,n.NivelId ,P.Usuario as Nombre_Profesor from grupos g,materias m ,niveles n ,profesores p, cursosacademicos ca where(g.MateriaId=M.MateriaId and g.NivelId =n.NivelId and g.ProfesorId =P.ProfesorId and g.CursoAcademicoId =ca.CursoAcademicoId  and ca.EsActivo =true and m.EsActiva=false and P.ProfesorId=$Id_Actual)";
			$Materias=mysqli_query($conexion,$VerMaterias);
			$nfilas=mysqli_num_rows($Materias);
			if($nfilas>0){
				echo("<table class='table table-bordered'>");
					echo("<thead>");
						echo("<tr class='bg-primary'>");
							echo("<th scope='col'>Id del Tema</th>");
							echo("<th scope='col'>Titulo</th>");
							echo("<th scope='col'>Asignatura</th>");
							echo("<th scope='col'>Nivel Educativo</th>");
							echo("<th scope='col'>Profesor que lo imparte</th>");
						echo("</tr>");
					echo("</thead>");
					echo("<tbody class='bg-info'>");
					
					while($fila=mysqli_fetch_array($Materias)){
						echo("<tr>");
								echo("<td>".$fila["AnioEscolar"]."</td>");
								echo("<td>".$fila["NombreGrupo"]."</td>");
								echo("<td>".$fila["Asignatura"]."</td>");
								echo("<td>".$fila["NivelEducativo"]."</td>");
								echo("<td>".$fila["Nombre_Profesor"]."</td>");
							echo("</tr>");
						
					}
					echo("</table>");
			}
			else{
				echo("No hay grupos desactivados");					
				}
					
		?>
		
	</body>
</html>