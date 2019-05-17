package com.megvii.demo.utils;

import android.content.Context;
import android.util.Base64;

import com.megvii.demo.bo.Constant;

import java.util.UUID;


public class Configuration {
    public static String getUUID(Context context) {
        String uuid = SpFileUtil.getString(context, SpFileUtil.FILE_NAME, SpFileUtil.KEY_UUID, "");
        if ("".equals(uuid)) {
            uuid = UUID.randomUUID().toString();
            uuid = Base64.encodeToString(uuid.getBytes(), Base64.DEFAULT);
            SpFileUtil.saveString(context, SpFileUtil.FILE_NAME, SpFileUtil.KEY_UUID, uuid);
        }
        return uuid;
    }

    public static void setIsVertical(Context context, boolean ISVERTICAL) { //屏幕方向 0竖屏 1 横屏
        SpFileUtil.saveBoolean(context, SpFileUtil.FILE_NAME, Constant.ISVERTICAL, ISVERTICAL);

    }

    public static boolean getIsVertical(Context context) {
        return SpFileUtil.getBoolean(context, SpFileUtil.FILE_NAME, Constant.ISVERTICAL, true);
    }

    public static void setCardType(Context context, int cardType) {     //0:双面，1:人像面，2:国徽面（默认人像面）
        SpFileUtil.saveInt(context, SpFileUtil.FILE_NAME, Constant.CARD_SIDE, cardType);
    }

    public static int getCardType(Context context) {
        return SpFileUtil.getInt(context, SpFileUtil.FILE_NAME, Constant.CARD_SIDE, 1);
    }

}
