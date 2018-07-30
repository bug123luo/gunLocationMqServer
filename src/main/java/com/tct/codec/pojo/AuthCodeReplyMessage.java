package com.tct.codec.pojo;

public class AuthCodeReplyMessage extends SimpleMessage{

	private AuthCodeReplyBody authCodeReplyBody;

	public AuthCodeReplyBody getAuthCodeReplyBody() {
		return authCodeReplyBody;
	}

	public void setAuthCodeReplyBody(AuthCodeReplyBody authCodeReplyBody) {
		this.authCodeReplyBody = authCodeReplyBody;
	}
	
	
}
