package com.tct.codec.pojo;

public class ClientHeartBeatReplyMessage extends SimpleMessage {
	
	private ClientHeartBeatReplyBody clientHeartBeatReplyBody;

	public ClientHeartBeatReplyBody getClientHeartBeatReplyBody() {
		return clientHeartBeatReplyBody;
	}

	public void setClientHeartBeatReplyBody(ClientHeartBeatReplyBody clientHeartBeatReplyBody) {
		this.clientHeartBeatReplyBody = clientHeartBeatReplyBody;
	}
	
	
	
}
