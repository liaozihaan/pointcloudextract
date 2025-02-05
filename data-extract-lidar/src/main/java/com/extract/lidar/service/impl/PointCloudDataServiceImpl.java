package com.extract.lidar.service.impl;

import com.extract.lidar.common.DataBlockBO;
import com.extract.lidar.common.DataBlockFrameDelay;
import com.extract.lidar.common.DataBlockMessage;
import com.extract.lidar.pcd.PointCloudDataBO;
import com.extract.lidar.pcd.PointCloudDataFileWriter;
import com.extract.lidar.service.PointCloudDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
public class PointCloudDataServiceImpl implements PointCloudDataService {

    private static final LinkedBlockingQueue<DataBlockMessage> dataBlockMessageQueue = new LinkedBlockingQueue<>();

    private static final ConcurrentHashMap<Integer, ArrayList<DataBlockMessage>> dataBlockMessageMap = new ConcurrentHashMap<>();

    private static final DelayQueue<DataBlockFrameDelay> frameIdDelayQueue = new DelayQueue<>();

    @Autowired
    private PointCloudDataFileWriter pointCloudDataFileWriter;

    @Override
    public void putDataBlockMessageToQueue(DataBlockMessage dataBlockMessage) {
        try {
            dataBlockMessageQueue.put(dataBlockMessage);
        } catch (InterruptedException e) {
            log.error("放入queue数据:{} 失败:{}", dataBlockMessage, e.getLocalizedMessage());
        }
    }

    @Override
    public DataBlockMessage takeDataBlockMessage() {
        try {
            return dataBlockMessageQueue.take();
        } catch (InterruptedException e) {
            log.error("take dataBlockMessageQueue 异常:{}", e.getLocalizedMessage());
        }
        return null;
    }


    @Override
    public DataBlockFrameDelay takeDataBlockFrameDelay() {
        try {
            return frameIdDelayQueue.take();
        } catch (InterruptedException e) {
            log.error("take frameIdDelayQueue 异常:{}", e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    public void putDataBlockMessageToMap(DataBlockMessage dataBlockMessage) {
        Integer frameId = dataBlockMessage.getFrameId();
        boolean containsKey = dataBlockMessageMap.containsKey(frameId);

        if (!containsKey) {
            //新的帧 添加key value
            dataBlockMessageMap.put(frameId, new ArrayList<>());
        }

        List<DataBlockMessage> dataBlockMessageList = dataBlockMessageMap.get(frameId);
        //新增元素
        dataBlockMessageList.add(dataBlockMessage);

        //是否是尾帧
        Integer packageFrameFlag = dataBlockMessage.getPackageFrameFlag();
        if (packageFrameFlag == 2) {
            DataBlockFrameDelay dataBlockFrameDelay = new DataBlockFrameDelay(frameId);
            log.info("收到frame尾帧,向queue中放入delay object:{}", dataBlockFrameDelay);
            frameIdDelayQueue.put(dataBlockFrameDelay);
        }
    }

    @Override
    public ArrayList<DataBlockMessage> completePointCloudData(Integer frameId) {
        return dataBlockMessageMap.get(frameId);
    }

    @Override
    public void createAndFlushPointCloudDataFile(ArrayList<DataBlockMessage> dataBlockMessageList) {

        int countDataBlock = 0;
        Integer frameId = null;

        ArrayList<ArrayList<String>> dataList = new ArrayList<>();

        for (DataBlockMessage dataBlockMessage : dataBlockMessageList) {

            List<DataBlockBO> dataBlockList = dataBlockMessage.getDataBlockList();

            for (DataBlockBO dataBlockBO : dataBlockList) {
                ArrayList<String> dataBlockMessagePropertyStringList = new ArrayList<>();
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getFrameId()));
                frameId = dataBlockMessage.getFrameId();
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getTimestamps()));
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getTimestampus()));
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getLuIndex()));
                dataBlockMessagePropertyStringList.add(dataBlockMessage.getDataBlockFlag());
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getAzimuth()));

                //======dataBlock===
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockBO.getDistance()));
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockBO.getIntensity()));
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockBO.getReflectivity()));
                //==================

                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getAzimuthIndexA()));
                dataBlockMessagePropertyStringList.add(String.valueOf(dataBlockMessage.getAzimuthIndexB()));

                dataList.add(dataBlockMessagePropertyStringList);
                countDataBlock += 1;
            }


        }

        PointCloudDataBO pointCloudDataBO = new PointCloudDataBO(countDataBlock, dataList);

        String content = pointCloudDataBO.headerContent().append(pointCloudDataBO.dataContent()).toString();
        if(frameId != null){
            pointCloudDataFileWriter.createAndFlush(String.valueOf(frameId) + ".pcd", content);
        }
    }
}
