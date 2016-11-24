package test37.socketserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class NioEchoServer {

    static Map<Socket, Long> time_start = new HashMap<>(10240);

    static class EchoClient {
        private LinkedList<ByteBuffer> outq;

        EchoClient() {
            this.outq = new LinkedList<ByteBuffer>();
        }

        public LinkedList<ByteBuffer> getOutputQueue() {
            return outq;
        }

        public void enqueue(ByteBuffer bb) {
            outq.addFirst(bb);
        }
    }

    static class HandleMsg implements Runnable {

        SelectionKey sk;
        ByteBuffer bb;

        HandleMsg(ByteBuffer bb, SelectionKey sk) {
            this.bb = bb;
            this.sk = sk;
        }

        @Override
        public void run() {
            EchoClient client = (EchoClient)sk.attachment();
            client.enqueue(bb);

            sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            selector.wakeup();
        }
    }

    public static void main(String[] args) throws Exception {
        startServer();
    }

    private static AbstractSelector selector;

    private static void startServer() throws Exception {
        selector = SelectorProvider.provider().openSelector();
        ServerSocketChannel scc = ServerSocketChannel.open();
        scc.configureBlocking(false);
        InetSocketAddress isa = new InetSocketAddress(8000);
        scc.socket().bind(isa);
        SelectionKey acceptKey = scc.register(selector, SelectionKey.OP_ACCEPT);
        for (; ; ) {
            selector.select();

            Set readyKey = selector.selectedKeys();
            Iterator i = readyKey.iterator();
            long e = 0;
            while (i.hasNext()) {
                SelectionKey sk = (SelectionKey) i.next();
                i.remove();

                if (sk.isAcceptable())
                    doAccept(sk);

                else if (sk.isValid() && sk.isReadable()) {
                    if (!time_start.containsKey(((SocketChannel)sk.channel()).socket())) {
                        time_start.put((((SocketChannel)sk.channel()).socket()),
                                System.currentTimeMillis());
                    }
                    doRead(sk);
                } else if (sk.isValid() && sk.isWritable()) {
                    doWrite(sk);
                    e = System.currentTimeMillis();
                    time_start.remove(((SocketChannel)sk.channel()).socket());
                    System.out.println("spend:"+(System.currentTimeMillis()-e));
                }
            }
        }
    }

    private static void doAccept(SelectionKey sk) {
        ServerSocketChannel server = (ServerSocketChannel)sk.channel();
        try {
            SocketChannel clientChannel = server.accept();
            clientChannel.configureBlocking(false);
            SelectionKey clientKey = clientChannel.register(selector,SelectionKey.OP_READ);
            EchoClient echoClient = new EchoClient();
            clientKey.attach(echoClient);

            InetAddress address = clientChannel.socket().getInetAddress();
            System.out.printf("Accepted connection from %s \n",address.getHostAddress());

        } catch (IOException e) {
            System.out.printf("Failed to accept ");
            e.printStackTrace();
        }
    }

    private static void doRead(SelectionKey sk) {
        SocketChannel channel = (SocketChannel)sk.channel();
        ByteBuffer bb = ByteBuffer.allocate(8192);
        int len;

        try {
            len = channel.read(bb);
            if (len < 0) {
                disconnect(sk);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            disconnect(sk);
            return;
        }

        bb.flip();
        //tp.execute(new HandleMsg(sk,bb));

    }

    private static ExecutorService tp = Executors.newCachedThreadPool();

    private static void disconnect(SelectionKey sk) {

    }

    private static void doWrite(SelectionKey sk) {


    }
}
