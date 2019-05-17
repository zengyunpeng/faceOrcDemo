package com.teemo.myapplication.demo.utils;

/**
 * description ： 身份证&银行卡识别
 * author : teemo
 * email :
 * date : 2019/5/16 17:58
 */
public class OrcUtil {
    private volatile static OrcUtil orcUtil;


    public static OrcUtil getInstance() {
        if (orcUtil == null) {
            synchronized (OrcUtil.class) {
                if (orcUtil == null) {
                    orcUtil = new OrcUtil();
                }
            }
        }
        return orcUtil;
    }



}
