package com.extract.lidar.udp.handler.codec;

import com.extract.lidar.common.BaseMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CommonDatagramPacketDecoder extends MessageToMessageDecoder<DatagramPacket> {


    private final int BUF_BASE_LENGTH = 60;

    private final int TAIL_LENGTH = 4;

    private final int SINGLE_DATA_BLOCK_LENGTH = 5;

    @Override
    protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
        ByteBuf buf = msg.content().copy();
        int bufLength = buf.readableBytes();

        if (bufLength >= BUF_BASE_LENGTH) {
            //校验和
            byte[] checkSumBytes = new byte[4];
            buf.readBytes(checkSumBytes);

            //frameVersion
            Integer frameVersion = (int) buf.readUnsignedByte();
            //frameId
            Integer frameId = buf.readUnsignedShort();
            //packageCount
            Integer packageCounter = buf.readUnsignedShort();
            //packageSeqNum
            Integer packageSeqNum = buf.readUnsignedShort();
            //phaseLockNum
            Integer phaseLockNum = buf.readUnsignedShort();
            //dataBlockNum
            Integer dataBlockNum = (int) buf.readUnsignedByte();

            //timestamp-s
            Long timestamps = buf.readUnsignedInt();
            //timestampus
            Long timestampus = buf.readUnsignedInt();
            //echoMode
            Integer echoMode = (int) buf.readByte();
            //luIndex
            Integer luIndex = (int) buf.readByte();
            //packageFrameFlag
            Integer packageFrameFlag = (int) buf.readByte();
            //azimuthIndex0
            Integer azimuthIndexA = (int) buf.readShort();
            //azimuthIndex1
            Integer azimuthIndexB = (int) buf.readShort();

            //TODO 解析bit
            buf.readByte();

            Integer reservedA = (int) buf.readByte();
            byte[] reservedB = new byte[22];
            buf.readBytes(reservedB);

            byte[] flagBytes = new byte[2];
            buf.readBytes(flagBytes);
            int azimuth = buf.readUnsignedShort();

            bufLength = buf.readableBytes();
            if (bufLength >= SINGLE_DATA_BLOCK_LENGTH + TAIL_LENGTH) {
                byte[] dataBlockBytes = new byte[bufLength - TAIL_LENGTH];
                buf.readBytes(dataBlockBytes);
                byte[] tailBytes = new byte[4];
                buf.readBytes(tailBytes);
                BaseMessage baseMessage = new BaseMessage(checkSumBytes, frameVersion, frameId, packageCounter, packageSeqNum, phaseLockNum,
                        dataBlockNum, timestamps, timestampus, echoMode, luIndex, packageFrameFlag, azimuthIndexA,
                        azimuthIndexB, 0, 0, 0, 0,
                        reservedA, reservedB, bytesToHex(flagBytes), (double) azimuth / 256, dataBlockBytes, tailBytes);
                out.add(baseMessage);
//                log.debug("frameId:{},packageSeqNum:{}, luIndex:{}, azimuthIndexA:{}, azimuthIndexB:{}, packageFrameFlag：{}", frameId, packageSeqNum,luIndex, azimuthIndexA, azimuthIndexB, packageFrameFlag);
            } else {
                log.debug("buflength不足");
            }
        } else {
            log.warn("buf长度不足{},当前长度:{}", BUF_BASE_LENGTH, bufLength);
        }
        buf.release();

    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String s = byteToHex(bytes[i]);
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private static String byteToHex(byte b) {
        String s = Integer.toHexString(b & 0xff);
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}
