package com.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

import com.dto.*;
import com.entities.*;



@Stateless
@LocalBean
public class AdmAgencias implements AdmAgenciasRemote, AdmAgenciasLocal {

	@PersistenceContext(unitName="MyPU")
	private EntityManager manager;
	
    public AdmAgencias() {
    }
    
	@Override
	public void altaMedios(MedioDePagoDTO medioDTO) {
		try{
			
			MedioDePago medioEntity = new MedioDePago();
			medioEntity.setNombre(medioDTO.getNombre());
    		manager.persist(medioEntity);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Conectando a " + e.getMessage());
    	}
	}


	@Override
	public void altaAgencia(AgenciaDTO agencia) {				
		Agencia agenciaNew = new Agencia();				
		agenciaNew.setDireccion(agencia.getDireccion());
		agenciaNew.setEstado(agencia.getEstado());
		agenciaNew.setId(agencia.getId());
		agenciaNew.setMail(agencia.getMail());
		agenciaNew.setNombre(agencia.getNombre());
		agenciaNew.setIdBO(0); //???		
		manager.persist(agenciaNew);						
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AgenciaDTO> recuperarAgencias() {
		try {
			Query q = manager.createQuery("Select a from Agencia a");
			List<Agencia> agencia = (List<Agencia>) q.getResultList();
			ArrayList<AgenciaDTO> agencias = new ArrayList<AgenciaDTO>();

			for (Agencia a : agencia) {
				AgenciaDTO agenciaD = new AgenciaDTO();
				agenciaD.setDireccion(a.getDireccion());
				agenciaD.setEstado(a.getEstado());
				agenciaD.setId(a.getId());
				agenciaD.setMail(a.getMail());
				agenciaD.setNombre(a.getNombre());
				agencias.add(agenciaD);
			}
			
			return agencias;
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println("Conectando a " + e.getMessage());
		}
		return null;
	}

	
	


}
