package com.extract.lidar.common;

import java.util.ArrayList;
import java.util.List;

public class DataBlockMessage extends BaseMessage {

    private List<DataBlockBO> dataBlockList;

    public DataBlockMessage() {
        super();
    }

    public DataBlockMessage(BaseMessage baseMessage, ArrayList<DataBlockBO> dataBlockBOList) {
        super(baseMessage.getCheckSum(), baseMessage.getFrameVersion(), baseMessage.getFrameId(),
                baseMessage.getPackageCounter(), baseMessage.getPackageSeqNum(), baseMessage.getPhaseLockNum(),
                baseMessage.getDataBlockNum(), baseMessage.getTimestamps(), baseMessage.getTimestampus(),
                baseMessage.getEchoMode(), baseMessage.getLuIndex(), baseMessage.getPackageFrameFlag(),
                baseMessage.getAzimuthIndexA(), baseMessage.getAzimuthIndexB(),
                baseMessage.getHighTemperatureDegradationFlag(), baseMessage.getDataInvalidFlag(),
                baseMessage.getRuntimeFrameRateFlag(), baseMessage.getReservedBit(), baseMessage.getReservedA(),
                baseMessage.getReservedB(), baseMessage.getDataBlockFlag(), baseMessage.getAzimuth(),
                baseMessage.getDataBlockBytes(), baseMessage.getTail());
        this.dataBlockList = dataBlockBOList;
    }


    public List<DataBlockBO> getDataBlockList() {
        return dataBlockList;
    }

    public void setDataBlockList(List<DataBlockBO> dataBlockList) {
        this.dataBlockList = dataBlockList;
    }

    @Override
    public String toString() {
        return "DataBlockMessage{" +
                "dataBlockList=" + dataBlockList +
                '}';
    }
}
