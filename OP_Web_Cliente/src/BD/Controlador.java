package BD;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.controlador.OPFacadeRemote;
import com.dto.*;

public class Controlador {
	
	
//	@EJB
//	private OfertaPaqueteFacadeRemote ofertaFacade;
	OPFacadeRemote ofertaFacade = null;
	
	private static Controlador instancia;

	public static Controlador getInstancia() throws NamingException {
		if (instancia == null) {
			instancia = new Controlador();
		}
		return instancia;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Controlador() throws NamingException {

		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		Context context;

		context = new InitialContext(jndiProperties);

		final String earName = "OP_EAR"; // Nombre del m�dulo EAR
		final String ejbModuleName = "OP_EJB"; // Nombre del m�dulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
		final String ejbClassName = "OPFacade"; // Nombre corto del EJB
		final String fullInterfaceName = OPFacadeRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		ofertaFacade = (OPFacadeRemote) context.lookup(lookupName);

		
		altaMedios();
//		altaServicios();
		altaDestinos();
	}
	
	
	public void agregarAgencia(AgenciaDTO agencia) {
		ofertaFacade.altaAgencia(agencia);
	}

	public void agregarOferta(OfertaPaqueteDTO oferta) {
		ofertaFacade.altaPaquete(oferta);
	}
	
	
	public List<AgenciaDTO> recuperarAgencias() {
		return ofertaFacade.recuperarAgencias();
	}

	public List<OfertaPaqueteDTO> recuperarOfertas() {
		return ofertaFacade.recuperarPaquetes();
	}
	
	public void altaMedios(){
		
		List<MedioDePagoDTO> medios = new ArrayList<MedioDePagoDTO>();
		MedioDePagoDTO medio1 = new MedioDePagoDTO();
			medio1.setNombre("TARJETA");
		medios.add(medio1);
		
		MedioDePagoDTO medio2 = new MedioDePagoDTO();
			medio2.setNombre("CHEQUE");
		medios.add(medio2);
		
		MedioDePagoDTO medio3 = new MedioDePagoDTO();
			medio3.setNombre("PAGO EN DESTINO");
		medios.add(medio3);
		
		MedioDePagoDTO medio4 = new MedioDePagoDTO();
			medio4.setNombre("MERCADO PAGO");
		medios.add(medio4);
		
		MedioDePagoDTO medio5 = new MedioDePagoDTO();
			medio5.setNombre("PAY PAL");
		medios.add(medio5);
		
		ofertaFacade.altaMedios(medios);
	}
	
	public void altaServicios(){
		
		List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
		ServicioDTO servicio1 = new ServicioDTO();
			servicio1.setDescripcion("Wifi");
		servicios.add(servicio1);
		
		ServicioDTO servicio2 = new ServicioDTO();
			servicio2.setDescripcion("Pisina");
		servicios.add(servicio2);
		
		ServicioDTO servicio3 = new ServicioDTO();
			servicio3.setDescripcion("Gimnasio");
		servicios.add(servicio3);
		
		ServicioDTO servicio4 = new ServicioDTO();
			servicio4.setDescripcion("Spa");
		servicios.add(servicio4);
		
		ofertaFacade.altaServicios(servicios);
	}
	
	public void altaDestinos(){
		
		List<DestinoDTO> destinos = new ArrayList<DestinoDTO>();
		DestinoDTO destino = new DestinoDTO();
			destino.setNombre("Camboriu");
			UbicacionDTO ubicacion = new UbicacionDTO();
			ubicacion.setLatitud(-26.9905600);
			ubicacion.setLogitud(-48.6347200);
			destino.setUbicacion(ubicacion);
		destinos.add(destino);
		
		DestinoDTO destino2 = new DestinoDTO();
		destino2.setNombre("Cancun");
			UbicacionDTO ubicacion2 = new UbicacionDTO();
			ubicacion2.setLatitud(21.1742900);
			ubicacion2.setLogitud(-86.8465600);
		destino2.setUbicacion(ubicacion2);
		destinos.add(destino2);
		
		DestinoDTO destino3 = new DestinoDTO();
		destino3.setNombre("Buzios");
			UbicacionDTO ubicacion3 = new UbicacionDTO();
			ubicacion3.setLatitud(-22.7469400);
			ubicacion3.setLogitud(-41.8816700);
		destino3.setUbicacion(ubicacion3);
		destinos.add(destino3);

		DestinoDTO destino4 = new DestinoDTO();
		destino4.setNombre("Rio de Janeiro");
			UbicacionDTO ubicacion4 = new UbicacionDTO();
			ubicacion4.setLatitud(-22.7227777);
			ubicacion4.setLogitud(-44.1358333);
		destino4.setUbicacion(ubicacion4);
		destinos.add(destino4);
		
		DestinoDTO destino5 = new DestinoDTO();
		destino5.setNombre("Punta Cana");
			UbicacionDTO ubicacion5 = new UbicacionDTO();
			ubicacion5.setLatitud(18.5818200);
			ubicacion5.setLogitud(-68.4043100);
		destino5.setUbicacion(ubicacion5);
		destinos.add(destino5);

		DestinoDTO destino6 = new DestinoDTO();
		destino6.setNombre("Punta del Este");
			UbicacionDTO ubicacion6 = new UbicacionDTO();
			ubicacion6.setLatitud(-34.9474700);
			ubicacion6.setLogitud(-54.9338200);
		destino6.setUbicacion(ubicacion6);
		destinos.add(destino6);
		
		DestinoDTO destino7 = new DestinoDTO();
		destino7.setNombre("Tailandia");
			UbicacionDTO ubicacion7 = new UbicacionDTO();
			ubicacion7.setLatitud(13.7539800);
			ubicacion7.setLogitud(100.5014400);
		destino7.setUbicacion(ubicacion7);
		destinos.add(destino7);

		
		ofertaFacade.altaDestinos(destinos);
	}

	public List<MedioDePagoDTO> recuperarMedios() {
		// TODO Auto-generated method stub
		return ofertaFacade.recuperarMedios();
	}

	public List<ServicioDTO> recuperarServicios() {
		// TODO Auto-generated method stub
		return ofertaFacade.recuperarServicios();
	}

	public List<DestinoDTO> recuperarDestinos() {
		// TODO Auto-generated method stub
		return ofertaFacade.recuperarDestinos();
	}



}