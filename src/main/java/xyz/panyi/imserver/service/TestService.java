package xyz.panyi.imserver.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xyz.panyi.imserver.log.LogUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Service
public class TestService {

    @PostConstruct
    public void init(){
        LogUtil.log("TestService init " + hashCode());

        try(final FileChannel fileChannel = new FileInputStream("").getChannel()){
//            fileChannel.force(true);
            ByteBuffer.allocateDirect(200);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PreDestroy
    public void destory(){
        LogUtil.log("TestService destory " + hashCode());
    }
}
