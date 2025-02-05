package com.extract.lidar.udp;

import com.extract.lidar.udp.handler.PointCloudDataChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

@Slf4j
@Component
public class PointCloudDataMulticastServer {

    private static final String SERVERNAME = "pointCloudDataMulticastServer";

    @Autowired
    @Qualifier("pointCloudDataMulticastInetSocketAddress")
    private InetSocketAddress multicastAddress;

    @Autowired
    @Qualifier("pointCloudDataMulticastNetworkInterface")
    private NetworkInterface networkInterface;

    @Autowired
    @Qualifier("pointCloudDataMulticastServerBootstrap")
    private Bootstrap bootstrap;

    @Autowired
    private PointCloudDataChannelInitializer pointCloudDataChannelInitializer;

    @Bean
    public void start() throws InterruptedException {
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        InetAddress localAddress = null;
        while (addresses.hasMoreElements()) {
            InetAddress address = addresses.nextElement();
            if (address instanceof Inet4Address) {
                localAddress = address;
            }
        }

        NioDatagramChannel nioDatagramChannel = (NioDatagramChannel) bootstrap.option(ChannelOption.IP_MULTICAST_IF, networkInterface)
                .localAddress(localAddress, multicastAddress.getPort())
                .handler(pointCloudDataChannelInitializer)
                .bind(multicastAddress).sync().channel();

        nioDatagramChannel.joinGroup(multicastAddress, networkInterface).sync();
        nioDatagramChannel.closeFuture().await();
    }


}
