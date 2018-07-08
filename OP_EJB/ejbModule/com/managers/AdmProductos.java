package com.managers;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dto.CategoriaDTO;
import com.dto.ProductoDTO;
import com.entities.Agencia;
import com.entities.Categoria;
import com.entities.Producto;


@Stateless
@LocalBean
public class AdmProductos implements AdmProductosRemote, AdmProductosLocal {

	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;
    public AdmProductos() {   
    }
    
    public void altaCategoria(CategoriaDTO categoriaDto){
    	Categoria categoriaEntity = new Categoria();
    	categoriaEntity.setDescripcion(categoriaDto.getDescripcion());
    	manager.persist(categoriaEntity);
    }
    
    public void altaProducto(ProductoDTO productoDto){
    	Producto productoEntity = new Producto();
    	productoEntity.setDescripcion(productoDto.getDescripcion());
    	productoEntity.setFoto(productoDto.getFoto());
    	productoEntity.setPrecioVenta(productoDto.getPrecioVenta());
    	productoEntity.setCategoria(ObtenerCategoriaPorID(productoDto.getCategoria().getId()));
    	manager.persist(productoEntity);
    }

	private Categoria ObtenerCategoriaPorID(Integer id) {
		Categoria categoriaEntity = manager.find(Categoria.class, id);
		return categoriaEntity;
	}
    
    

}
