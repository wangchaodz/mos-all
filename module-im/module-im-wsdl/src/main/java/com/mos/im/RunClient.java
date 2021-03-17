package com.mos.connection;

/**
 * 请用一句话描述下你打算干啥
 * @date  2021/1/30 14:27
 * @author  wangchaodz@gmail.com
 **/
public interface RunClient<RE,RS> {

    RS execute(RE request);
}
