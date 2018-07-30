package com.tct.dao;

import com.tct.po.ServerMessageSerialnumberCustom;

public interface ServerMessageSerialNumberDao {
	public ServerMessageSerialnumberCustom selectMaxIdAndSerialNumber() throws Exception;
}
