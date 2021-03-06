package com.dto;

import java.io.Serializable;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class JsonAgencia implements Serializable {
	
	/*
		"idSolicitud":"",            //Nulo
		"codEntidad":"",       //Nulo
		"nombre":"Faena",
		"estado":"" ,		//Nulo
		"direccion": "Juana Manso 1001",
		"tipo": 1		//1 si es hotel 2 si es agencia 
	 */
	
//	private String idSolicitud;
//	private String codEntidad;
	private String nombre;
//	private String estado;
	private String direccion;
	private int tipo;
	
	public JsonAgencia(AgenciaDTO agenciaDTO) {
//		this.setIdSolicitud("");
//		this.setCodEntidad("");
		this.setNombre(agenciaDTO.getNombre());
//		this.setEstado("");
		this.setDireccion(agenciaDTO.getDireccion());
		this.setTipo(2);//1 si es hotel 2 si es agencia 
	}

	public String ToJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
//	public String getIdSolicitud() {
//		return idSolicitud;
//	}
//
//	public void setIdSolicitud(String idSolicitud) {
//		this.idSolicitud = idSolicitud;
//	}
//
//	public String getCodEntidad() {
//		return codEntidad;
//	}
//
//	public void setCodEntidad(String codEntidad) {
//		this.codEntidad = codEntidad;
//	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public String getEstado() {
//		return estado;
//	}
//
//	public void setEstado(String estado) {
//		this.estado = estado;
//	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}


	
	
	

}
