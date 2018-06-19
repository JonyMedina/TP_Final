package com.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;

import com.dto.*;

@Remote
public interface AdmOfertasRemote {

	void altaPaquete(OfertaPaqueteDTO oferta);
	void altaMedios(List<MedioDePagoDTO> medios);
	void altaServicio(List<ServicioDTO> servicios);
	void altaDestino(List<DestinoDTO> destinosDto);
	List<MedioDePagoDTO> recuperarMedios();
	List<DestinoDTO> recuperarDestinos();
	ArrayList<OfertaPaqueteDTO> recuperarPaquetes();
	

}
