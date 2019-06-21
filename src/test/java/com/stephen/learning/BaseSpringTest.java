package com.stephen.learning;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: jack
 * @Date: 2018/10/25 15:06
 * @Description:
 */

public class BaseSpringTest {
    public static void main(String[] args) {
        Map<String,String> map = Collections.synchronizedMap(new HashMap<>());

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }
    }
}
