package com.controlador;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dto.*;
import com.managers.AdmProductos;
import com.managers.AdmProductosRemote;


@Stateless
@LocalBean
public class OPFacadeKiosco implements OPFacadeKioscoRemote, OPFacadeKioscoLocal {

  
	@EJB
	AdmProductosRemote admProductos;
	
	
    public OPFacadeKiosco() {
    }
    
    public void altaCategoria(CategoriaDTO categoriaDto){
    	admProductos.altaCategoria(categoriaDto);
    }
    
    public void altaProductos(ProductoDTO productoDto){
    	admProductos.altaProducto(productoDto);	
    }

}
