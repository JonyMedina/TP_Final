<%@page language="java" contentType="text/html" pageEncoding="UTF-8"
	import="com.dto.*,java.util.*"%>
<% 
List<AgenciaDTO> agencias = request.getAttribute("agencias")!=null? (List<AgenciaDTO>) request.getAttribute("agencias"): new ArrayList<AgenciaDTO>();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Oferta Paquetes - Grupo 11</title>

<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="signin.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<div class="col-md-12">
			<h1>Agencias</h1>
			<%
				if (request.getAttribute("error") != null) {
			%>
			<div class="col-md-12">
				<div class="alert alert-danger">
					<p><%=request.getAttribute("error")%></p>
				</div>
			</div>
			<%
				}
			%>
			<%
				if (request.getAttribute("mensaje") != null) {
			%>
			<div class="col-md-12">
				<div class="alert alert-info">
					<p><%=request.getAttribute("mensaje")%></p>
				</div>
			</div>
			<%
				}
			%>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Nombre</th>
						<th>Direccion</th>
						<th>Estado</th>
						<th>E-Mail</th>
						<th></th>


					</tr>
				</thead>
				<tbody>
					<%
						for (AgenciaDTO a : agencias) {
					%>
					<tr>
						<td><%= a.getId() %></td>
						<td><%= a.getNombre() %></td>
						<td><%= a.getDireccion() %></td>
						<td><%=a.getEstado() %></td>
						<td><%=a.getMail() %></td>
						
					</tr>
					<%
						}
					%>
						
					
				</tbody>
			</table>
			
			<br>
			<form action="/OP_Web_Cliente/Agencia" method="POST" class="col-md-4 col-md-push-3">
				<button name="accion" value="Volver" class="btn btn-lg btn-primary btn-block" type="submit"> Nueva agencia</button>
			</form>			
			
			<form action="/OP_Web_Cliente/Volver" method="POST" class="col-md-4 col-md-push-3">
				<button name="accion" value="Volver" class="btn btn-lg btn-primary btn-block" type="submit"> Volver</button>
			</form>
			


		</div>
	</div>
</body>
</html>
