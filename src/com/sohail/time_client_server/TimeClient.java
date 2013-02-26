/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.time_client_server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 *
 * @author sohail.alam
 */
public class TimeClient {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 1234;
        
        ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        
        ClientBootstrap clientBootstrap = new ClientBootstrap(factory);
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new TimeClientHandler());
            }
        });
        
        clientBootstrap.setOption("tcpNoDelay", true);
        clientBootstrap.setOption("keepAlive", true);
        
        clientBootstrap.connect(new InetSocketAddress(host, port));
    }
}
 