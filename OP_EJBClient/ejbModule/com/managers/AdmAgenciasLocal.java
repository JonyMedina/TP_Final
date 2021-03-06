package com.managers;

import java.util.ArrayList;

import javax.ejb.Local;

import com.dto.*;


@Local
public interface AdmAgenciasLocal {

	public void altaMedios(MedioDePagoDTO medioDTO);

	public Integer altaAgencia(AgenciaDTO agencia);
	public void actualizarAgencia(AgenciaDTO agencia);
	public ArrayList<AgenciaDTO> recuperarAgencias();
	public AgenciaDTO recuperarAgencia(int clave);
}
