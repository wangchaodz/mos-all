package com.mos.task.executor;

import com.mos.base.BaseTest;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @date: 2020/9/12 9:52
 * @author: wangchaodz@gmail.com
 */
class ParkJobTaskTest extends BaseTest {

    @Resource
    private ParkJobTask parkJobTask;

    @Test
    void parkJobHandler() throws Exception {
        parkJobTask.parkJobHandler("");
    }
}