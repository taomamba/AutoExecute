package com.inspur.auto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InspurApplicationTests {



    @Test
    public void contextLoads() {
        Logger logger = LoggerFactory.getLogger(getClass());
        //日志级别从低到高
        logger.trace("");
        logger.debug("");
        //springboot默认使用的是info级别的日志
        logger.info("");
        logger.warn("");
        logger.error("");
    }
    @Test
    public void intTest(){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

}
