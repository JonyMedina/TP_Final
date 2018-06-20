package com.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import com.dto.*;


@Remote
public interface OPFacadeRemote {
		
	public String sayHello(String name);	
	public void altaMedios(MedioDePagoDTO medioDto);
	
	
	//TP
	public void altaAgencia(AgenciaDTO agencia);
	public ArrayList<AgenciaDTO> recuperarAgencias();
	public void altaPaquete(OfertaPaqueteDTO oferta);
	public ArrayList<OfertaPaqueteDTO> recuperarPaquetes();
	public void altaMedios(List<MedioDePagoDTO> medios);
	public void altaServicios(List<ServicioDTO> servicios);
	public void altaDestinos(List<DestinoDTO> destinos);
	public List<MedioDePagoDTO> recuperarMedios();
	public List<ServicioDTO> recuperarServicios();
	public List<DestinoDTO> recuperarDestinos();
}
