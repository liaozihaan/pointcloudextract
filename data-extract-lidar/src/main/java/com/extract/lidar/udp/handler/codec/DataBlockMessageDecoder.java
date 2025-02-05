package com.extract.lidar.udp.handler.codec;

import com.extract.lidar.common.BaseMessage;
import com.extract.lidar.common.DataBlockBO;
import com.extract.lidar.common.DataBlockMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DataBlockMessageDecoder extends MessageToMessageDecoder<BaseMessage> {


    private final int SINGLE_DATA_BLOCK_LENGTH = 5;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, BaseMessage baseMessage, List<Object> out) throws Exception {
        byte[] dataBlockBytes = baseMessage.getDataBlockBytes();
        if (dataBlockBytes.length >= SINGLE_DATA_BLOCK_LENGTH) {
            ArrayList<DataBlockBO> dataBlockBOList = new ArrayList<>();
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes(dataBlockBytes);
            while (buf.readableBytes() >= SINGLE_DATA_BLOCK_LENGTH) {
                DataBlockBO dataBlockBO = decodeDataBlock(buf);
                dataBlockBOList.add(dataBlockBO);
            }
            DataBlockMessage dataBlockMessage = new DataBlockMessage(baseMessage, dataBlockBOList);
            out.add(dataBlockMessage);
//            log.debug("解析出dataBlockList：{}", dataBlockMessage);
        }
//        log.debug("接收到数据:{}", baseMessage);
    }

    private DataBlockBO decodeDataBlock(ByteBuf buf) {
        int distance = buf.readUnsignedShort();
        int intensity = buf.readUnsignedShort();
        int reflectivity = buf.readUnsignedByte();
        return new DataBlockBO((double) distance / 256, intensity, reflectivity);
    }


}
