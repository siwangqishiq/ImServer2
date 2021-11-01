package xya.panyi.imserver;

import javax.xml.crypto.KeySelector;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest {
    public static void main(String args[]){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.configureBlocking(false);
            Selector masterSelector = Selector.open();
            serverSocketChannel.register(masterSelector , SelectionKey.OP_ACCEPT);
            serverSocketChannel.bind(new InetSocketAddress(4444));

            while(true){
                System.out.println("listen slector ...");
                int selectCount = masterSelector.select();
                if(selectCount >= 0){
                    Set<SelectionKey> keys = masterSelector.selectedKeys();
                    final Iterator<SelectionKey> iter = keys.iterator();
                    while(iter.hasNext()){
                        final SelectionKey selectionKey = iter.next();
                        iter.remove();

                        if(selectionKey.isAcceptable()){
                            System.out.println("client connect");
                            ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                            final SocketChannel clientChannel = ssc.accept();

                            System.out.println("client Ip = " + clientChannel.getRemoteAddress().toString());
                            clientChannel.close();
                        }
                    }//end while
                }
            }//end while
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
