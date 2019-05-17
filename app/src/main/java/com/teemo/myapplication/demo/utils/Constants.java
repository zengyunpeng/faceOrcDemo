package com.teemo.myapplication.demo.utils;

/**
 * description ： TODO:类的作用
 * author : teemo
 * email :
 * date : 2019/5/16 11:42
 */
public interface Constants {
    /**
     * 人脸识别鉴权接口
     */
    String GET_BIZTOKEN_URL = "https://api.megvii.com/faceid/v3/sdk/get_biz_token";
    /**
     * 人脸识别人脸比对接口
     */
    String VERIFY_URL = "https://api.megvii.com/faceid/v3/sdk/verify";

    String API_KEY = "nfihZtxQviTdqDnRxf_uSCURAY0XUhyy";
    String SECRET = "Fuhh9fp7v6OgSGzftwkXQTIvSQaBZnrd";
    String SIGN_VERSION = "hmac_sha1";

    /**
     * 静态检测
     */
    String STILL = "still";
    /**
     * 活体检测
     */
    String MEGLIVE = "meglive";

}
