package com.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;
import com.dto.*;
import com.entities.*;


@Stateless
@LocalBean
public class AdmOfertas implements AdmOfertasRemote, AdmOfertasLocal {

	@PersistenceContext(unitName="MyPU")
	   private EntityManager manager;
	
    public AdmOfertas() {

    }

	@Override
	public void altaPaquete(OfertaPaqueteDTO ofertaDto) {
		
		OfertaPaquete ofertaNew = new OfertaPaquete();
				
		ofertaNew.setAgencia(this.recuperarAgencia(ofertaDto.getAgencia().getId()));
		ofertaNew.setCantidadPersonas(ofertaDto.getCantidadPersonas());
		ofertaNew.setCupo(ofertaDto.getCupo());
		ofertaNew.setDescripcion(ofertaDto.getDescripcion());
		ofertaNew.setDestino(this.recuperarDestino(ofertaDto.getDestino().getIdDestino()));
		ofertaNew.setEstado(ofertaDto.getEstado());
		ofertaNew.setFechaRegreso(ofertaDto.getFechaRegreso());
		ofertaNew.setFechaSalida(ofertaDto.getFechaSalida());
		ofertaNew.setFoto(ofertaDto.getFoto());
		ofertaNew.setIdPaquete(ofertaDto.getIdPaquete());		
		ofertaNew.setNombre(ofertaDto.getNombre());
		ofertaNew.setPoliticaCancelacion(ofertaDto.getPoliticaCancelacion());
		ofertaNew.setPrecioXPersona(ofertaDto.getPrecioXPersona());
		
		//  Mapear Medios de Pagos
		ArrayList<MedioDePago> mediosPagoEntity = new ArrayList<MedioDePago>();
		for(MedioDePagoDTO m: ofertaDto.getMediosDePagos()){
			MedioDePago medioEntity = this.recuperarMedio(m.getIdMP());
			mediosPagoEntity.add(medioEntity);
		}		
		ofertaNew.setMediosDePagos(mediosPagoEntity);
				
						
		//  Mapear servicios
//		ArrayList<Servicio> serviciosEntity = new ArrayList<Servicio>();
//		for(ServicioDTO s: ofertaDto.getServicios()){
//			Servicio servicioEntity = this.recuperarServicio(s.getIdServicio());
//			serviciosEntity.add(servicioEntity);
//		}		
//		ofertaNew.setServicios(serviciosEntity);
						
		manager.persist(ofertaNew);		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<OfertaPaqueteDTO> recuperarPaquetes() {
		try {
			Query q = manager.createQuery("Select o from OfertaPaquete o");
			List<OfertaPaquete> oferta = (List<OfertaPaquete>) q.getResultList();
			ArrayList<OfertaPaqueteDTO> ofertasD = new ArrayList<OfertaPaqueteDTO>();

			for (OfertaPaquete o : oferta) {
												
				OfertaPaqueteDTO ofertaD = new OfertaPaqueteDTO();
				ofertaD.setCantidadPersonas(o.getCantidadPersonas());
				ofertaD.setCupo(o.getCupo());
				ofertaD.setDescripcion(o.getDescripcion());
				
				DestinoDTO destinoD = new DestinoDTO();
				destinoD.setIdDestino(o.getDestino().getIdDestino());
				destinoD.setNombre(o.getDestino().getNombre());
				
				UbicacionDTO ubicacionD = new UbicacionDTO();
				ubicacionD.setLatitud(o.getDestino().getUbicacion().getLatitud());
				ubicacionD.setLogitud(o.getDestino().getUbicacion().getLatitud());
				
				destinoD.setUbicacion(ubicacionD);
															
				ofertaD.setDestino(destinoD);
				
				ofertaD.setEstado(o.getEstado());
				ofertaD.setFechaRegreso(o.getFechaRegreso());
				ofertaD.setFechaSalida(o.getFechaSalida());
				

				ofertaD.setFoto(o.getFoto());
				
				
				
				ArrayList<MedioDePagoDTO> mediosD = new ArrayList<MedioDePagoDTO>();
				for(MedioDePago m: o.getMediosDePagos()){
					MedioDePagoDTO medioD = new MedioDePagoDTO();
					medioD.setIdMP(m.getIdMP());
					medioD.setNombre(m.getNombre());
					mediosD.add(medioD);
					
				}
				ofertaD.setMediosDePagos(mediosD);
				
				ofertaD.setNombre(o.getNombre());
				
				ofertaD.setPoliticaCancelacion(o.getPoliticaCancelacion());
				ofertaD.setPrecioXPersona(o.getPrecioXPersona());
				
				
				ArrayList<ServicioDTO> serviciosD = new ArrayList<ServicioDTO>();
				for(Servicio s: o.getServicios()){
					ServicioDTO servicioD = new ServicioDTO();
					servicioD.setDescripcion(s.getDescripcion());
					serviciosD.add(servicioD);
					
					
				}
				ofertaD.setServicios(serviciosD);
				ofertaD.setIdPaquete(o.getIdPaquete());
				
				
				AgenciaDTO agenciaDto = new AgenciaDTO();
				agenciaDto.setDireccion(o.getAgencia().getDireccion());
				agenciaDto.setEstado(o.getAgencia().getEstado());
				agenciaDto.setId(o.getAgencia().getId());
				agenciaDto.setIdBO(o.getAgencia().getIdBO());
				agenciaDto.setMail(o.getAgencia().getMail());
				agenciaDto.setNombre(o.getAgencia().getNombre());
				
				ofertaD.setAgencia(agenciaDto);
				
				ofertasD.add(ofertaD);
			}
			
			return ofertasD;
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println("Conectando a " + e.getMessage());
		}
		return null;
	}
	
	
	@Override
	public void altaMedios(List<MedioDePagoDTO> medios) {
		for (MedioDePagoDTO m : medios){
			MedioDePago medioEntity = new MedioDePago();
			medioEntity.setNombre(m.getNombre());
			manager.persist(medioEntity);			
		}				
	}

	@Override
	public void altaServicio(List<ServicioDTO> servicios) {
		for (ServicioDTO s : servicios){
			Servicio servicioEntity = new Servicio();
			servicioEntity.setIdServicio(s.getIdServicio());
			servicioEntity.setDescripcion(s.getDescripcion());
			manager.persist(servicioEntity);			
		}	
	}
	
	
	@Override
	public void altaDestino(List<DestinoDTO> destinosDto) {
		for (DestinoDTO d : destinosDto){
			Destino destinoEntity = new Destino();
			destinoEntity.setNombre(d.getNombre());
			Ubicacion ubicacionEntity = new Ubicacion();
			ubicacionEntity.setLatitud(d.getUbicacion().getLatitud());
			ubicacionEntity.setLogitud(d.getUbicacion().getLogitud());
			destinoEntity.setUbicacion(ubicacionEntity);			
			manager.persist(destinoEntity);
		}
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<MedioDePagoDTO> recuperarMedios() {
		try {
			Query q = manager.createQuery("Select a from MedioDePago a");
			List<MedioDePago> medios = (List<MedioDePago>) q.getResultList();
			ArrayList<MedioDePagoDTO> mediosD = new ArrayList<MedioDePagoDTO>();

			for (MedioDePago m : medios) {
				MedioDePagoDTO medioD = new MedioDePagoDTO();
				medioD.setIdMP(m.getIdMP());
				medioD.setNombre(m.getNombre());
				mediosD.add(medioD);
			}
			
			return mediosD;
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println("Conectando a " + e.getMessage());
		}
		return null;
	}
	
	//TODO: ver si conviene agregar una capa DAO que obtenga estos datos.	
	
	private Servicio recuperarServicio(int idServicio) {
		Servicio servicio = manager.find(Servicio.class, idServicio);
		return servicio;
	}	
	
	
	private MedioDePago recuperarMedio(int idMedio) {		
		MedioDePago medio = manager.find(MedioDePago.class, idMedio);
		return medio;						
	}
	
		
	private Destino recuperarDestino(int idDestino) {		
		Destino destino = manager.find(Destino.class, idDestino);
		return destino;			
	}
	
	
	private Agencia recuperarAgencia(int idAgencia) {		
		Agencia agencia = manager.find(Agencia.class, idAgencia);
		return agencia;				
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DestinoDTO> recuperarDestinos() {
		try {
			Query q = manager.createQuery("Select a from Destino a");
			List<Destino> destinos = (List<Destino>) q.getResultList();
			ArrayList<DestinoDTO> destinosD = new ArrayList<DestinoDTO>();

			for (Destino d : destinos) {
				DestinoDTO destinoD = new DestinoDTO();
				destinoD.setIdDestino(d.getIdDestino());
				destinoD.setNombre(d.getNombre());
				UbicacionDTO ubi = new UbicacionDTO();
				ubi.setLatitud(d.getUbicacion().getLatitud());
				ubi.setLogitud(d.getUbicacion().getLogitud());
				
				destinoD.setUbicacion(ubi);								
				destinosD.add(destinoD);
			}
			
			return destinosD;
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println("Conectando a " + e.getMessage());
		}
		return null;
	}

}
