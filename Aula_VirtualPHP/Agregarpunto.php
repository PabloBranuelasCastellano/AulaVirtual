<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		
		$Usuario_Actual=$_SESSION["Nombre_Profesor"];
		$Id_Profesor=$_SESSION["ProfesorId"];
		$Id_Materia=$_GET["Id_Activo"];
		$Id_Tema=$_GET["Id_Tema"];
		echo("El id del tema es ".$Id_Tema);
		
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
	<body>
		<form action='Agregarpunto.php' method='post' style='margin-left:10rem;'>
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
			
			<input type='hidden' name='TemaActual'value="<?php echo($_GET["Id_Tema"]);?>"/>
			<button type="submit" name="Agregar" 	class="btn btn-primary btn-lg btn-block" >Registrar Punto</button>	  
		</form>
	</body>
	
	<?php
		
		if(isset($_POST['Agregar'])){
			$Agregar_Punto="insert into puntos values (null,".$_POST['TemaActual'].",'".$_POST['point_Name']."','".$_POST['point_Resumen']."','".$_POST['point_text']."',".$_POST['activar_Punto'].",".$_POST['point_Number'].",".$_POST['point_order'].")";
			$Guardar_Punto=mysqli_query($conexion,$Agregar_Punto);
			
			if($Guardar_Punto){
				echo("<script>
					alert('Punto Creado correctamente');
				</script>");
			}
			else{
				echo("<script>
					alert('No se ha podido agregar el punto');
				</script>");
			}
			
		}
	?>
</html>