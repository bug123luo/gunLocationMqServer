package com.tct.codec;

import com.alibaba.fastjson.JSONObject;
import com.tct.codec.pojo.ServerInWareHouseBody;
import com.tct.codec.pojo.ServerInWareHouseMessage;

public class ServerInWareHouseMessageCodec implements MessageCodec {

	@Override
	public Object decode(String inMsg) throws Exception {
		JSONObject json= JSONObject.parseObject(inMsg);
		
		ServerInWareHouseMessage serverInWareHouseMessage = new ServerInWareHouseMessage();
		serverInWareHouseMessage.setServerInWareHouseBody(json.getObject("messageBody",ServerInWareHouseBody.class));
		serverInWareHouseMessage.setServiceType(json.getString("serviceType"));
		serverInWareHouseMessage.setFormatVersion(json.getString("formatVersion"));
		serverInWareHouseMessage.setDeviceType(json.getInteger("deviceType"));
		serverInWareHouseMessage.setSerialNumber(json.getString("serialNumber"));
		serverInWareHouseMessage.setMessageType(json.getString("messageType"));
		serverInWareHouseMessage.setSendTime(json.getString("sendTime"));
		
		return serverInWareHouseMessage;
	}

	@Override
	public String encode(Object outMsg) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
