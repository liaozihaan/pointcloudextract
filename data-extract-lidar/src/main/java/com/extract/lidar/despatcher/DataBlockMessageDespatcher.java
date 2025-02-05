package com.extract.lidar.despatcher;

import com.extract.lidar.common.DataBlockMessage;
import com.extract.lidar.service.PointCloudDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DataBlockMessageDespatcher implements Runnable {

    @Autowired
    private PointCloudDataService pointCloudDataService;


    @Override
    public void run() {
        for (; ; ) {
            DataBlockMessage dataBlockMessage = pointCloudDataService.takeDataBlockMessage();
            pointCloudDataService.putDataBlockMessageToMap(dataBlockMessage);
        }
    }

    @PostConstruct
    public void start() {
        new Thread(this).start();
    }
}
