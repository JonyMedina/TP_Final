package com.controlador;

import javax.ejb.Local;

import com.dto.CategoriaDTO;
import com.dto.ProductoDTO;

@Local
public interface OPFacadeKioscoLocal {
    public void altaCategoria(CategoriaDTO categoriaDto);
    
    public void altaProductos(ProductoDTO productoDto);
}
