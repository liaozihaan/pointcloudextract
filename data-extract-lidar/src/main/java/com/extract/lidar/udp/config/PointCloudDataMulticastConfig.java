package com.extract.lidar.udp.config;

import com.extract.lidar.util.NetworkAddressConvert;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.net.*;

@SpringBootConfiguration
@ConfigurationProperties(prefix = "test")
public class PointCloudDataMulticastConfig {

    /**
     * 本机ip
     */
    private String hostname;

    /**
     * 组播地址
     */
    private String multicastHostname;

    /**
     * 组播端口
     */
    private Integer multicastPort;

    @Bean(name = "pointCloudDataMulticastServerBootstrap")
    public Bootstrap bootstrap() {
        Bootstrap bootstrap = new Bootstrap();
        return bootstrap.group(new NioEventLoopGroup())
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true);
    }

    @Bean(name = "pointCloudDataMulticastInetSocketAddress")
    public InetSocketAddress multicastInetSocketAddress() {
        return new InetSocketAddress(multicastHostname, multicastPort);
    }

    @Bean(name = "pointCloudDataMulticastNetworkInterface")
    public NetworkInterface multicastNetworkInterface() throws UnknownHostException, SocketException {
        if (hostname != null && !hostname.trim().isEmpty()) {
            return NetworkInterface.getByInetAddress(InetAddress.getByAddress(NetworkAddressConvert.ipV4AddressToBytes(hostname)));
        }
        throw new UnknownHostException();
    }


    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setMulticastHostname(String multicastHostname) {
        this.multicastHostname = multicastHostname;
    }

    public void setMulticastPort(Integer multicastPort) {
        this.multicastPort = multicastPort;
    }
}
