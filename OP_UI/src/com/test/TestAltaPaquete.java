package com.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.controlador.OPFacadeRemote;
import com.dto.*;
public class TestAltaPaquete {
//NO USAR... FALTA COMPLETAR, PUEDE FALLAR
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws Exception  {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "OP_EAR"; // Nombre del m�dulo EAR
		final String ejbModuleName = "OP_EJB"; // Nombre del m�dulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
										
		final String ejbClassName = "OPFacade"; // Nombre corto del EJB
		final String fullInterfaceName = OPFacadeRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		OPFacadeRemote mbr = (OPFacadeRemote) context.lookup(lookupName);
		System.out.println("Respuesta: " + mbr.sayHello("Mr Bean"));

		
		// PRUEBA ALTA PAQUETE
		OfertaPaqueteDTO ofertaDto = CargarPaquete("2");
		mbr.altaPaquete(ofertaDto);
	}
	private static OfertaPaqueteDTO CargarPaquete(String numeroPrueba) {
		OfertaPaqueteDTO ofertaReturn = new OfertaPaqueteDTO();

		AgenciaDTO agenciaDto = new AgenciaDTO();
		agenciaDto.setId(1);
		ofertaReturn.setAgencia(agenciaDto);
		ofertaReturn.setCantidadPersonas(2);
		ofertaReturn.setCupo(3);
		ofertaReturn.setDescripcion("Descripcion Paquete " + numeroPrueba);

		DestinoDTO destinoDto = new DestinoDTO();
		destinoDto.setIdDestino(12);
		UbicacionDTO ubicacionDto = new UbicacionDTO();
		ubicacionDto.setLatitud(-26.9905600);
		ubicacionDto.setLogitud(-48.6347200);
		destinoDto.setUbicacion(ubicacionDto);

		ofertaReturn.setDestino(destinoDto);
		ofertaReturn.setEstado("PENDIENTE");
		ofertaReturn.setFechaRegreso(new Date());
		ofertaReturn.setFechaSalida(new Date());
		ofertaReturn.setFoto("foto");

		List<MedioDePagoDTO> lstMedios = new ArrayList<MedioDePagoDTO>();
		MedioDePagoDTO medio1 = new MedioDePagoDTO();
		medio1.setIdMP(3);
		lstMedios.add(medio1);

		MedioDePagoDTO medio2 = new MedioDePagoDTO();
		medio2.setIdMP(4);
		lstMedios.add(medio2);

		ofertaReturn.setMediosDePagos(lstMedios);

		ofertaReturn.setNombre("Nombre Paquete " + numeroPrueba);
		ofertaReturn.setPoliticaCancelacion("Esta es la politica de cancelacion " + numeroPrueba);
		ofertaReturn.setPrecioXPersona(Float.parseFloat("125300"));

		ServicioDTO servicioDto = new ServicioDTO();
		servicioDto.setIdServicio(9);
		List<ServicioDTO> lstServicio = new ArrayList<ServicioDTO>();
		lstServicio.add(servicioDto);

		return ofertaReturn;
	}
}
