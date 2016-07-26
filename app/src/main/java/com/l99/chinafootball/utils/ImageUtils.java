package com.l99.chinafootball.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.l99.chinafootball.base.BaseApplication;

/**
 * Created by lifeix-101 on 2016/6/23.
 */
public class ImageUtils {
    public static final int SMALL = 0;
    public static final int MEDIUM = 1;
    public static final int BIG = 2;


    public static void setImage(ImageView imageView, String url) {
        Glide.with(BaseApplication.sContext).load(url).into(imageView);
    }

    public static void flagCompress(String imageName, int size,ImageView image) {
        String url;
        switch (size) {
            case SMALL:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/60/h/42";
                break;
            case MEDIUM:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/120/h/84";
                break;
            case BIG:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/240/h/172";
                break;
            default:
                url = Url.IMAGE_URL + imageName;
                //最大原图
        }
        setImage(image, url);
    }

    public static void compressLogo(String imageName,ImageView image){
       // http://roi.skst.cn/logo/3376.png!icon?imageView/2/w/100/h/100
        String url = "http://roi.skst.cn/logo/" +imageName+"!icon?imageView/2/w/100/h/100";
        setImage(image, url);
    }

    public  static void compressHeroesImage(String imageName, int size ,ImageView image){
        String url;
        switch (size) {
            case SMALL:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/35/h/43";
                break;
            case MEDIUM:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/105/h/129";
                break;
            case BIG:
                url = Url.IMAGE_URL + imageName + "?imageView/1/w/210/h/258";
                break;
            default:
                url = Url.IMAGE_URL + imageName;
                //最大原图
        }
       setImage(image,url);

    }


    public static void playerAvatarCompress(String avatar, int size, ImageView image) {
        String url;
        switch (size) {
            case SMALL:
                url = Url.IMAGE_URL + avatar + "?imageView/1/w/157/h/237";
                break;
            case MEDIUM:
                url = Url.IMAGE_URL + avatar + "?imageView/1/w/315/h/474";
                break;
            case BIG:
                url = Url.IMAGE_URL + avatar + "?imageView/1/w/631/h/948";
                break;
            default:
                url = Url.IMAGE_URL + avatar;
                //最大原图
        }
        setImage(image,url);

    }
    public static void refereesAvatarCompress(String avatar, ImageView image) {
        String url="";
        url=Url.IMAGE_URL+avatar+"?imageView/1/w/180/h/250";
        setImage(image,url);
    }
}