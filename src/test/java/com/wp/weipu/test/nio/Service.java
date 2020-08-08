package com.wp.weipu.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Service {

    public static void main(String[] args) {
        Selector selector = null;
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8800));
            //设置为非阻塞的
            ssc.configureBlocking(false);

            selector = Selector.open();
            //这有点像注册通道,指定感兴趣的事件
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            //分配内存
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            //先给客户端，发送消息
            writeBuffer.put("hello client".getBytes());
            //刷新的感觉
            writeBuffer.flip();

            while (true) {
                //阻塞等待,poll 和epoll呢
                int readyNum = selector.select();
                if (readyNum == 0) {
                    continue;
                }
                //获取就绪的keys
                Set<SelectionKey> keys = selector.selectedKeys();
                //遍历描述符
                Iterator<SelectionKey> it = keys.iterator();

                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    //整个三步是一块实现执行的，先要简历连接，然后设置读再设置写
                    if (key.isAcceptable()) {
                        //创建新的连接，并且把新的连接注册到selector上，且只对读操作感兴趣
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        readBuffer.clear();
                        socketChannel.read(readBuffer);
                        System.out.println("server receive  ---" + new String(readBuffer.array()));
                        //读完之后只对写感兴趣，因为要给client发送数据
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        writeBuffer.rewind();
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.write(writeBuffer);
                        //发送完成后又指对读感兴趣
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    //处理完成时间以后从就绪keys中移除，都remove了还set干啥。。。。
                    it.remove();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (selector != null) {
                try {
                    selector.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
