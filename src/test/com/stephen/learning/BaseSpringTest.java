package com.stephen.learning;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Auther: jack
 * @Date: 2018/10/25 15:06
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SinaWeiboMicroService.class)
@WebIntegrationTest({"server.port: 8989", "service.tag:local"})
public class BaseSpringTest {

}
