package com.rest;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import java.util.*;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.dto.*;
import com.google.gson.Gson;


@Stateless
@LocalBean
public class MensajeRest implements MensajeRestRemote, MensajeRestLocal {


    public MensajeRest() {

    }
    
    
	public ResponseServicioAltaAgenciaDTO envioAgenciaJSON(String agencia) throws IOException{
	
		//URL url = new URL("http://192.168.1.92:8080/rest/agencias");
		
		URL url = new URL("http://localhost:3000/agencias");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
	  	urlConnection.setRequestProperty("Content-Type","application/json");
	  	
	 // Send post request
	  	urlConnection.setDoOutput(true);
	 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
	 	wr.writeBytes(agencia);
	 	wr.flush();
	 	wr.close();

	 	int responseCode = urlConnection.getResponseCode();
	 	System.out.println("\nSending 'POST' request to URL : " + url);
	 	System.out.println("Post parameters : " + agencia);
	 	System.out.println("Response Code : " + responseCode);

	 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 	String inputLine;
	 	StringBuffer response = new StringBuffer();

	 	while((inputLine = in.readLine()) != null) {
	 		response.append(inputLine);
	 	}
		in.close();

		//print result
		System.out.println(response.toString());
 		Gson gson = new Gson();
 		ResponseServicioAltaAgenciaDTO responseBackoffice = gson.fromJson(response.toString(), ResponseServicioAltaAgenciaDTO.class);
// 		System.out.println("Id: "+agencia2.getId());
 		return responseBackoffice; 
	}    

	public void envioLog (String log) throws IOException{
		
//		URL url = new URL("http://192.168.1.92:8080/TPO_BO_WEB/rest/ServiciosBO/RegistrarLog");
		URL url = new URL("http://localhost:3000/EnviarLog");
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);
		urlConnection.setRequestMethod("POST");
	  	urlConnection.setRequestProperty("Content-Type","application/json");	  	
// Send post request
	  	urlConnection.setDoOutput(true);
	 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
	 	wr.writeBytes(log);
	 	wr.flush();
	 	wr.close();

	 	int responseCode = urlConnection.getResponseCode();
	 	System.out.println("\nSending 'POST' request to URL : " + url);
	 	System.out.println("Post parameters : " + log);
	 	System.out.println("Response Code : " + responseCode);

	 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	 	String inputLine;
	 	StringBuffer response = new StringBuffer();

	 	while((inputLine = in.readLine()) != null) {
	 		response.append(inputLine);
	 	}
		in.close();

//		//print result
// 		System.out.println(response.toString());

	}

	public List<JsonTipoServicio> recuperarServicios() throws IOException{

		
			//URL url = new URL("http://192.168.1.92:8080/rest/servicios"); TODO: ESTA COMENTADA PARA PRUEBA LOCAL SOLAMENTE!!!
		
			URL url = new URL("http://localhost:3000/servicios");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setRequestMethod("GET");
		  	urlConnection.setRequestProperty("Content-Type","application/json");
		  	
		 // Send request
//		  	urlConnection.setDoOutput(true);
//		 	DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
//		 	wr.writeBytes(tipo);
//		 	wr.flush();
//		 	wr.close();

		 	int responseCode = urlConnection.getResponseCode();
		 	System.out.println("\nSending 'GET' request to URL : " + url);
		 	//System.out.println("Post parameters : " + tipo);
		 	System.out.println("Response Code : " + responseCode);

		 	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		 	String inputLine;
		 	StringBuffer response = new StringBuffer();

		 	while((inputLine = in.readLine()) != null) {
		 		response.append(inputLine);
		 	}
			in.close();

			//print result
	 		System.out.println(response.toString());
	 		
	 		Gson gson = new Gson();
	 		JsonTipoServicio[] servicios = gson.fromJson(response.toString(), JsonTipoServicio[].class);
	 		
	 		
	 		List<JsonTipoServicio> lstReturn = new ArrayList<JsonTipoServicio>();
	 		for (JsonTipoServicio jsonTipoServicio : servicios) {
				lstReturn.add(jsonTipoServicio);
			}
	 		
	 		return lstReturn;
	}
}
