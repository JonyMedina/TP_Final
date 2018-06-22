<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<title>Oferta Paquetes</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
</head>
<script type="text/javascript">

    window.addEventListener('load', inicio, false);

    function inicio() {
        document.getElementById("formulario1").addEventListener('submit', validar, false);
    }

    function validar(evt) {
        var nombre = document.getElementById("txtNombre").value;
        var direccion = document.getElementById("txtDireccion").value;
        var email = document.getElementById("txtemail").value;
        
        if (nombre=='') {
            alert('Debe cargar un nombre');
            evt.preventDefault();
        }
        else{
       		 if (direccion=='') {
         		   alert('Debe cargar una direccion');
         		  evt.preventDefault();
     	     }
     	     else{
     	    	expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
     	        if ( !expr.test(email) ){
     	            alert("Error: La dirección de correo " + email + " es incorrecta.");
     		       evt.preventDefault();
 				 }
     	     
     	     }
        
        }

        
    }

</script>


<body>
	<div class="container">
		<h1>Alta de Agencia</h1>
		<br />
		<br />
		
		<form action="/OP_Web_Cliente/AltaAgencia" method="POST" name="formulario1" id="formulario1">

			<!-- NOMBRE -->
			<div class="row form-group">
				<div class="col-md-8  form-group">
					<label for="txtNombre">Nombre:</label> 
					<input type="text" name="txtNombre" class="form-control input-sm" id="txtNombre" />
				</div>
			</div>
			
			<!-- DIRECCION -->
			<div class="row form-group">
				<div class="col-md-8  form-group">
					<label for="txtDireccion">Direccion:</label> 
					<input type="text" name="txtDireccion"	class="form-control input-sm" id="txtDireccion" />
				</div>
			</div>

			<!-- EMAIL -->
			<div class="row form-group">
				<div class="col-md-6  form-group">
					<label for="txtemail">Email:</label> 
					<input type="text" name="txtemail" class="form-control input-sm" id="txtemail"  />
				</div>
			</div>
		<div class="col-md-4">	
			<button name="accion" value="guardar" class="btn btn-lg btn-primary btn-block" type="submit">Guardar</button>
		</div>
		</form>
		
		<div class="col-md-4">
		<form action="/OP_Web_Cliente/AltaAgencia" method="POST" name="formulario1" id="formulario1">
			<button name="accion" value="cancelar" class="btn btn-lg btn-primary btn-block" type="submit">Cancelar</button>
		</form>
		</div>
		
		
		<br />
		<br />
		
		<br>
	</div>
</body>
</html>