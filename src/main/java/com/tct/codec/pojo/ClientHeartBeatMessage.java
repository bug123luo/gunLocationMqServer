package com.tct.codec.pojo;

public class ClientHeartBeatMessage extends SimpleMessage {
	
	private ClientInWareHouseBody clientInWareHouseBody;

	public ClientInWareHouseBody getClientInWareHouseBody() {
		return clientInWareHouseBody;
	}

	public void setClientInWareHouseBody(ClientInWareHouseBody clientInWareHouseBody) {
		this.clientInWareHouseBody = clientInWareHouseBody;
	}
	
	
}
