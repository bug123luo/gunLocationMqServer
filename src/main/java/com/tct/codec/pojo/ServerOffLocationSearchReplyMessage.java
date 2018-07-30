package com.tct.codec.pojo;

public class ServerOffLocationSearchReplyMessage extends SimpleMessage {
	
	private ServerOffLocationSearchBody serverOffLocationSearchBody;

	public ServerOffLocationSearchBody getServerOffLocationSearchBody() {
		return serverOffLocationSearchBody;
	}

	public void setServerOffLocationSearchBody(ServerOffLocationSearchBody serverOffLocationSearchBody) {
		this.serverOffLocationSearchBody = serverOffLocationSearchBody;
	}
	
	
}
