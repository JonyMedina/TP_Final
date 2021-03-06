package com.dto;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class OfertaPaqueteMensaje {
	private int id;
	private String nombre;
	//private CiudadMensaje ciudadDestino;
	private int cupo;
	private int cantPersonas;
	private AgenciaMensaje agencia;
	private String foto;
	private String fechaDesde; // Ej: 2007-04-05T12:30-02:00
	private String fechaHasta; // Ej: 2007-04-05T12:30-02:00
	private String estado; // INACTIVO, ACTIVO
	private float precio;
	private String descripcion;
	private String politicas;
	private String servicios;
	private String mediosDePago;
	private int ciudadDestino;
	
	public OfertaPaqueteMensaje(OfertaPaqueteDTO ofertaPaqueteDTO) {
	
		this.setId(ofertaPaqueteDTO.getIdPaquete()==null?0:ofertaPaqueteDTO.getIdPaquete());
		this.setNombre(ofertaPaqueteDTO.getNombre());
		//this.setCiudadDestino(new CiudadMensaje(ofertaPaqueteDTO.getDestino().getIdCiudadDestino()));
		this.ciudadDestino = ofertaPaqueteDTO.getDestino().getIdCiudadDestino();
		this.setCupo(ofertaPaqueteDTO.getCupo());
		this.setCantPersonas(ofertaPaqueteDTO.getCantidadPersonas());
		this.setAgencia(new AgenciaMensaje(ofertaPaqueteDTO.getAgencia().getIdBO(), 
										   ofertaPaqueteDTO.getAgencia().getNombre(),
										   ofertaPaqueteDTO.getAgencia().getDireccion(),
										   ofertaPaqueteDTO.getAgencia().getEstado()));
		this.setFoto(ofertaPaqueteDTO.getFoto());
		
		this.setFechaDesde(ObtenerFecha(ofertaPaqueteDTO.getFechaSalida()));
		this.setFechaHasta(ObtenerFecha(ofertaPaqueteDTO.getFechaRegreso()));
		
		this.setEstado(ofertaPaqueteDTO.getEstado());
		this.setPrecio(ofertaPaqueteDTO.getPrecioXPersona());
		this.setDescripcion(ofertaPaqueteDTO.getDescripcion());
		this.setPoliticas(ofertaPaqueteDTO.getPoliticaCancelacion());
		this.setServicios(ListaServiciosToString(ofertaPaqueteDTO.getServicios()));
		this.setMediosDePago(ListaMedioToString(ofertaPaqueteDTO.getMediosDePagos()));
		
	}
	
	private String ListaMedioToString(List<MedioDePagoDTO> lstMediosDePagos) {
		String strReturn = "";
		for (MedioDePagoDTO medioDePagoDTO : lstMediosDePagos) {
			if(strReturn.isEmpty())
				strReturn += medioDePagoDTO.getNombre();
			else
				strReturn += ", " + medioDePagoDTO.getNombre();
		}
		return strReturn;
	}

	private String ListaServiciosToString(List<ServicioDTO> lstServiciosDTO) {
		String strReturn = "";
		for (ServicioDTO servicioDTO : lstServiciosDTO) {
			if(strReturn.isEmpty())
				strReturn += servicioDTO.getDescripcion();
			else
				strReturn += ", " + servicioDTO.getDescripcion();
		}
		return strReturn;
	}

	private String ObtenerFecha(Date fecha) {// // Ej: 2007-04-05T12:30-02:00
		//Date date = new Date();
		//Format formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
//		return formatter.format(fecha);
		
		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.systemDefault()).format(fecha.toInstant());
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
//	public CiudadMensaje getCiudadDestino() {
//		return ciudadDestino;
//	}
//	public void setCiudadDestino(CiudadMensaje ciudadDestino) {
//		this.ciudadDestino = ciudadDestino;
//	}
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
	public AgenciaMensaje getAgencia() {
		return agencia;
	}
	public void setAgencia(AgenciaMensaje agencia) {
		this.agencia = agencia;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPoliticas() {
		return politicas;
	}
	public void setPoliticas(String politicas) {
		this.politicas = politicas;
	}
	public String getServicios() {
		return servicios;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	public String getMediosDePago() {
		return mediosDePago;
	}
	public void setMediosDePago(String mediosDePago) {
		this.mediosDePago = mediosDePago;
	}

}
