package com.megvii.demo.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * description ： 缓存图片的工具类
 * author : teemo
 * email :
 * date : 2019/5/20 15:49
 */
public class BitmapUtil {
    public static String saveBitmap(Activity activity, Bitmap mBitmap) {
        File file = new File(activity.getApplicationContext().getExternalCacheDir() + "/files/");
        if (!file.exists()) {
            file.mkdir();
        }
        File f = new File(file.getAbsolutePath() + File.separator + setFileName() + ".jpg");
        try {
            f.createNewFile();
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String image_file_url = f.getAbsolutePath();
        Log.i("image_file_url", image_file_url);
        return image_file_url;
    }

    private static String setFileName() {
        return System.currentTimeMillis() + "bmp";
    }


    public static Bitmap getBitmapFromUri(Activity context, Uri uri) {
        try {
            // 读取uri资源中的bitmap
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }


    public static Bitmap getBitmapFromAbosolutePath(String fileName) {
        try {
            return BitmapFactory.decodeFile(fileName);
        } catch (OutOfMemoryError e) {
            return null;
        }

    }


    //将Uri中图片相关数据转换成bitmap
    private void UriTobitmap(Activity context, ImageView imageView) {
        Intent intent = context.getIntent();
        if (intent != null) {
            Uri uri = intent.getData();
            if (uri == null) {
                Log.i("tag", "The uri is not exist.");
                return;
            }
            intent.setDataAndType(uri, "image/*");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("intent");
                imageView.setImageBitmap(photo);
            } else {
                Log.i("extras", "The extras is null.");
            }
        } else {
            Log.i("intent", "The intent is null.");
        }
    }
}
