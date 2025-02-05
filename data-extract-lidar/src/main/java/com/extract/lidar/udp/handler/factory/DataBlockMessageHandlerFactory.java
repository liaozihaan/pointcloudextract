package com.extract.lidar.udp.handler.factory;

import com.extract.lidar.service.PointCloudDataService;
import com.extract.lidar.udp.handler.DataBlockMessageHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBlockMessageHandlerFactory implements FactoryBean<DataBlockMessageHandler> {

    @Autowired
    private PointCloudDataService pointCloudDataService;


    @Override
    public DataBlockMessageHandler getObject() throws Exception {
        return new DataBlockMessageHandler(pointCloudDataService);
    }

    @Override
    public Class<?> getObjectType() {
        return DataBlockMessageHandler.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
