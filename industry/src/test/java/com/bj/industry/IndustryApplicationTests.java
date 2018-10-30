package com.bj.industry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


@RunWith(SpringRunner.class)
@SpringBootTest
public class IndustryApplicationTests {

	@Test
	public void contextLoads() {
		Jedis jedis = new Jedis("173.82.240.222");
//        jedis.auth("123456");
		System.out.println("ping: "+jedis.ping());
		jedis.set("name","张三");
		System.out.println(jedis.get("name"));
	}

	@Test
	public void loadTest(){
	}
}
