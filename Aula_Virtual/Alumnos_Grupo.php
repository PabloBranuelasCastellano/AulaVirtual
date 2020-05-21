<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		$Id_Grupo=$_GET["Id_Activo"];
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		
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
			$VerAlumnos="select a.AlumnoId ,a.Nombre,a.PrimerApellido ,a.SegundoApellido from alumnos a, gruposalumnos ga  ,grupos g  where (ga.GrupoId =g.GrupoId and ga.AlumnoId =a.AlumnoId and g.GrupoId=$Id_Grupo) order by a.PrimerApellido asc";
			$Alumnos=mysqli_query($conexion,$VerAlumnos);
			$nfilas=mysqli_num_rows($Alumnos);
			if($nfilas>0){
				echo("<table class='table table-bordered'>");
					echo("<thead>");
						echo("<tr class='bg-primary'>");
							echo("<th scope='col'>Id Alumno</th>");
							echo("<th scope='col'>Nombre Alumno</th>");
							echo("<th scope='col'>Primer Apellido</th>");
							echo("<th scope='col'>Segundo Apellido</th>");
							
						echo("</tr>");
					echo("</thead>");
					echo("<tbody class='bg-info'>");
					
					while($fila=mysqli_fetch_array($Alumnos)){
						echo("<tr>");
								echo("<td>".$fila["AlumnoId"]."</td>");
								echo("<td>".$fila["Nombre"]."</td>");
								echo("<td>".$fila["PrimerApellido"]."</td>");
								echo("<td>".$fila["SegundoApellido"]."</td>");
								
							echo("</tr>");
						
					}
					echo("</table>");
			}
			else{
				echo("No hay Alumnos en este grupo");					
				}
					
		?>
		
	</body>
</html>