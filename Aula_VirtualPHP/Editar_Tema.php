<html><meta charset='utf-8'>
	<?php
		session_start();
		include("conexion.php");
		$Ver_Dia="select curdate()";
		$Mostrar=mysqli_query($conexion,$Ver_Dia);
		$fecha=mysqli_fetch_row($Mostrar);
		$fecha=$fecha[0];
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
			$Agregar_Tema="insert into temas values (null,'".$_POST['tema_Name']."','".$_POST['tema_Resumen']."',".$Id_Profesor.",".$_POST['MateriaActual'].",'".$_POST['NivelActual']."','".$fecha."',".$_POST['activar_Tema'].",".$_POST['tema_Number'].",".$_POST['tema_order'].")";
			$Guardar_Tema=mysqli_query($conexion,$Agregar_Tema);
			
			if($Guardar_Tema){
				echo("<script>
					alert('Tema Creado correctamente');
				</script>");
			}
			else{
				echo("<script>
					alert('No se ha podido agregar el tema');
				</script>");
			}
			
		}
	?>
</html>