package com.l99.chinafootball;

import com.l99.chinafootball.base.BaseApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by 78101 on 2016/7/14.
 */
public class AssetsUtils {
    public static String getFromAssets(String fileName){
        try {
            InputStreamReader inputReader = new InputStreamReader(BaseApplication.sContext.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
