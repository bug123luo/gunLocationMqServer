package com.tct.codec.pojo;

public class ServerInWareHouseReplyMessage extends SimpleMessage {
	
	private ServerInWareHouseReplyBody serverInWareHouseReplyBody;

	public ServerInWareHouseReplyBody getServerInWareHouseReplyBody() {
		return serverInWareHouseReplyBody;
	}

	public void setServerInWareHouseReplyBody(ServerInWareHouseReplyBody serverInWareHouseReplyBody) {
		this.serverInWareHouseReplyBody = serverInWareHouseReplyBody;
	}

}
