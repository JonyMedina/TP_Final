package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import com.dto.*;

import BD.Controlador;

@MultipartConfig
@WebServlet("/AltaPaquete")
public class AltaPaquete extends BaseController {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	public AltaPaquete() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<AgenciaDTO> agencias;
		List<MedioDePagoDTO> medios;
		List<TipoServicioDTO> servicios;
		List<DestinoDTO> destinos;
		session=request.getSession(true);
		try {
			agencias = Controlador.getInstancia().recuperarAgencias();
			request.setAttribute("agencias", agencias);

			medios = Controlador.getInstancia().recuperarMedios();
			request.setAttribute("mediosdepago", medios);
			session.setAttribute("session_mediosdepago", medios);

			servicios = Controlador.getInstancia().recuperarServicios();
			request.setAttribute("servicios", servicios);
			session.setAttribute("session_servicios", servicios);

			destinos = Controlador.getInstancia().recuperarDestinos();
			request.setAttribute("destinos", destinos);

			Dispatch("altapaquete.jsp", request, response);
		} catch (NamingException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("accion").equals("Guardar") == true) {

			String nombre = (String) request.getParameter("txtNombre");
			String fechaSalidaS = (String) request.getParameter("txtFechaSalida");
			String fechaRegresoS = (String) request.getParameter("txtFechaRegreso");
			String politicas = (String) request.getParameter("txtPoliticaCancelacion");
			float precio = Float.parseFloat(request.getParameter("txtPrecio"));
			int cantPersonas = Integer.parseInt(request.getParameter("txtPersonas"));
			String descripcion = (String) request.getParameter("txtDescripcion");
			int cupo = Integer.parseInt(request.getParameter("cupo"));
			String agencia = (String) request.getParameter("CAgencia");
			String destinoD = (String) request.getParameter("CDestino");
			String direccionFoto =  UploadFile(request, response);
			
			String[] servicio = (String[]) request.getParameterValues("cmbServicio");
			String[] medio = (String[]) request.getParameterValues("cmbMedioDePago");

			// SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

			Date fechaSalida = null;
			Date fechaRegreso = null;

			try {
				fechaSalida = formatter.parse(fechaSalidaS);
				fechaRegreso = formatter.parse(fechaRegresoS);
			} catch (ParseException e1) {

				e1.printStackTrace();
			}

			OfertaPaqueteDTO paquete = new OfertaPaqueteDTO();
			DestinoDTO destino = new DestinoDTO();

			destino.setIdDestino(Integer.valueOf(destinoD));

			List<MedioDePagoDTO> medios = new ArrayList<MedioDePagoDTO>();
			for (String med : medio) {
				MedioDePagoDTO medioD = new MedioDePagoDTO();
				medioD.setIdMP(Integer.valueOf(med));
				medioD.setNombre(obtenerDescripcionMedio(medioD.getIdMP()));
				medios.add(medioD);
			}

			List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
			for (String ser : servicio) {
				ServicioDTO servicioD = new ServicioDTO();
				servicioD.setIdServicio(Integer.valueOf(ser));
				servicioD.setDescripcion(obtenerDescripcionServicio(servicioD.getIdServicio()));
				servicios.add(servicioD);
			}

			AgenciaDTO agenciaD = new AgenciaDTO();
			agenciaD.setId(Integer.valueOf(agencia));

			paquete.setAgencia(agenciaD);
			paquete.setCantidadPersonas(cantPersonas);
			paquete.setCupo(cupo);
			paquete.setDescripcion(descripcion);
			paquete.setDestino(destino);
			paquete.setEstado("PENDIENTE");
			paquete.setFechaRegreso(fechaRegreso);
			paquete.setFechaSalida(fechaSalida);
			paquete.setFoto(direccionFoto); //direccionFoto es en realidad el Base64 de la foto
			paquete.setMediosDePagos(medios);
			paquete.setNombre(nombre);
			paquete.setPoliticaCancelacion(politicas);
			paquete.setPrecioXPersona(precio);
			paquete.setServicios(servicios);

			try {

				Controlador.getInstancia().agregarOferta(paquete);
			} catch (NamingException e) {

				e.printStackTrace();
			}

			response.sendRedirect("/OP_Web_Cliente/Paquete");
		} else {
			response.sendRedirect("/OP_Web_Cliente/Paquete");
		}

	}

	private String obtenerDescripcionMedio(Integer idMP) {
		@SuppressWarnings("unchecked")
		List<MedioDePagoDTO> lstMedios = (List<MedioDePagoDTO>)session.getAttribute("session_mediosdepago");
		
		for (MedioDePagoDTO medioDTO : lstMedios) {
			if(medioDTO.getIdMP() ==idMP)
				return medioDTO.getNombre();
		}
		return "Visa";
	}


	private String obtenerDescripcionServicio(Integer idServicio) {
		
		//session=request.getSession(true);
		@SuppressWarnings("unchecked")
		List<TipoServicioDTO> lstTipoServicio = (List<TipoServicioDTO>)session.getAttribute("session_servicios");
		
		for (TipoServicioDTO tipoServicioDTO : lstTipoServicio) {
			for (ServicioDTO servicioDTO : tipoServicioDTO.getServicios()) {
				if(servicioDTO.getIdServicio()==idServicio)
					return servicioDTO.getDescripcion();
			}
		}
		
		return "Servicio General";
	}


	private String UploadFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		String path = "C:/TP_Final/OP_Web_Cliente/WebContent";

		File uploads = new File(path); // Carpeta donde se guardan los archivos
		// uploads.mkdirs(); //Crea los directorios necesarios
		File file = File.createTempFile("File-", "-" + fileName, uploads); // Evita que haya dos archivos con el mismo nombre

		try (InputStream input = filePart.getInputStream()) {
			Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			input.close();
		}

		//int i = file.getName().length();
		// String a = file.getName().substring(45, i-1);
		//String a = file.getName();
		
		java.nio.file.Path mypath = java.nio.file.Paths.get(file.getPath());
		byte[] mydata = java.nio.file.Files.readAllBytes(mypath);
		
		String str = DatatypeConverter.printBase64Binary(mydata);
		
		uploads = null;
		//return a;
		return str;
	}
}
