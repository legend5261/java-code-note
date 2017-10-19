package jdk.nio.time;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        MulitiplexerTimeServer timeServer = new MulitiplexerTimeServer(port);

        new Thread(timeServer, "NIO-TimeServer-001").start();
    }
}
