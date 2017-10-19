package design.pattern.reactor.example1;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {
    public Reactor reactor;

    public Acceptor(Reactor reactor) {
        this.reactor = reactor;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = reactor.serverSocketChannel.accept();
            //调用Handler来处理channel
            if (socketChannel != null) {
                new SocketReadHanler(reactor.selector, socketChannel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
