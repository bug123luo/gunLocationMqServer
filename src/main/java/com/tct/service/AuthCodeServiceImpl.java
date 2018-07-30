package com.tct.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tct.util.RandomNumber;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import com.tct.cache.UnSendReplyMessageCache;
import com.tct.cache.UnhandlerReceiveMessageCache;
import com.tct.cache.UserOnlineQueueCache;
import com.tct.codec.pojo.AuthCodeMessage;
import com.tct.codec.pojo.AuthCodeReplyBody;
import com.tct.codec.pojo.AuthCodeReplyMessage;
import com.tct.dao.AuthCodeDao;

@Slf4j
@Service(value="authCodeService")
public class AuthCodeServiceImpl implements AuthCodeService{
	
	@Autowired
	AuthCodeDao authcodeDao;
	
	@Override
	public void handleCodeMsg(Object msg) throws Exception {
			
		AuthCodeMessage message=(AuthCodeMessage)msg;
		//缓存消息
		//AuthCodeMessage 中的username目前是警员编号
		ConcurrentHashMap<String, Hashtable<String, Object>> unhandlerReceiveMessageHashMap = UnhandlerReceiveMessageCache.getUnSendReplyMessageMap();
		ConcurrentHashMap<String, Hashtable<String, String>> userOnlineQueueHashMap = UserOnlineQueueCache.getOnlineUserQueueMap();
		ConcurrentHashMap<String, Hashtable<String, Object>> unSendReplyMessageHashMap = UnSendReplyMessageCache.getUnSendReplyMessageMap();
		
		Hashtable<String , String> userQueueMap=new Hashtable<String,String>();
		if (userOnlineQueueHashMap.containsKey(message.getMessageBody().getUsername())) {
			userQueueMap=userOnlineQueueHashMap.get(message.getMessageBody().getUsername());
		}
		//userQueueMap.put("receQueue", "queue."+message.getMessageBody().getUsername());
		userQueueMap.put("sendQueue", message.getMessageBody().getUsername());
		userOnlineQueueHashMap.put(message.getMessageBody().getUsername(), userQueueMap);	
		
		Hashtable<String, Object> messageMap=new Hashtable<String,Object>();
		if (unhandlerReceiveMessageHashMap.containsKey(message.getMessageBody().getUsername())) {
			messageMap= unhandlerReceiveMessageHashMap.get(message.getMessageBody().getUsername());
		}
		messageMap.put(message.getSerialNumber(), message);	
		unhandlerReceiveMessageHashMap.put(message.getMessageBody().getUsername(), messageMap);

		Boolean tempboolean = authcodeDao.findDeviceUserAndUpdateLocation(message);
		if(tempboolean) {
			//构造回应消息
			AuthCodeReplyMessage authCodeReplyMessage =  new AuthCodeReplyMessage();
			AuthCodeReplyBody authCodeReplyBody = new AuthCodeReplyBody();
			
			authCodeReplyBody.setReserve("1");
			authCodeReplyBody.setHeartbeat("2");
			authCodeReplyBody.setLo(message.getMessageBody().getLo());
			authCodeReplyBody.setLa(message.getMessageBody().getLa());
			authCodeReplyBody.setAuthCode(RandomNumber.getRandomNumber());
			authCodeReplyBody.setDia("2");
			
			authCodeReplyMessage.setDeviceType(message.getDeviceType());
			authCodeReplyMessage.setFormatVersion(message.getFormatVersion());
			authCodeReplyMessage.setMessageType(message.getMessageType());
			authCodeReplyMessage.setSendTime(message.getSendTime());
			authCodeReplyMessage.setSerialNumber(message.getSerialNumber());;
			authCodeReplyMessage.setServiceType(message.getServiceType());
			authCodeReplyMessage.setAuthCodeReplyBody(authCodeReplyBody);
			
			
			String authJson = JSONObject.toJSONString(authCodeReplyMessage);
			//将回应消息放进消息缓存队列中
			Hashtable<String, Object> tempUnSendReplyMessageMap = new Hashtable<String, Object>();
			if(unhandlerReceiveMessageHashMap.containsKey(message.getMessageBody().getUsername())) {
				tempUnSendReplyMessageMap = unhandlerReceiveMessageHashMap.get(message.getMessageBody().getUsername());
			}
			
			tempUnSendReplyMessageMap.put(authCodeReplyMessage.getSerialNumber(), authJson);
			//将回应消息放入全局缓存
			unSendReplyMessageHashMap.put(message.getMessageBody().getUsername(), tempUnSendReplyMessageMap);
			
		}else {
			log.debug("数据库更新数据失败");
		}
		
	}

}
