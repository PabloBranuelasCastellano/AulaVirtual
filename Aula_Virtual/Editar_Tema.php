<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Profesor=$_SESSION["ProfesorId"];
		
		if($Usuario_Actual=="" or $Usuario_Actual==null){
			header("Location:index.php");
		}
		
		
	?>
		
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Estilos/diseño.css">
	</head>
	
	<body>
		<form action='Editar_Tema.php' method='post' style='margin-left:10rem;'>
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
			<input type='hidden' name='MateriaActual'value="<?php echo($_GET["Id_Materia"]);?>"/>
			<input type='hidden' name='NivelActual'value="<?php echo($_GET["Id_Nivel"]);?>"/>
			<button type="submit" name="Agregar" 	class="btn btn-primary btn-lg btn-block" >Registrar Tema</button>	  
		</form>
	</body>
	
	<?php
		
		if(isset($_POST['Agregar'])){
			echo("<p>Id del Nivel\r\n".$_POST['NivelActual']."</p>");
			echo("<p>Id de la Materia\r\n".$_POST['MateriaActual']."</p>");
			echo("<p>Nombre del Tema\r\n".$_POST['tema_Name']."</p>");
			echo("<p>Resumen del Tema".$_POST['tema_Resumen']."</p>");
			echo("Opcion Activada?\r\n".$_POST['activar_Tema']);
		}
	?>
</html>