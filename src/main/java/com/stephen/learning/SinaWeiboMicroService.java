package com.stephen.learning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: jack
 * @Date: 2018/10/25 14:50
 * @Description:
 */
@Slf4j
@SpringBootApplication
public class SinaWeiboMicroService {
    public static void main(String[] args) {
        SpringApplication.run(SinaWeiboMicroService.class, args);
    }
}
