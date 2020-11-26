package com.mos.example.logging;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @date: 2020/11/21 14:43
 * @author: wangchaodz@gmail.com
 */
public class Log4jExample {

    //private static final Logger LOGGER = Logger.getLogger(Log4jExample.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(Log4jExample.class);

    public static void main(String[] args) {
        LOGGER.info("==========================>log4j");
    }
}
