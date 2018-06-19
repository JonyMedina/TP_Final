package com.mensajeria;

import javax.ejb.Remote;

@Remote
public interface OfertaProductorRemote {
	public void sendMessage(String messageText);
}
