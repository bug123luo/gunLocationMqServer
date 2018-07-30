package com.tct.service;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.swing.Spring;

import com.tct.codec.AuthCodeMessageCodec;
import com.tct.codec.MessageCodec;
import com.tct.codec.ServerInWareHouseMessageCodec;
import com.tct.codec.ServerOutWareHouseMessageCodec;
import com.tct.codec.pojo.AuthCodeMessage;
import com.tct.codec.pojo.ServerInWareHouseMessage;
import com.tct.codec.pojo.ServerOutWareHouseMessage;

public class ServiceSelector {
	
	public void handlerService(MessageCodec messageCodec,TextMessage textMessage) {
		if(messageCodec instanceof AuthCodeMessageCodec) {
			AuthCodeMessage authCodeMessage = null;
			try {
				authCodeMessage = (AuthCodeMessage) messageCodec.decode(textMessage.getText());
				AuthCodeService authCodeService = SpringContextUtil.getBean("authCodeService");
				authCodeService.handleCodeMsg(authCodeMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(messageCodec instanceof ServerOutWareHouseMessageCodec) {
			ServerOutWareHouseMessage serverOutWareHouseMessage = null;
			try {
				serverOutWareHouseMessage = (ServerOutWareHouseMessage) messageCodec.decode(textMessage.getText());
				ServerOutWareHouseService serverOutWareHouseService = SpringContextUtil.getBean("serverOutWareHouseService");
				serverOutWareHouseService.handleCodeMsg(serverOutWareHouseMessage);
			} catch (JMSException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(messageCodec instanceof ServerInWareHouseMessageCodec){
			 ServerInWareHouseMessage serverInWareHouseMessage = null;
			 try {
				serverInWareHouseMessage =(ServerInWareHouseMessage) messageCodec.decode(textMessage.getText());
				ServerInWareHouseService serverInWareHouseService = SpringContextUtil.getBean("serverInWareHouseService");
				serverInWareHouseService.handleCodeMsg(serverInWareHouseMessage);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
