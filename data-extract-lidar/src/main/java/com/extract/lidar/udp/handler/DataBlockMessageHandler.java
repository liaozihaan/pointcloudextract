package com.extract.lidar.udp.handler;

import com.extract.lidar.common.DataBlockMessage;
import com.extract.lidar.service.PointCloudDataService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataBlockMessageHandler extends SimpleChannelInboundHandler<DataBlockMessage> {

    private PointCloudDataService pointCloudDataService;
    public DataBlockMessageHandler(PointCloudDataService pointCloudDataService) {
        this.pointCloudDataService = pointCloudDataService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataBlockMessage msg) throws Exception {
//        log.debug("DataBlockMessageHandler接收到数据:frameId:{}, packageSeqNum:{}", msg.getFrameId(), msg.getPackageSeqNum());
        pointCloudDataService.putDataBlockMessageToQueue(msg);
    }
}
