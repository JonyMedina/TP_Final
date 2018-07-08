package com.dto;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

public class OPLog implements Serializable {
	private static final long serialVersionUID = 1L;
	/*	
	"idLog":”",      //Nulo
	"modulo":1,	//usar la tabla de abajo para cada acción
	"accion":1,	//usar la tabla de abajo para cada acción
	"fecha": "2018-06-19"	
	 */
	//private String idLog;
	private int modulo;
	private int accion;
	private long fecha;
	
	public OPLog(int accion) {
		
		//this.idLog = "";
		this.modulo = 2; //2-Oferta Paquete
		this.accion = accion;  //3: Nueva agencia / 4: Nueva oferta
		this.fecha = ObtenerFecha();
	}

	private long ObtenerFecha() {
		Date date = new Date();
//		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
//		return formatter.format(date);
		return date.getTime();
	}

	public String ToJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}




//	public String getIdLog() {
//		return idLog;
//	}
//
//
//
//
//	public void setIdLog(String idLog) {
//		this.idLog = idLog;
//	}




	public int getModulo() {
		return modulo;
	}




	public void setModulo(int modulo) {
		this.modulo = modulo;
	}




	public int getAccion() {
		return accion;
	}




	public void setAccion(int accion) {
		this.accion = accion;
	}




	public long getFecha() {
		return fecha;
	}




	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

}
