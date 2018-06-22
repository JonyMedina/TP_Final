package com.dto;

public class AgenciaMensaje {
		private int id;
		private String nombre;
		private String direccion;
		private String estado; // INACTIVO, ACTIVO
		
		public AgenciaMensaje(int id, String nombre, String direccion, String estado) {
			this.setId(id);
			this.setNombre(nombre);
			this.setDireccion(direccion);
			this.setEstado(estado);
			
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
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
}