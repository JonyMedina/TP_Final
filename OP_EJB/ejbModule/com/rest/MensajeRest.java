package com.rest;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dto.*;
import com.google.gson.Gson;


@Stateless
@LocalBean
public class MensajeRest implements MensajeRestRemote, MensajeRestLocal {

	//private String urlServiciosBackoffice = "http://localhost:3000";
	//private String urlServiciosBackoffice = "http://192.168.130.104:8080/IA_TPO_BO_G05_DWS/REST";
	private String urlServiciosBackoffice = "http://192.168.0.154:8080/BackOffice/REST"; 
    
	public MensajeRest() {}
    
	public String envioAgenciaJSON(String agencia) throws IOException{	
		URL url = new URL(urlServiciosBackoffice + "/solicitudes");
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("PUT"); 
		//urlConnection.setRequestMethod("POST"); 
	  	urlConnection.setRequestProperty("Content-Type","application/json");
	  	
	 // Send post request
	  	urlConnection.setDoOutput(true);
	 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
	 	wr.writeBytes(agencia);
	 	wr.flush();
	 	wr.close();

	 	int responseCode = urlConnection.getResponseCode();
	 	System.out.println("------------------------------------------");
	 	System.out.println("\tSending 'PUT' request to URL : " + url);
	 	System.out.println("\tPost parameters : " + agencia);
	 	System.out.println("\tResponse Code : " + responseCode);
	 	System.out.println("------------------------------------------");

	 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 	String inputLine;
	 	StringBuffer response = new StringBuffer();

	 	while((inputLine = in.readLine()) != null) {
	 		response.append(inputLine);
	 	}
		in.close();

		System.out.println("------------------------------------------");
		System.out.println("\tRespuesta de backoffice: ");
		System.out.println(response.toString());
		System.out.println("------------------------------------------");
		return response.toString();
// 		Gson gson = new Gson();
// 		ResponseServicioAltaAgenciaDTO responseBackoffice = gson.fromJson(response.toString(), ResponseServicioAltaAgenciaDTO.class);
// 		return responseBackoffice; 
	}    

	public void envioLog (String log) throws IOException{
		
		URL url = new URL(urlServiciosBackoffice + "/logs");
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("PUT");
		//urlConnection.setRequestMethod("POST"); 
	  	urlConnection.setRequestProperty("Content-Type","application/json");	  	

	  	urlConnection.setDoOutput(true);
	 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
	 	wr.writeBytes(log);
	 	wr.flush();
	 	wr.close();

	 	int responseCode = urlConnection.getResponseCode();
	 	System.out.println("------------------------------------------");
	 	System.out.println("\nSending 'PUT' request to URL : " + url);
	 	System.out.println("Post parameters : " + log);
	 	System.out.println("Response Code : " + responseCode);
	 	System.out.println("------------------------------------------");

	 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 	String inputLine;
	 	StringBuffer response = new StringBuffer();

	 	while((inputLine = in.readLine()) != null) {
	 		response.append(inputLine);
	 	}
		in.close();
// 		System.out.println(response.toString());
	}

	public List<JsonTipoServicio> recuperarServicios() throws IOException{
		
			URL url = new URL(urlServiciosBackoffice + "/servicios");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");
		  	urlConnection.setRequestProperty("Content-Type","application/json");
		  	
//		  	urlConnection.setDoOutput(true);
//		 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
//		 	wr.writeBytes(tipo);
//		 	wr.flush();
//		 	wr.close();

		 	int responseCode = urlConnection.getResponseCode();
		 	System.out.println("------------------------------------------");
		 	System.out.println("\nSending 'GET' request to URL : " + url);
		 	//System.out.println("Post parameters : " + tipo);
		 	System.out.println("Response Code : " + responseCode);
		 	System.out.println("------------------------------------------");

		 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),StandardCharsets.UTF_8));
		 	String inputLine;
		 	StringBuffer response = new StringBuffer();

		 	while((inputLine = in.readLine()) != null) {
		 		response.append(inputLine);
		 	}
			in.close();

			//print result
			System.out.println("------------------------------------------");
			System.out.println("\t Respuesta de los servicios:");
	 		System.out.println(response.toString());
	 		System.out.println("------------------------------------------");
	 		
	 		Gson gson = new Gson();
	 		JsonTipoServicio[] servicios = gson.fromJson(response.toString(), JsonTipoServicio[].class);
	 		
	 		List<JsonTipoServicio> lstReturn = new ArrayList<JsonTipoServicio>();
	 		for (JsonTipoServicio jsonTipoServicio : servicios) {
				lstReturn.add(jsonTipoServicio);
			}
	 		return lstReturn;
	}
}
