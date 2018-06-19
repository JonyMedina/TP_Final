package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.controlador.OPFacadeRemote;
import com.dto.*;

public class TestClient {

	public static void main(String[] args) throws Exception {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "OP_EAR"; // Nombre del módulo EAR
		final String ejbModuleName = "OP_EJB"; // Nombre del módulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones
										// en nombres
		final String ejbClassName = "OPFacade"; // Nombre corto del EJB
		final String fullInterfaceName = OPFacadeRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		OPFacadeRemote mbr = (OPFacadeRemote) context.lookup(lookupName);
		System.out.println("Respuesta: " + mbr.sayHello("Mr Bean"));

		// PRUEBA ALTA AGENCIA

		AgenciaDTO agenciaTest = new AgenciaDTO();
		agenciaTest.setDireccion("Albarracin 1644");
		agenciaTest.setEstado("PENDIENTE");
		agenciaTest.setMail("jona.medina@hotmail.com");
		agenciaTest.setNombre("Turismo Bumeran");
		mbr.altaAgencia(agenciaTest);

		agenciaTest = new AgenciaDTO();
		agenciaTest.setDireccion("Balbastro 1644");
		agenciaTest.setEstado("PENDIENTE");
		agenciaTest.setMail("jona.medina@hotmail.com");
		agenciaTest.setNombre("Turismo Bumeran 2");
		mbr.altaAgencia(agenciaTest);

		// PRUEBA RECUPERAR AGENCIAS
		mostrarAgencias(mbr.recuperarAgencias());

		// PRUEBA ALTA MEDIOS
		mbr.altaMedios(altaMedios());

		// PRUEBA ALTA SERVICIOS
		mbr.altaServicios(altaServicios());

		// PRUEBA ALTA DESTINOS
		mbr.altaDestinos(altaDestinos());

		// PRUEBA RECUPERAR MEDIOS
		mostrarMedios(mbr.recuperarMedios());

		// PRUEBA RECUPERAR SERVICIOS
		// --> este me falta terminar porque es la llamada al webservice de
		// backoffice

		// PRUEBA RECUPERAR DESTINOS
		mostrarDestinos(mbr.recuperarDestinos());

		// PRUEBA ALTA PAQUETE
		OfertaPaqueteDTO ofertaDto = CargarPaquete("2");
		mbr.altaPaquete(ofertaDto);

		// PRUEBA MOSTRAR PAQUETES

		mostrarPaquetes(mbr.recuperarPaquetes());

	}

	// METODOS TESTING

	private static void mostrarPaquetes(ArrayList<OfertaPaqueteDTO> lstPaqueteDto) {
		System.out.println("-----------PRUEBA MOSTRAR PAQUETES-------------------");
		for (OfertaPaqueteDTO ofertaPaqueteDTO : lstPaqueteDto) {
			System.out.println("-------------Paquete Nro: " + ofertaPaqueteDTO.getIdPaquete() + " - "
					+ ofertaPaqueteDTO.getNombre());
			System.out.println("\tDescripcion: " + ofertaPaqueteDTO.getDescripcion());
			System.out.println("\tEstado: " + ofertaPaqueteDTO.getEstado());
			System.out.println("\tFoto: " + ofertaPaqueteDTO.getFoto());
			System.out.println("\tPolitica Cancelacion: " + ofertaPaqueteDTO.getPoliticaCancelacion());
			System.out.println("\tCant. Personas: " + ofertaPaqueteDTO.getCantidadPersonas());
			System.out.println("\tCupo: " + ofertaPaqueteDTO.getCupo());
			System.out.println("\tPrecio por Persona: " + ofertaPaqueteDTO.getPrecioXPersona());

			System.out.println("\t---- AGENCIA : " + ofertaPaqueteDTO.getAgencia().getId() + " - "
					+ ofertaPaqueteDTO.getAgencia().getNombre());
			System.out.println("\t\t\t\tDireccion: " + ofertaPaqueteDTO.getAgencia().getDireccion());
			System.out.println("\t\t\t\tMail: " + ofertaPaqueteDTO.getAgencia().getMail());

			// System.out.println("\tFoto: "+
			// ofertaPaqueteDTO.getAgencia().toJason());

			System.out.println("\t---- DESTINO : " + ofertaPaqueteDTO.getDestino().getIdDestino() + " - "
					+ ofertaPaqueteDTO.getDestino().getNombre());
			System.out.println("\t\t\t\tLatitud: " + ofertaPaqueteDTO.getDestino().getUbicacion().getLatitud());
			System.out.println("\t\t\t\tLongitud: " + ofertaPaqueteDTO.getDestino().getUbicacion().getLogitud());
			System.out.println("\t\t\t\tFecha Salida: " + ofertaPaqueteDTO.getFechaSalida());
			System.out.println("\t\t\t\tFecha Regreso: " + ofertaPaqueteDTO.getFechaRegreso());

			System.out.println("\tMedios de Pago:");
			for (MedioDePagoDTO m : ofertaPaqueteDTO.getMediosDePagos()) {
				System.out.println("\t\t\tId: " + m.getIdMP() + " - " + m.getNombre());
			}

			// System.out.println("\tServicios:");
			// for (ServicioDTO s : ofertaPaqueteDTO.getServicios()) {
			// System.out.println("\t\t\tId: "+ s.getIdServicio() +" - " +
			// s.getDescripcion());
			// }

			System.out.println("---------------------------------------------------------");

		}

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
		ofertaReturn.setFechaRegreso(new Date(2018, 03, 31));
		ofertaReturn.setFechaSalida(new Date(2018, 03, 01));
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

	private static void mostrarDestinos(List<DestinoDTO> lstDestinos) {
		for (DestinoDTO destinoDto : lstDestinos) {
			System.out.println("Nombre Destino: " + destinoDto.getNombre());
		}
		System.out.println("---------------------------------------");
	}

	private static void mostrarMedios(List<MedioDePagoDTO> lstMedios) {
		for (MedioDePagoDTO medioPago : lstMedios) {
			System.out.println("Nombre Medio Pago: " + medioPago.getNombre());
		}
		System.out.println("---------------------------------------");
	}

	private static void mostrarAgencias(ArrayList<AgenciaDTO> lstAgenciasDto) {
		for (AgenciaDTO agenciaDto : lstAgenciasDto) {
			System.out.println("Nombre Agencia: " + agenciaDto.getNombre());
		}
		System.out.println("---------------------------------------");
	}

	public static List<MedioDePagoDTO> altaMedios() {

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

		return medios;
	}

	public static List<ServicioDTO> altaServicios() {

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

		return servicios;
	}

	public static List<DestinoDTO> altaDestinos() {

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

		return destinos;
	}

}
