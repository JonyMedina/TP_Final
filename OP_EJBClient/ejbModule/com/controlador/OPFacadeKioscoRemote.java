package com.controlador;

import javax.ejb.Remote;

import com.dto.CategoriaDTO;
import com.dto.ProductoDTO;

@Remote
public interface OPFacadeKioscoRemote {
    public void altaCategoria(CategoriaDTO categoriaDto);
    
    public void altaProductos(ProductoDTO productoDto);
}
