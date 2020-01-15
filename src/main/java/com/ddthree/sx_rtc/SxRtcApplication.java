package com.ddthree.sx_rtc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.ddthree.sx_rtc.dao")
@SpringBootApplication
public class SxRtcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SxRtcApplication.class, args);
    }

}
