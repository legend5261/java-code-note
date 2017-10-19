package jdk.nio;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public static void main(String[] args) throws Exception {
        SocketAddress add = new InetSocketAddress("localhost", 5454);
        SocketChannel client = SocketChannel.open(add);

        System.out.println("client send message...");

        String[] messages = new String[]{"time goes fast", "what now ?", "Bye."};

        for (int i = 0; i < messages.length; i++) {
            byte[] message = new String(messages[i]).getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);
            System.out.println(messages[i]);
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            client.read(allocate);
            System.out.println(new String(allocate.array()).trim());
            buffer.clear();
            Thread.sleep(1000);
        }
        /*ByteBuffer buffer = ByteBuffer.allocate(1024);
		client.read(buffer);
		System.out.println(new String(buffer.array()));*/
        client.close();
    }
}
