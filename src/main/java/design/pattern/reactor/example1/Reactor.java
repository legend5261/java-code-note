package design.pattern.reactor.example1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 反应器设计模式
 * <p>
 * <pre>
 * 用于解决多用户访问并发问题
 * </pre>
 * <p>
 * 例 : 餐厅服务问题
 * </p>
 * <p>
 * 传统线程池做法 : 来一个客人(request)去一个服务员(thread)
 * </p>
 * <p>
 * 反应器模式做法 : 当客人点菜的时候,服务员就可以去招呼其他客人了,当客人点好了菜,直接招呼一声'服务员'
 * </p>
 *
 * @author YuChuanQi
 * @since 2016年1月22日 下午2:59:03
 */
public class Reactor implements Runnable {
    public Selector selector;
    public ServerSocketChannel serverSocketChannel;

    public Reactor(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(), port);
            serverSocketChannel.socket().bind(address);
            // 非阻塞
            serverSocketChannel.configureBlocking(false);
            // 向selector注册该channel
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 利用SelectionKey的attach绑定Acceptor,如果有事情,触发Acceptor
            selectionKey.attach(new Acceptor(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();
                //Selector如果发现channel有OP_ACCEPT或READ事件发生,下列遍历就会进行
                while (iterator.hasNext()) {
                    //来一个事件,第一次触发一个acceptor线程
                    //以后触发SocketReadHandler
                    SelectionKey selectionKey = iterator.next();
                    dispatch(selectionKey);
                    selectedKeys.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null) {
            r.run();
        }
    }
}
