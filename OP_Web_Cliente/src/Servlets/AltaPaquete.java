package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Inet4Address;
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
import javax.servlet.http.Part;

import com.dto.*;

import BD.Controlador;

/**
 * Servlet implementation class AltaPaquete
 */
@MultipartConfig
@WebServlet("/AltaPaquete")
public class AltaPaquete extends BaseController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<AgenciaDTO> agencias;
		List<MedioDePagoDTO> medios;
		List<ServicioDTO> servicios;
		List<DestinoDTO> destinos;
		
		try {
			agencias = Controlador.getInstancia().recuperarAgencias();
			request.setAttribute("agencias", agencias);
			
			medios = Controlador.getInstancia().recuperarMedios();
			request.setAttribute("mediosdepago", medios);
			
			servicios = Controlador.getInstancia().recuperarServicios();
			request.setAttribute("servicios", servicios);
			
			destinos = Controlador.getInstancia().recuperarDestinos();
			request.setAttribute("destinos", destinos);
			
			
			Dispatch("altapaquete.jsp", request, response);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("accion").equals("Guardar")==true){
			
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
	 		String direccionFoto = UploadFile(request, response);
	//		String direccionFoto = "http://foto.com";
			String[] servicio = (String[]) request.getParameterValues("cmbServicio");
			String[] medio = (String[]) request.getParameterValues("cmbMedioDePago");
			
			//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			
			Date fechaSalida = null;
			Date fechaRegreso = null;
			
			try {
				fechaSalida = formatter.parse(fechaSalidaS);
				fechaRegreso = formatter.parse(fechaRegresoS);
			} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
	        
			OfertaPaqueteDTO paquete = new OfertaPaqueteDTO();
			DestinoDTO destino = new DestinoDTO();
			
			destino.setIdDestino(Integer.valueOf(destinoD));
			
			List<MedioDePagoDTO> medios = new ArrayList<MedioDePagoDTO>();
			for(String med: medio){
				MedioDePagoDTO medioD = new MedioDePagoDTO();
				medioD.setIdMP(Integer.valueOf(med));
				medios.add(medioD);
			}
			
			List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
			for(String ser: servicio){
				ServicioDTO servicioD = new ServicioDTO();
				servicioD.setIdServicio(Integer.valueOf(ser));
				servicios.add(servicioD);
			}
			
			AgenciaDTO agenciaD = new AgenciaDTO();
			agenciaD.setId(Integer.valueOf(agencia));
			
			paquete.setAgencia(agenciaD);
			paquete.setCantidadPersonas(cantPersonas);
			paquete.setCupo(cupo);
			paquete.setDescripcion(descripcion);
			paquete.setDestino(destino);
			paquete.setEstado("Activo");
			paquete.setFechaRegreso(fechaRegreso);
			paquete.setFechaSalida(fechaSalida);
			paquete.setFoto("http://192.168.1.43:8080/OP_Web_Cliente/" + direccionFoto);
			paquete.setMediosDePagos(medios);
			paquete.setNombre(nombre);
			paquete.setPoliticaCancelacion(politicas);
	 		paquete.setPrecioXPersona(precio);
			paquete.setServicios(servicios);
			
			try {
	
				Controlador.getInstancia().agregarOferta(paquete);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			response.sendRedirect("/OP_Web_Cliente/Paquete");
		}
		else{
			response.sendRedirect("/OP_Web_Cliente/Paquete");
		}

	}
	
	private String UploadFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

	    
	    
	   String path="C:/Users/DANIEL/workspace7/OP_Web_Cliente/WebContent";
	    File uploads = new File(path); //Carpeta donde se guardan los archivos
//	    uploads.mkdirs(); //Crea los directorios necesarios
	    File file = File.createTempFile("GRUPO3-", "-"+fileName, uploads); //Evita que hayan dos archivos con el mismo nombre
	
	
	    
	    try (InputStream input = filePart.getInputStream()){
	        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        input.close();
	    }
	    
	    int i = file.getName().length();
	    //String a = file.getName().substring(45, i-1);
	    String a = file.getName();
	    
	    
	    uploads = null;
	    return a;
	}
}

