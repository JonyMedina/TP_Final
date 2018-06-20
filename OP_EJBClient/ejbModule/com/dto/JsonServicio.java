package com.dto;

import java.io.Serializable;

public class JsonServicio implements Serializable {

	private int id;
	private String nombre;

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

	private static final long serialVersionUID = 1L;

	public ServicioDTO toDTO() {
		ServicioDTO servicioDTO = new ServicioDTO();
		servicioDTO.setDescripcion(this.getNombre());
		servicioDTO.setIdServicio(this.getId());
		return servicioDTO;
	}
}
