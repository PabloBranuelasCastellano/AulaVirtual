<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Profesor=$_SESSION["ProfesorId"];
		$Id_Materia=$_GET["Id_Activo"];
		echo($Id_Profesor."&nbsp;&nbsp;".$Id_Materia);
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
			$Ver_Temas="select t.titulo,m.Nombre from temas t, profesor p ,niveles n where(t.materiaId=p.MateriaId and n.nivelId=t.nivelId and t.profesorId=$IdProfesor and t.materiaId=$Id_Materia)"; 
		?>
	</body>
</html>