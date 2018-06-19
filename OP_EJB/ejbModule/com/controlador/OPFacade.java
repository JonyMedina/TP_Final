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
import com.entities.*;
import com.google.gson.Gson;


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
			//ofertaProductor.sendMessage("");
		}
	
	//TP

	@Override
	public void altaAgencia(AgenciaDTO agencia) {				
						
		admAgencia.altaAgencia(agencia);
		
//		Gson gson = new Gson();
//	  	try 
//	  	{													
//	  		JsonAgencia2 agenciaJson = controladorRest.envioAgenciaJSON(gson.toJson(agencia.toJason()));
//	  		JsonLog log = new JsonLog("BackOficce 1","Alta Agencia","OK"); //ver que enviar al log
//	  		controladorRest.envioLog(gson.toJson(log));
//	  		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}					
	}

	@Override
	public ArrayList<AgenciaDTO> recuperarAgencias() {

		return admAgencia.recuperarAgencias();
	}

	@Override
	public void altaPaquete(OfertaPaqueteDTO ofertaDto) {
				
		admOfertas.altaPaquete(ofertaDto);
				
//		Gson gson = new Gson();
//  		ofertaProductor.sendMessage1(gson.toJson(ofertaDto.toJson()));
// 		ofertaProductor.sendMessage2(gson.toJson(ofertaDto.toJson()));
// 		
// 		JsonLog log  = new JsonLog("Portal Web 4","Alta Agencia","OK");
// 		JsonLog log2 = new JsonLog("Portal Web 7","Alta Agencia","OK");
//		try {
//			controladorRest.envioLog(gson.toJson(log));
//			controladorRest.envioLog(gson.toJson(log2));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
