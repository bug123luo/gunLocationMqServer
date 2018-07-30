package com.tct.service;

import javax.jms.TextMessage;
import com.tct.codec.AuthCodeMessageCodec;
import com.tct.codec.MessageCodec;
import com.tct.codec.pojo.AuthCodeMessage;

public class ServiceSelector {
	
	public void handlerService(MessageCodec messageCodec,TextMessage textMessage) {
		if(messageCodec instanceof AuthCodeMessageCodec) {
			AuthCodeMessage authCodeMessage = null;
			try {
				authCodeMessage = (AuthCodeMessage) messageCodec.decode(textMessage.getText());
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
								
			//登录业务处理
			try {
				AuthCodeService authCodeService = SpringContextUtil.getBean("authCodeService");
				authCodeService.handleCodeMsg(authCodeMessage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
