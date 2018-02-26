package com.skcc.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.skcc"})
@Slf4j
public class AppMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext run                 = SpringApplication.run(AppMain.class, args);
        String[]                       beanDefinitionNames = run.getBeanDefinitionNames();

        Arrays.sort(beanDefinitionNames);
        for (String name : beanDefinitionNames) {
//            System.out.println("name = " + name);
//            log.debug("name: " + name);
        }
    }
}
