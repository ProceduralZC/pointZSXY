package com.android.commonapp.config;
/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 配置地址
 */
public class HttpConfig {
//    public static String BASE_URL = "http://121.42.197.96:8089/";
    public static String BASE_URL = "http://120.27.37.115:8085/";//自己服务器
//    public static String BASE_URL = "http://192.168.1.185:8089/";//公司ip
//    public static String BASE_URL = "http://192.168.0.105:8089/";//家里ip

    public static long CONNECT_TIMEOUT = 10000L;
    public static long WRITE_TIMEOUT = 10000L;
    public static long READ_TIMEOUT = 10000L;

    public static final String SUCCESS = "ok";
    public static final String FAILED = "error";
}
