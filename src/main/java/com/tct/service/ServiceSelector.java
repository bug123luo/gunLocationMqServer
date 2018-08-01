package com.tct.service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.tct.codec.AuthCodeMessageCodec;
import com.tct.codec.MessageCodec;
import com.tct.codec.ServerInWareHouseMessageCodec;
import com.tct.codec.ServerInWareHouseMessageReplyCodec;
import com.tct.codec.ServerOutWareHouseMessageCodec;
import com.tct.codec.ServerOutWareHouseReplyMessageCodec;
import com.tct.codec.pojo.AuthCodeMessage;
import com.tct.codec.pojo.ServerInWareHouseMessage;
import com.tct.codec.pojo.ServerInWareHouseReplyMessage;
import com.tct.codec.pojo.ServerOutWareHouseMessage;
import com.tct.codec.pojo.ServerOutWareHouseReplyMessage;


public class ServiceSelector {
	
	public boolean handlerService(MessageCodec messageCodec,TextMessage textMessage) {
		
		boolean flag = false;
		
		if(messageCodec instanceof AuthCodeMessageCodec) {
			AuthCodeMessage authCodeMessage = null;
			try {
				authCodeMessage = (AuthCodeMessage) messageCodec.decode(textMessage.getText());
				AuthCodeService authCodeService = SpringContextUtil.getBean("authCodeService");
				authCodeService.handleCodeMsg(authCodeMessage);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}else if(messageCodec instanceof ServerOutWareHouseMessageCodec) {
			ServerOutWareHouseMessage serverOutWareHouseMessage = null;
			try {
				serverOutWareHouseMessage = (ServerOutWareHouseMessage) messageCodec.decode(textMessage.getText());
				ServerOutWareHouseService serverOutWareHouseService = SpringContextUtil.getBean("serverOutWareHouseService");
				serverOutWareHouseService.handleCodeMsg(serverOutWareHouseMessage);
				flag = true;
			} catch (JMSException e) {
				e.printStackTrace();
				flag = false;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}else if(messageCodec instanceof ServerOutWareHouseReplyMessageCodec) {
			ServerOutWareHouseReplyMessage serverOutWareHouseReplyMessage = null;
			try {
				serverOutWareHouseReplyMessage = (ServerOutWareHouseReplyMessage) messageCodec.decode(textMessage.getText());
				ServerOutWareHouseService serverOutWareHouseReplyService = SpringContextUtil.getBean("serverOutWareHouseReplyService");
				serverOutWareHouseReplyService.handleCodeMsg(serverOutWareHouseReplyMessage);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}else if(messageCodec instanceof ServerInWareHouseMessageCodec){
			ServerInWareHouseMessage serverInWareHouseMessage = null;
			try {
				serverInWareHouseMessage =(ServerInWareHouseMessage) messageCodec.decode(textMessage.getText());
				ServerInWareHouseService serverInWareHouseService = SpringContextUtil.getBean("serverInWareHouseService");
				serverInWareHouseService.handleCodeMsg(serverInWareHouseMessage);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}else if(messageCodec instanceof ServerInWareHouseMessageReplyCodec) {
			ServerInWareHouseReplyMessage serverInWareHouseReplyMessage = null;
			try {
				serverInWareHouseReplyMessage = (ServerInWareHouseReplyMessage) messageCodec.decode(textMessage.getText());
				ServerInWareHouseService serverInWareHouseReplyService =  SpringContextUtil.getBean("serverInWareHouseReplyService");
				serverInWareHouseReplyService.handleCodeMsg(serverInWareHouseReplyMessage);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
}
