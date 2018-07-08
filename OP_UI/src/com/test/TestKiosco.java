package com.test;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.controlador.OPFacadeKioscoRemote;
import com.controlador.OPFacadeRemote;
import com.dto.CategoriaDTO;
import com.dto.ProductoDTO;

public class TestKiosco {

	public static void main(String[] args) throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		final Context context = new InitialContext(jndiProperties);
		final String earName = "OP_EAR"; // Nombre del m�dulo EAR
		final String ejbModuleName = "OP_EJB"; // Nombre del m�dulo EJB
		final String distinctName = ""; // Opcional para resolver repeticiones en nombres
										
		final String ejbClassName = "OPFacadeKiosco"; // Nombre corto del EJB
		final String fullInterfaceName = OPFacadeKioscoRemote.class.getName();
		String lookupName = "ejb:" + earName + "/" + ejbModuleName + "/" + distinctName + "/" + ejbClassName + "!"
				+ fullInterfaceName;
		System.out.println("Conectando a " + lookupName);
		OPFacadeKioscoRemote mbr = (OPFacadeKioscoRemote) context.lookup(lookupName);
		//System.out.println("Respuesta: " + mbr.sayHello("Mr Bean"));

		mbr.altaCategoria(new CategoriaDTO("Bebidas"));
		
		CategoriaDTO catAux = new CategoriaDTO("");
		catAux.setId(1);
		ProductoDTO productoDto = new ProductoDTO();
		productoDto.setCategoria(catAux);
		mbr.altaProductos(productoDto);
	}

}
