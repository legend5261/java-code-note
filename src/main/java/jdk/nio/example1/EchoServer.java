package jdk.nio.example1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class EchoServer {
    private SocketAddress add;
    private ServerSocketChannel acceptChannel;
    private boolean running = true;

    private Selector selector;

    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.run();
    }

    public Selector initSelector() throws IOException {
        add = new InetSocketAddress(InetAddress.getLocalHost(), 7120);
        //System.out.println("server start on " + add);
        //创建一个ServerSocketChannel,并将之设置为非阻塞模式
        acceptChannel = ServerSocketChannel.open();
        acceptChannel.configureBlocking(false);
        //将Server Socket绑定到add.
        acceptChannel.socket().bind(add);
        //向selector注册channel,注册事件为Accept
        Selector selector = Selector.open();
        acceptChannel.register(selector, SelectionKey.OP_ACCEPT);
        return selector;
    }

    public void run() {
        try {
            selector = initSelector();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (running) {
            SelectionKey key = null;
            try {
                selector.select();
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    key = iter.next();
                    iter.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        doAccept(key);
                    } else if (key.isWritable()) {
                        send(key);
                    } else if (key.isReadable()) {
                        receive(key);
                    }
                    key = null;
                }
            } catch (Exception e) {

            }
        }
    }

    private void send(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer writeBuffer = (ByteBuffer) key.attachment();
        String sendMessage = "send message from server";
        writeBuffer.wrap(sendMessage.getBytes());
        socketChannel.write(writeBuffer);
        //写完成后,切换至Read
        if (writeBuffer.remaining() == 0) {
            writeBuffer.clear();
            key.interestOps(SelectionKey.OP_READ);
        }
    }

    private void receive(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //ByteBuffer readBuffer = (ByteBuffer) key.attachment();
        ByteBuffer readBuffer = ByteBuffer.allocate(8192);
        int read = socketChannel.read(readBuffer);
        if (read > 0) {
            //read data
            System.out.println(new String(readBuffer.array()));
            readBuffer.clear();
            //readBuffer.flip();
            //key.interestOps(SelectionKey.OP_WRITE);//切换至write
        }

    }

    private void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        //接受连接请求,并将之设置为阻塞模式
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        //将新的channel注册到selector中,一旦有需要读或写的数据,就会通知相应的程序
        SelectionKey clientKey = socketChannel.register(selector, SelectionKey.OP_READ);
        ByteBuffer buffer = ByteBuffer.allocate(8192);//8k
        clientKey.attach(buffer);
    }
}

