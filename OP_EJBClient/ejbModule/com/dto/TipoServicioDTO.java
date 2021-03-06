package com.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TipoServicioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<ServicioDTO> getServicios() {
		return servicios;
	}
	public void setServicios(List<ServicioDTO> servicios) {
		if(this.servicios==null){
			this.servicios = new ArrayList<ServicioDTO>();
		}
		
		this.servicios = servicios;
	}
	private int id;
	private String nombre;
	private List<ServicioDTO> servicios = new ArrayList<ServicioDTO>();
	
}
