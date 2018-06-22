package Servlets;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.AgenciaDTO;

import BD.Controlador;

@WebServlet("/AltaAgencia")
public class AltaAgencia extends BaseController {
	private static final long serialVersionUID = 1L;
      
    public AltaAgencia() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("accion").equals("guardar")==true)
		{	
			try {
					AgenciaDTO agencia = new AgenciaDTO();
					String mail = (String) request.getParameter("txtemail");
					String nombre = (String) request.getParameter("txtNombre");
					String direccion = (String) request.getParameter("txtDireccion");
							
					agencia.setEstado("PENDIENTE");
					agencia.setMail(mail);
					agencia.setNombre(nombre);
					agencia.setDireccion(direccion);
						
					Controlador.getInstancia().agregarAgencia(agencia);
				
					response.sendRedirect("/OP_Web_Cliente/Agencia");
				
			} catch (NamingException e) {
				
				e.printStackTrace();
			}
		}
		else{
			response.sendRedirect("/OP_Web_Cliente/Agencia");
		}
	}
}

