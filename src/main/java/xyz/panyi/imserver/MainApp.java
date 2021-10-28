package xyz.panyi.imserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * entry point
 */
@SpringBootApplication
@EnableScheduling
public class MainApp {
    public static void main(String[] args){
        SpringApplication.run(MainApp.class , args);
    }
}
