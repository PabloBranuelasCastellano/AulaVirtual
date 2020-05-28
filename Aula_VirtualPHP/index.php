<html><meta charset='utf-8'>
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="Estilos/diseño.css">
	</head>
	
	<?php
		include("conexion.php");
		session_start();
		
	?>
	
	<body>
		<p><h1 id="Cabecera"><u>INICIO DE SESIÓN</u></h1></p>
		
		<form action="index.php" method="post">
		  <div class="form-group">
			<p id="Encabezado">Correo del Usuario</p>
			<input type="text" id="Entrada_Datos" name="Email_Acceso" class="form-control">
			
		  </div>
		  
		  <div class="form-group">
			<p id="Encabezado">Password</p>
			<input type="password" id="Entrada_Datos" name="Clave_Acceso" class="form-control">
		  </div>
		  
		  <button type="submit" name="Enviar" 	class="btn btn-primary btn-lg btn-block" >Submit</button>
		</form>
	</body>
	
	<?php
		if(isset($_POST["Enviar"])){
				$Email_Usuario=$_POST["Email_Acceso"];
				$Clave_Usuario=md5($_POST["Clave_Acceso"]);
				
				$Buscar_Usuario="select * from Profesores where Email like'$Email_Usuario' and Password like '$Clave_Usuario'and EsActivo=true";
				$Comprobar=mysqli_query($conexion,$Buscar_Usuario);
				$resultado=mysqli_fetch_array($Comprobar);
				if((int)$resultado==1){
					$IdUsuario=($_SESSION["ProfesorId"]=$resultado["ProfesorId"]);
					$UsuarioConectado=($_SESSION["Nombre_Profesor"]=$resultado["Usuario"]);
					// echo("<p>El usuario que se ha conectado ha sido: $UsuarioConectado</p>
					// <p>Su Id es $IdUsuario </p>");
					header("Location:PanelProfesores.php");
				}
				else{
					$Email_Usuario=$_POST["Email_Acceso"];
					$Clave_Usuario=md5($_POST["Clave_Acceso"]);
				
					$Buscar_Usuario="select * from Alumnos where Email like'$Email_Usuario' and Password like '$Clave_Usuario'and EsActivo=true";
					$Comprobar=mysqli_query($conexion,$Buscar_Usuario);
					$resultado=mysqli_fetch_array($Comprobar);
					if((int)$resultado==1){
						$IdUsuario=($_SESSION["AlumnoId"]=$resultado["AlumnoId"]);
						$UsuarioConectado=($_SESSION["Nombre_Alumno"]=$resultado["Usuario"]);
						// echo("<p>El usuario que se ha conectado ha sido: $UsuarioConectado</p>
						// <p>Su Id es $IdUsuario </p>");
						header("Location:PanelAlumnos.php");
					}
					else{
						echo("
							<script>
								alert('Correo o contraseña no valido o el usuario no está activado');
							</script>");
					}
				}
				
				
			
			
		}
	?>
	
</html>