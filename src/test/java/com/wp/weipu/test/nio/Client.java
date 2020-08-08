package com.wp.weipu.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) {
        SocketChannel channel = null;
        try {
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress("127.0.0.1", 8800));
            ByteBuffer writeBuf = ByteBuffer.allocate(1024);
            ByteBuffer readBuf = ByteBuffer.allocate(1024);

            writeBuf.put("hello server".getBytes());
            writeBuf.flip();
            while (true){
                writeBuf.rewind();
                channel.write(writeBuf);
                readBuf.clear();
                channel.read(readBuf);
                System.out.println("client receive    ----"+new String(readBuf.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
