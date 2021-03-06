package com.dto;

import java.io.Serializable;
import java.util.List;

public class JsonOferta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nombre;
	private JsonDestino ciudadDestino;
	private int cupo;
	private int cantPersonas;
	private JsonAgenciaenPaquete agencia;
	private String foto;
	private String fechaDesde;
	private String fechaHasta;
	private String estado;
	private float precio;
	private String descripcion;
	private String politicas;
	private List<ServicioDTO> servicios;
	private List<MedioDePagoDTO> mediosDePago;

	
	public JsonOferta(OfertaPaqueteDTO ofertaPaqueteDTO) {
		
		this.setId(ofertaPaqueteDTO.getIdPaquete()==null?0:ofertaPaqueteDTO.getIdPaquete());
		this.setNombre(ofertaPaqueteDTO.getNombre());
		this.setCiudadDestino(ofertaPaqueteDTO.getDestino().ToDestinoJson());
		this.setCupo(ofertaPaqueteDTO.getCupo());
		this.setCantPersonas(ofertaPaqueteDTO.getCantidadPersonas());
		this.setAgencia(ofertaPaqueteDTO.getAgencia().ToJsonAgenciaenPaquete());
		this.setFoto(ofertaPaqueteDTO.getFoto());
		this.setfechaDesde(ofertaPaqueteDTO.getFechaSalida().toString());
		this.setfechaHasta(ofertaPaqueteDTO.getFechaRegreso().toString());
		this.setEstado(ofertaPaqueteDTO.getEstado());
		this.setPrecio(ofertaPaqueteDTO.getPrecioXPersona());
		this.setDescripcion(ofertaPaqueteDTO.getDescripcion());
		this.setPoliticas(ofertaPaqueteDTO.getPoliticaCancelacion());
		this.setServicios(ofertaPaqueteDTO.getServicios());
		
	}
	
	
	

	public String getfechaDesde() {
		return fechaDesde;
	}
	public void setfechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getfechaHasta() {
		return fechaHasta;
	}
	public void setfechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public String getPoliticas() {
		return politicas;
	}
	public void setPoliticas(String politicas) {
		this.politicas = politicas;
	}


	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



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



	public JsonDestino getCiudadDestino() {
		return ciudadDestino;
	}



	public void setCiudadDestino(JsonDestino ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}




	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	public List<ServicioDTO> getServicios() {
		return servicios;
	}



	public void setServicios(List<ServicioDTO> servicios) {
		this.servicios = servicios;
	}
	
	
	public List<MedioDePagoDTO> getMediosDePago() {
		return mediosDePago;
	}



	public void setMediosDePago(List<MedioDePagoDTO> mediosDePago) {
		this.mediosDePago = mediosDePago;
	}



	public JsonAgenciaenPaquete getAgencia() {
		return agencia;
	}



	public void setAgencia(JsonAgenciaenPaquete agencia) {
		this.agencia = agencia;
	}
	public int getCupo() {
		return cupo;
	}




	public void setCupo(int cupo) {
		this.cupo = cupo;
	}




	public int getCantPersonas() {
		return cantPersonas;
	}




	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

}
