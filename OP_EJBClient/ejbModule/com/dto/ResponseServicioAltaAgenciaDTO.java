package com.dto;

import java.io.Serializable;

public class ResponseServicioAltaAgenciaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idAgenciaGeneradoBackOffice;

	public int getIdAgenciaGeneradoBackOffice() {
		return idAgenciaGeneradoBackOffice;
	}

	public void setIdAgenciaGeneradoBackOffice(int idAgenciaGeneradoBackOffice) {
		this.idAgenciaGeneradoBackOffice = idAgenciaGeneradoBackOffice;
	}

}
