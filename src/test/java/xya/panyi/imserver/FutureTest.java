package xya.panyi.imserver;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    @Test
    public void test(){
        NioEventLoopGroup group = new NioEventLoopGroup();
        Promise<Integer> promise = new DefaultPromise<Integer>(group.next());

        promise.setSuccess(0);

        Future<Integer> future = Executors.newFixedThreadPool(2).submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 100;
            }
        });

        try {
            int val = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
