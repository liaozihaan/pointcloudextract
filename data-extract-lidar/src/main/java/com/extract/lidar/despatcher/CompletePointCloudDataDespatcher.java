package com.extract.lidar.despatcher;


import com.extract.lidar.common.DataBlockFrameDelay;
import com.extract.lidar.common.DataBlockMessage;
import com.extract.lidar.service.PointCloudDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Slf4j
@Component
public class CompletePointCloudDataDespatcher implements Runnable {

    @Autowired
    private PointCloudDataService pointCloudDataService;

    @Override
    public void run() {
        for (; ; ) {
            DataBlockFrameDelay dataBlockFrameDelay = pointCloudDataService.takeDataBlockFrameDelay();
            if(dataBlockFrameDelay != null){
                ArrayList<DataBlockMessage> dataBlockMessages = pointCloudDataService.completePointCloudData(dataBlockFrameDelay.getFramdId());
                log.debug("完整数据:{}", dataBlockMessages.size());
                pointCloudDataService.createAndFlushPointCloudDataFile(dataBlockMessages);
            }
        }
    }

    @PostConstruct
    public void start() {
        new Thread(this).start();
    }
}
