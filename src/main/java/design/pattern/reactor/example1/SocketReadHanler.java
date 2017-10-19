package design.pattern.reactor.example1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketReadHanler implements Runnable {
    private SocketChannel socketChannel;

    public SocketReadHanler(Selector selector, SocketChannel socketChannel) {
        try {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, 0);
            //将selectionKey绑定为本Handler下一步有事件触发时,将调用本类run方法
            //参看diapatch(SelectionKey seletionKey)
            selectionKey.attach(this);

            //同时将SeletionKey标记为可读,以便读取
            selectionKey.interestOps(SelectionKey.OP_READ);

            selector.wakeup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理读取数据
     */
    @Override
    public void run() {
        ByteBuffer in = ByteBuffer.allocate(1024);
        in.clear();
        try {
            this.socketChannel.read(in);
            //激活线程池,处理这些request
            //requestHandle(new Request(socket, btt));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
