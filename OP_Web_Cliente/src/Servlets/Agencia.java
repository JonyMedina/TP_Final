package Servlets;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dto.AgenciaDTO;

import BD.Controlador;


@WebServlet("/Agencia")
public class Agencia extends BaseController {
	private static final long serialVersionUID = 1L;
       
    public Agencia() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AgenciaDTO> agencias;
		try {
			agencias = Controlador.getInstancia().recuperarAgencias();
			request.setAttribute("agencias", agencias);
			Dispatch("agencias.jsp", request, response);
		} catch (Exception e) {
			
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dispatch("altaAgencia.jsp",request,response);
	}

}
