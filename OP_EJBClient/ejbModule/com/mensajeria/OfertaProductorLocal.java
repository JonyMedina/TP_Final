package com.mensajeria;

import javax.ejb.Local;

@Local
public interface OfertaProductorLocal {

	public void sendMessage(String messageText);
}
