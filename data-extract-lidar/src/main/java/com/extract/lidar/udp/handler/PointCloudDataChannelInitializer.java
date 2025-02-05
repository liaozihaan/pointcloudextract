package com.extract.lidar.udp.handler;

import com.extract.lidar.service.PointCloudDataService;
import com.extract.lidar.udp.handler.codec.CommonDatagramPacketDecoder;
import com.extract.lidar.udp.handler.codec.DataBlockMessageDecoder;
import com.extract.lidar.udp.handler.factory.DataBlockMessageHandlerFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointCloudDataChannelInitializer extends ChannelInitializer<NioDatagramChannel> {

    @Autowired
    private DataBlockMessageHandlerFactory dataBlockMessageHandlerFactory;

    @Autowired
    private PointCloudDataService pointCloudDataService;

    @Override
    protected void initChannel(NioDatagramChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new LoggingHandler(LogLevel.DEBUG));
        pipeline.addLast(new CommonDatagramPacketDecoder());
        pipeline.addLast(new DataBlockMessageDecoder());
        pipeline.addLast(dataBlockMessageHandlerFactory.getObject());
    }
}
