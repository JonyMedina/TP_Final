package com.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class JsonTipoServicio implements Serializable {

	private int id;
	private String nombre;
	private List<JsonServicio> servicios = new ArrayList<JsonServicio>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<JsonServicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<JsonServicio> servicios) {
		this.servicios = servicios;
	}

	
	public TipoServicioDTO ToTipoServicioDTO() {
		TipoServicioDTO tipoServicioReturn = new TipoServicioDTO();
		tipoServicioReturn.setId(this.getId());
		tipoServicioReturn.setNombre(this.getNombre());
		
		List<ServicioDTO> lstAux = new ArrayList<ServicioDTO>();
		for (JsonServicio jsonServicio : servicios) {
			lstAux.add(jsonServicio.toDTO());
		}
		
		tipoServicioReturn.setServicios(lstAux);
		return tipoServicioReturn;
	}

}
