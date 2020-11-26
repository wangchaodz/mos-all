package com.mos.module.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description:
 * @date: 2020/11/21 15:13
 * @author: wangchaodz@gmail.com
 */
public class LogbackExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackExample.class);

    public static void main(String[] args) {
        LOGGER.info("==========================>logback");
    }
}
