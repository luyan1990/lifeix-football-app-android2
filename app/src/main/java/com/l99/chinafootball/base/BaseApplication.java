package com.l99.chinafootball.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.orhanobut.logger.Logger;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * 自定义Application
 *
 */
public class BaseApplication extends Application {

	public static Context sContext;

	public static Handler handler;
	public static int mainThreadId;
	
	// 当应用创建的时候,调用此方法
	@Override
	public void onCreate() {
		super.onCreate();
		sContext = getApplicationContext();
//		SMSSDK.initSDK(this, "1362441d82828", "27d21be7415d50847e6eb803b780fab6");
		//LeakCanary.install(this);
		Logger.init(this.getApplicationInfo().packageName);
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
//
				.connectTimeout(5000L, TimeUnit.MILLISECONDS)
				.readTimeout(5000L, TimeUnit.MILLISECONDS)
				.build();

		OkHttpUtils.initClient(okHttpClient);

		handler = new Handler();

	}

	public static Handler getHandler() {
		return handler;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

}
