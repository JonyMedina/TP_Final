package com.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dto.*;
import com.managers.*;
import com.mensajeria.*;
import com.rest.*;

@Stateless
@LocalBean
public class OPFacade implements OPFacadeRemote, OPFacadeLocal {

	@EJB
	AdmAgenciasRemote admAgencia;

	@EJB
	AdmOfertasRemote admOfertas;

	@EJB
	OfertaProductor ofertaProductor;

	@EJB
	MensajeRest controladorRest;

	public OPFacade() {

	}

	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public void altaMedios(MedioDePagoDTO medioDto) {
		admAgencia.altaMedios(medioDto);
	}

	// TP

	@Override
	public void altaAgencia(AgenciaDTO agencia) {

		try {
			Integer idAgenciaCreada = admAgencia.altaAgencia(agencia);
			agencia.setId(idAgenciaCreada);
			
			String responseBackOffice = controladorRest.envioAgenciaJSON(agencia.toJson());
			agencia.setIdBO(responseBackOffice); 
			
			admAgencia.actualizarAgencia(agencia);
			
			 controladorRest.envioLog(new OPLog(7).ToJson());//7-nueva agencia

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<AgenciaDTO> recuperarAgencias() {

		return admAgencia.recuperarAgencias();
	}

	@Override
	public void altaPaquete(OfertaPaqueteDTO ofertaDto) {
		try 
		{
			admOfertas.altaPaquete(ofertaDto);
			ofertaDto.setDestino(admOfertas.recuperarDestinoDTO(ofertaDto.getDestino().getIdDestino())); //leo de la base para recuperar el idCiudad y todo. 

			ofertaDto.setAgencia(admAgencia.recuperarAgencia(ofertaDto.getAgencia().getId()));
			
			
			ofertaProductor.sendMessage(ofertaDto.toJson());
			
			System.out.println(ofertaDto.toJson());
			
			controladorRest.envioLog(new OPLog(8).ToJson());//8- Nueva oferta paquete

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<OfertaPaqueteDTO> recuperarPaquetes() {
		try {
			return admOfertas.recuperarPaquetes();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void altaMedios(List<MedioDePagoDTO> medios) {
		admOfertas.altaMedios(medios);
	}

	@Override
	public void altaServicios(List<ServicioDTO> servicios) {
		admOfertas.altaServicio(servicios);
	}

	@Override
	public void altaDestinos(List<DestinoDTO> destinosDto) {
		admOfertas.altaDestino(destinosDto);
	}

	@Override
	public List<MedioDePagoDTO> recuperarMedios() {
		return admOfertas.recuperarMedios();
	}

	@Override
	public List<TipoServicioDTO> recuperarServicios() {
		
	List<TipoServicioDTO> lstReturn = new ArrayList<TipoServicioDTO>();
	try {				
			List<JsonTipoServicio> lstJsonServicio = controladorRest.recuperarServicios();
			for (JsonTipoServicio jsonTipoServicio : lstJsonServicio) {
				lstReturn.add(jsonTipoServicio.ToTipoServicioDTO());
			}
			return lstReturn;
		} catch (IOException e) {
			e.printStackTrace();
			TipoServicioDTO tipoError = new TipoServicioDTO();
			tipoError.setId(-1);
			tipoError.setNombre("Error llamada");
			tipoError.setServicios(new ArrayList<ServicioDTO>());
			return lstReturn;
		}
	}

	@Override
	public List<DestinoDTO> recuperarDestinos() {
		return admOfertas.recuperarDestinos();
	}

}
