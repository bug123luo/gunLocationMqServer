package com.tct.codec.pojo;

public class ServerOffLocationWarningStartStopMessage extends SimpleMessage {
	
	private ServerOffLocationWarningStartStopBody serverOffLocationWarningStartStopBody;

	public ServerOffLocationWarningStartStopBody getServerOffLocationWarningStartStopBody() {
		return serverOffLocationWarningStartStopBody;
	}

	public void setServerOffLocationWarningStartStopBody(
			ServerOffLocationWarningStartStopBody serverOffLocationWarningStartStopBody) {
		this.serverOffLocationWarningStartStopBody = serverOffLocationWarningStartStopBody;
	}
	
	
}
