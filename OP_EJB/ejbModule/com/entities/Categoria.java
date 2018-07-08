package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



@Entity
@Table(name="Categorias")
public class Categoria implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String descripcion;
	
	
	@OneToMany
	@JoinColumn(name="producto")
	private List<Producto> productos;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}

