package com.mos.module.logging;


//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @date: 2020/11/21 14:43
 * @author: wangchaodz@gmail.com
 */
public class Log4j2Example {

    //private static final Logger LOGGER = LogManager.getLogger(Log4j2Example.class);

    private static final Logger LOGGER = LoggerFactory.getLogger(Log4j2Example.class);

    public static void main(String[] args) {
        LOGGER.info("==========================>log4j2");
    }
}
