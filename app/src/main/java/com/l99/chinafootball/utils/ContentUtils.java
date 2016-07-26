package com.l99.chinafootball.utils;

/**
 * Created by lifeix-101 on 2016/7/25.
 */
public class ContentUtils {
    public static String htmlSpace(String introduce) {
        return  introduce.trim().replaceAll("<p>","<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    }
}
