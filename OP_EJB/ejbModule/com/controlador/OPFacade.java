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

		admAgencia.altaAgencia(agencia);

		try {

			controladorRest.envioAgenciaJSON(agencia.toJson());

			String messageLog = new JsonLog("BackOficce", "Alta Agencia", "PRUEBA JONATHAN").ToJson();
			controladorRest.envioLog(messageLog);

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

		admOfertas.altaPaquete(ofertaDto);

		ofertaProductor.sendMessage(ofertaDto.toJson());

		try {
			controladorRest.envioLog(new JsonLog("PruebaJONa", "Alta Oferta", "OK").ToJson());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<OfertaPaqueteDTO> recuperarPaquetes() {
		return admOfertas.recuperarPaquetes();
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
	public List<ServicioDTO> recuperarServicios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DestinoDTO> recuperarDestinos() {
		return admOfertas.recuperarDestinos();
	}

}
