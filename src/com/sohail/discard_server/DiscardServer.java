/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.discard_server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 *
 * @author Sohail.Alam
 */
public class DiscardServer{
    public static void main(String[] args){
        ChannelFactory factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        ServerBootstrap bootstrap = new ServerBootstrap(factory);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new DiscardServerHandler());
            }
        });
        
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("ttl", 20);
        bootstrap.setOption("child.keepAlive", false);
        
        bootstrap.bind(new InetSocketAddress(1234));
        bootstrap.bind(new InetSocketAddress(4321));
    }
}
