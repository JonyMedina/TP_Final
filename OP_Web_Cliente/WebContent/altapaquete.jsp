<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.dto.*, java.util.*"%>
<%
	List<TipoServicioDTO> servicios = (List<TipoServicioDTO>) request.getAttribute("servicios");
	List<MedioDePagoDTO> mediosdepago = (List<MedioDePagoDTO>) request.getAttribute("mediosdepago");
	List<AgenciaDTO> agencias = (List<AgenciaDTO>) request.getAttribute("agencias");
	List<DestinoDTO> destinos = (List<DestinoDTO>) request.getAttribute("destinos");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8">
<title>Oferta Paquetes - Grupo 11</title>

<link href="assets/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<h1>Alta de Paquetes - Grupo 11</h1>
		<br /> <br />
		<form action="/OP_Web_Cliente/AltaPaquete" method="post"
			enctype="multipart/form-data" name="formulario1" id="formulario1">
			<div class="col-md-12">
				<div class="row form-group">
					<div class="col-sm-12 form-group">
						<label for="Agencia">Agencia:</label> <select
							class="form-control input-sm" name="CAgencia" id="CAgencia"
							required>
							<%
								for (AgenciaDTO agencia : agencias) {
							%>
							<option value="<%=agencia.getId()%>"><%=agencia.getNombre()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<!-- NOMBRE -->
				<div class="row form-group">
					<div class="col-sm-12 form-group">
						<label for="txtNombre">Nombre:</label> <input type="text"
							class="form-control input-sm" id="txtNombre" name="txtNombre"
							required />
					</div>
				</div>

				<!-- DESTINO -->
				<div class="row  form-group">
					<div class="col-sm-6  form-group">
						<label for="direccion">Destino:</label> <select
							class="form-control input-sm" name="CDestino" id="CDestino" required>
							<%
								for (DestinoDTO destino : destinos) {
							%>
							<option value="<%=destino.getIdDestino()%>"><%=destino.getNombre()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

				<!-- FECHAS -->
				<div class="row  form-group">
					<div class="col-sm-6  form-group">
						<label for="txtFechaSalida">Fecha de salida:</label> <input
							type="date" class="form-control input-sm" id="txtFechaSalida"
							name="txtFechaSalida" required />
					</div>
					<div class="col-sm-6  form-group">
						<label for="txtFechaRegreso">Fecha de regreso:</label> <input
							type="date" class="form-control input-sm" id="txtFechaRegreso"
							name="txtFechaRegreso" required />
					</div>
				</div>

				<!-- ACA VAN LAS FOTOS -->
				<div class="row form-group">
					<div class="col-sm-12  form-group">
						<label class="btn-default" for="file">Cargar foto: <input
							type="file" name="file" />
						</label>
					</div>
				</div>

				<!-- DESCRIPCION DEL PAQUETE -->
				<div class="row form-group">
					<div class="col-sm-12  form-group">
						<label for="txtDescripcion">Descripción del paquete:</label>
						<textarea class="form-control" rows="5" id="txtDescripcion"
							placeholder="Ingrese la descripcion del paquete..."
							name="txtDescripcion" required></textarea>
					</div>
				</div>

				<!-- POLITICA DE CANCELACION -->
				<div class="row form-group">
					<div class="col-sm-12  form-group">
						<label for="txtPoliticaCancelacion">Politica de
							cancelacion:</label>
						<textarea class="form-control" rows="5"
							id="txtPoliticaCancelacion"
							placeholder="Ingrese los terminos de la Politica de Cancelacion..."
							name="txtPoliticaCancelacion" required></textarea>
					</div>
				</div>

				<!-- SERVICIOS -->
				<div class="row form-group">
					<div class="col-sm-6  form-group">
						<label for="cmbServicio">Servicios:</label> 
						<select multiple class="form-control" id="cmbServicio" name="cmbServicio" required>
				 	  		<%for(TipoServicioDTO s : servicios){ %>
				 	    		<option disabled style="font-weight:bold" value="<%=s.getId() %>"><%=s.getNombre() %></option>
				 	    		
				 	    		<%for(ServicioDTO ser : s.getServicios()){ %>
				 	    			<option value="<%=ser.getIdServicio() %>"><%=ser.getDescripcion() %></option>
				 	    	<%}%>
				 	    	<%}%>
						</select>
					</div>
				</div>


				<!-- CANTIDAD DE PERSONAS -->
				<div class="row form-group">
					<div class="col-sm-6  form-group">
						<label for="txtPersonas">Cantidad de Personas:</label> <input
							type="number" class="form-control input-sm" id="txtPersonas"
							name="txtPersonas" min="1" required>
					</div>
				</div>

				<!-- PRECIO POR PERSONA -->
				<div class="row form-group">
					<div class="col-sm-6  form-group">
						<label for="txtPrecio">Precio por persona:</label> <input
							type="number" class="form-control input-sm" id="txtPrecio"
							name="txtPrecio" min="0" step="0.01" data-number-to-fixed="2"
							data-number-stepfactor="100" required />
					</div>
				</div>

				<!-- CUPO -->
				<div class="row form-group">
					<div class="col-sm-6  form-group">
						<label for="txtCupo">Cupo:</label> <input type="number"
							name="cupo" class="form-control input-sm" min="1" id="cupo"
							required />
					</div>
				</div>

				<!-- MEDIOS DE PAGO -->
				<div class="row form-group">
					<div class="col-sm-6  form-group">
						<label for="cmbMedioDePago">Medios de Pago:</label> <select
							multiple class="form-control" id="cmbMedioDePago"
							name="cmbMedioDePago" required>
							<%
								for (MedioDePagoDTO m : mediosdepago) {
							%>
							<option value="<%=m.getIdMP()%>"><%=m.getNombre()%></option>
							<%
								}
							%>
						</select>
					</div>
				</div>

			</div>

			<div class="col-md-4">
				<!-- BOTONES -->
				<button name="accion" value="Guardar"
					class="btn btn-lg btn-primary btn-block" type="submit">Guardar</button>

			</div>
		</form>

		<div class="col-md-4">
			<form action="/OP_Web_Cliente/AltaPaquete" method="POST">
				<button name="accion" value="Cancelar"
					class="btn btn-lg btn-primary btn-block" type="submit">Cancelar</button>
			</form>
		</div>
		<br /> <br />
	</div>
	</div>
	<script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/validador.js"></script>
</body>
</html>