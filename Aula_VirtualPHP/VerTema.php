<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Profesor=$_SESSION["ProfesorId"];
		$Id_Materia=$_GET["Id_Activo"];
		// echo($Id_Profesor."&nbsp;&nbsp;".$Id_Materia);
		if($Usuario_Actual=="" or $Usuario_Actual==null){
			header("Location:index.php");
		}
		
		
	?>
		
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	</head>
	<body>
		<?php
			$Ver_Temas="select distinct t.titulo,m.Nombre,t.TemaId from temas t, profesores p ,niveles n,materias m where(t.materiaId=m.MateriaId and n.nivelId=t.nivelId and t.profesorId=$Id_Profesor and t.materiaId=$Id_Materia)"; 
			$Temario=mysqli_query($conexion,$Ver_Temas);
			$nfilas=mysqli_num_rows($Temario);
			if($nfilas>0){
				echo("<ul class='list-group'>");
				while($fila=mysqli_fetch_array($Temario)){
					echo(
						"<li class='list-group-item list-group-item-action list-group-item-primary'>".$fila['titulo']."<a class='btn btn-primary' data-toggle='collapse' href='#Tema".strval($fila["TemaId"])."' role='button' aria-expanded='false' aria-controls='multiCollapseExample1' style='margin-left:50%;'>Ver Tema</a><a class='btn btn-primary' role='button' href='Agregarpunto.php?Id_Tema=".strval($fila["TemaId"])."' role='button' aria-expanded='false' aria-controls='multiCollapseExample1' style='margin-left:10%;'>Añadir Puntos</a>
						</li>");
					echo("<div class='row'>
							  <div class='col'>
								<div class='collapse multi-collapse' id='Tema".strval($fila["TemaId"])."'>
								  <div class='card card-body'>");
								  
									$Ver_Puntos="select pnt.Titulo,pnt.resumen,pnt.texto from puntos pnt,temas t,profesores p where(pnt.temaId=t.temaId and t.profesorId=p.profesorId and t.materiaId='$Id_Materia' and t.profesorId='$Id_Profesor' and pnt.TemaId='".$fila['TemaId']."')";
									$Puntos=mysqli_query($conexion,$Ver_Puntos);
									$nfilasPuntos=mysqli_num_rows($Puntos);
									if($nfilasPuntos>0){
										
									}
									else{
										echo("<p>Este tema no tiene ningún punto</p>");
									}
								  echo("</div>
								</div>
							</div>
						</div>");	
				}
			echo("</ul>");
			}
			else{
				echo("No hay temas activados");
			}
		?>
	</body>
</html>