package com.extract.lidar.util;

public class NetworkAddressConvert {

    /**
     * ip地址字符串转换成4字节数组
     *
     * @param ipv4Address ip地址
     * @return 字节数组
     */
    public static byte[] ipV4AddressToBytes(String ipv4Address) {
        String[] split = ipv4Address.trim().split("\\.");
        byte[] bytes = new byte[4];
        int length = split.length;
        if (length == 4) {
            for (int i = 0; i < length; i++) {
                bytes[i] = (byte) Integer.valueOf(split[i]).intValue();
            }
        } else {
            throw new RuntimeException("ipv4Address contains four byte, but found " + length + "," + ipv4Address);
        }
        return bytes;
    }


}
