package jdk.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        System.out.println("Selector open " + selector.isOpen());

        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        SocketAddress add = new InetSocketAddress("localhost", 5454);
        serverSocket.bind(add);
        //serverSocket.socket().bind(add);

        serverSocket.configureBlocking(false);
        int ops = serverSocket.validOps();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            System.out.println("Waiting for select...");
            int num = selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
                SelectionKey selectionKey = it.next();
                it.remove();
                if (selectionKey.isAcceptable()) {
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("Accepted new connection from " + client);
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);

                    String str = new String(buffer.array()).trim();
                    System.out.println("Message read from client : " + str);
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                    if (str.equals("Bye.")) {
                        client.close();
                        System.out.println("Client message are complete");
                    }

                } else if (selectionKey.isWritable()) {
                    System.out.println("write");
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    String m = "send from server";
                    client.write(ByteBuffer.wrap(m.getBytes()));
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}
