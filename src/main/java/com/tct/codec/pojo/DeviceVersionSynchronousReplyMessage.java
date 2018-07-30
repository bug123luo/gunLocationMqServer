package com.tct.codec.pojo;

public class DeviceVersionSynchronousReplyMessage extends SimpleMessage{
	private DeviceVersionSynchronousReplyBody deviceVersionSynchronousBody;

	public DeviceVersionSynchronousReplyBody getDeviceVersionSynchronousBody() {
		return deviceVersionSynchronousBody;
	}

	public void setDeviceVersionSynchronousBody(DeviceVersionSynchronousReplyBody deviceVersionSynchronousBody) {
		this.deviceVersionSynchronousBody = deviceVersionSynchronousBody;
	}
	
	
}
