/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sohail.echo_server;

import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

/**
 *
 * @author Sohail.Alam
 */
public class EchoServerHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        Channel ch = e.getChannel();
//        ch.write(e.getMessage());

        System.out.println("Message from: " + ch.getRemoteAddress());

        ChannelBuffer readBuffer = (ChannelBuffer) e.getMessage();

        while (readBuffer.readable()) {
            System.out.print((char) readBuffer.readByte());
            System.out.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();

        Channel ch = e.getChannel();
        ch.close();
    }
}
