package jdk.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * Java NIO Channel demo
 *
 * @author YuChuanQi
 * @since 2016年1月22日 上午10:46:59
 */
public class TestChannel {

    public static void main(String[] args) throws Exception {
        SocketChannel();
    }

    /**
     * channel数据写入缓冲区,在从缓冲区中读取数据
     */
    public static void read() throws Exception {
        RandomAccessFile file = new RandomAccessFile("D:\\jdk.nio-data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(5);

        int bt = channel.read(buf);

        while (bt != -1) {
            System.out.println("Read : " + bt);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }
            buf.compact();
            bt = channel.read(buf);
        }
        file.close();
    }

    /**
     * 不同通道之间的数据传输
     */
    @SuppressWarnings("resource")
    public static void transfer() throws Exception {
        RandomAccessFile file = new RandomAccessFile("D:\\jdk.nio-data.txt", "rw");
        FileChannel fchannel = file.getChannel();
        RandomAccessFile tofile = new RandomAccessFile("D:\\to-jdk.nio-data.txt", "rw");
        FileChannel tochannel = tofile.getChannel();

        long position = 0;
        long count = fchannel.size();
        fchannel.transferTo(position, count, tochannel);
    }

    /**
     * Selector : 仅用单个线程处理多个Channels
     * <pre>
     *  单线程使用一个Selector处理3个channel
     * </pre>
     */

    public static void selector() throws Exception {
        //创建一个Selector
        @SuppressWarnings("unused")
        Selector selector = Selector.open();
        //向Selector注册通道

    }

    /**
     * SocketChannel是一个连接到TCP网络套接字的通道
     */
    public static void SocketChannel() throws Exception {
        java.nio.channels.SocketChannel socketChannel = java.nio.channels.SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("192.168.188.34", 50070));
        ByteBuffer buf = ByteBuffer.allocate(48);
        int read = socketChannel.read(buf);
        while (read != -1) {
            System.out.println("Read : " + read);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get() + "");
            }
            buf.clear();
            read = socketChannel.read(buf);
        }
        socketChannel.close();
    }
}
