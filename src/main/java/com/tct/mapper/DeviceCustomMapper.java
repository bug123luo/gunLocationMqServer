package com.tct.mapper;

import com.tct.po.DeviceQueryVo;

public interface DeviceCustomMapper {
    Integer selectByDeviceQueryVo(DeviceQueryVo deviceQueryVo) throws Exception;
}