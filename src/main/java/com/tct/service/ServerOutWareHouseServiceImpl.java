package com.tct.service;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tct.cache.UnSendReplyMessageCache;
import com.tct.cache.UnhandlerReceiveMessageCache;
import com.tct.cache.UserOnlineQueueCache;
import com.tct.codec.pojo.ServerOutWareHouseMessage;
import com.tct.codec.pojo.ServerOutWareHouseReplyBody;
import com.tct.codec.pojo.ServerOutWareHouseReplyMessage;
import com.tct.dao.ServerOutWareHouseDao;
import com.tct.po.DeviceGunCustom;
import com.tct.po.DeviceGunQueryVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(value="serverOutWareHouseService")
public class ServerOutWareHouseServiceImpl implements ServerOutWareHouseService {
		
	@Autowired
	ServerOutWareHouseDao serverOutWareHouseDao;
	
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
		ServerOutWareHouseMessage message = (ServerOutWareHouseMessage)msg;
		
		ConcurrentHashMap<String, Hashtable<String, Object>> unhandlerReceiveMessageHashMap = UnhandlerReceiveMessageCache.getUnSendReplyMessageMap();
		ConcurrentHashMap<String, Hashtable<String, String>> userOnlineQueueHashMap = UserOnlineQueueCache.getOnlineUserQueueMap();
		ConcurrentHashMap<String, Hashtable<String, Object>> unSendReplyMessageHashMap = UnSendReplyMessageCache.getUnSendReplyMessageMap();
			
		DeviceGunQueryVo deviceGunQueryVo = new DeviceGunQueryVo();
		DeviceGunCustom deviceGunCustom = new DeviceGunCustom();
		deviceGunCustom.setGunMac(message.getServerOutWareHouseBody().getBluetoothMac());
		deviceGunQueryVo.setDeviceGunCustom(deviceGunCustom);
		DeviceGunCustom deviceGunCustom2 = serverOutWareHouseDao.selectByDeviceGunQueryVo(deviceGunQueryVo);
		
		String outWareJson = JSONObject.toJSONString(message);

		Hashtable<String, Object> tempUnSendReplyMessageMap = new Hashtable<String, Object>();
		if(unhandlerReceiveMessageHashMap.containsKey(deviceGunCustom2.getDeviceNo())) {
			tempUnSendReplyMessageMap = unhandlerReceiveMessageHashMap.get(deviceGunCustom2.getDeviceNo());
		}
		
		tempUnSendReplyMessageMap.put(message.getSerialNumber(), outWareJson);
		//将回应消息放入全局缓存
		unSendReplyMessageHashMap.put(deviceGunCustom2.getDeviceNo(), tempUnSendReplyMessageMap);
	}

}
