package com.managers;

import javax.ejb.Remote;

import com.dto.CategoriaDTO;
import com.dto.ProductoDTO;

@Remote
public interface AdmProductosRemote {

    public void altaCategoria(CategoriaDTO categoriaDto);
    public void altaProducto(ProductoDTO productoDto);	
}
