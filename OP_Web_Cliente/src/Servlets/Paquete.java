package Servlets;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dto.OfertaPaqueteDTO;
import BD.Controlador;

@WebServlet("/Paquete")
public class Paquete extends BaseController {
	private static final long serialVersionUID = 1L;
       
    public Paquete() {
        super();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<OfertaPaqueteDTO> ofertas;
	 	try {	
	 			ofertas = Controlador.getInstancia().recuperarOfertas();
	 			request.setAttribute("ofertas", ofertas);
	 			Dispatch("paquetes.jsp", request, response);
 			} catch (NamingException e) {
 				e.printStackTrace();
 		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/OP_Web_Cliente/AltaPaquete");
	}

}
