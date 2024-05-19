package com.jeogi.jeogitrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secure.properties")
public class JeogiTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeogiTripApplication.class, args);
    }

}
