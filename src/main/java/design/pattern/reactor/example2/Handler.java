package design.pattern.reactor.example2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    private final static int DEFAULT_SIZE = 8092;

    private final static int READING = 0;

    private final static int SENDING = 1;

    private final SocketChannel socketChannel;

    private final SelectionKey selectionKey;
    public ByteBuffer inputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);
    public ByteBuffer outputBuffer = ByteBuffer.allocate(DEFAULT_SIZE);
    private int state = READING;

    public Handler(Selector selector, SocketChannel channel) throws IOException {
        this.socketChannel = channel;
        socketChannel.configureBlocking(false);
        this.selectionKey = socketChannel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);

        selector.wakeup();
    }

    @Override
    public void run() {
        if (state == READING) {
            read();
        } else if (state == SENDING) {
            write();
        }
    }

    private void read() {
        try {
            socketChannel.read(inputBuffer);
            if (inputIsComplete()) {
                process();
                selectionKey.attach(new Sender());
                selectionKey.interestOps(SelectionKey.OP_WRITE);
                selectionKey.selector().wakeup();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write() {
        try {
            socketChannel.write(outputBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (outIsComplete()) {
            selectionKey.cancel();
        }
    }

    public boolean inputIsComplete() {
        return false;
    }

    public boolean outIsComplete() {
        return false;
    }

    public void process() {
        //TODO
    }

    class Sender implements Runnable {
        @Override
        public void run() {
            try {
                socketChannel.write(outputBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (outIsComplete()) {
                selectionKey.cancel();
            }
        }
    }
}
