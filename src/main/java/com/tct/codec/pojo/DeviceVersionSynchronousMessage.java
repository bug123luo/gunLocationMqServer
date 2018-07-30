package com.tct.codec.pojo;

public class DeviceVersionSynchronousMessage extends SimpleMessage {
	private DeviceVersionSynchronousBody deviceVersionSynchronousBody;

	public DeviceVersionSynchronousBody getDeviceVersionSynchronousBody() {
		return deviceVersionSynchronousBody;
	}

	public void setDeviceVersionSynchronousBody(DeviceVersionSynchronousBody deviceVersionSynchronousBody) {
		this.deviceVersionSynchronousBody = deviceVersionSynchronousBody;
	}
	
	
}
