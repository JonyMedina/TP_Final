package com.managers;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.dto.AgenciaDTO;
import com.dto.MedioDePagoDTO;


@Remote
public interface AdmAgenciasRemote {

	public void altaMedios(MedioDePagoDTO medioDTO);

	public void altaAgencia(AgenciaDTO agencia);

	public ArrayList<AgenciaDTO> recuperarAgencias();

	
	
	
}
