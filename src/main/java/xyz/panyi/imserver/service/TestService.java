package xyz.panyi.imserver.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import xyz.panyi.imserver.log.LogUtil;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class TestService {

    @PostConstruct
    public void init(){
        LogUtil.log("TestService init " + hashCode());
    }


    @PreDestroy
    public void destory(){
        LogUtil.log("TestService destory " + hashCode());
    }
}
