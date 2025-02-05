package com.extract.lidar.service;

import com.extract.lidar.common.DataBlockFrameDelay;
import com.extract.lidar.common.DataBlockMessage;

import java.util.ArrayList;

public interface PointCloudDataService {

    void putDataBlockMessageToQueue(DataBlockMessage dataBlockMessage);

    DataBlockMessage takeDataBlockMessage();

    DataBlockFrameDelay takeDataBlockFrameDelay();

    void putDataBlockMessageToMap(DataBlockMessage dataBlockMessage);

    ArrayList<DataBlockMessage> completePointCloudData(Integer frameId);

    void createAndFlushPointCloudDataFile(ArrayList<DataBlockMessage> dataBlockMessageList);

}
