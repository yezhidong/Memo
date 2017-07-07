package com.android.yzd.mima.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title:        AppUtils
 * <p>Description:  跟App相关的辅助类
 * <p>@author:      yzd
 * <p>Create Time:  2017/6/1 下午8:56
 */
public class AppUtils {

	private AppUtils() {
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");

	}

	/**
	 * 是否在主线程(UI线程)运行
	 * @return
	 */
	public static boolean isMainThread() {
		return Looper.myLooper() == Looper.getMainLooper();
	}

	/**
	 * 判断ctx是不是在主进程
	 * @param ctx
	 * @return
	 */
	public static boolean isMainProcess(Context ctx){
		String processName = getProcessName(ctx);
		return processName != null && !processName.contains(":");
	}

	/**
	 * desc: 判断是不是运行在pname进程里
	 * <p/>
	 * author:yzd
	 * date:2016-06-30
	 *
	 * @param context
	 * @param pname
	 * @return
	 */
	public static boolean isInProcess(Context context, String pname) {
		String processName = getProcessName(context);
		return processName != null && processName.equals(pname);
	}

	/**
	 * 获得ctx所在的进程名
	 * @param ctx
	 * @return
	 */
	public static String getProcessName(Context ctx) {
		int pid = Process.myPid();
		ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
		if (runningApps == null) {
			return null;
		}
		for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
			if (procInfo.pid == pid) {
				return procInfo.processName;
			}
		}
		return null;
	}

	public static String getProcessName(Context cxt, int pid) {
		ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
		if (runningApps == null) {
			return null;
		}
		for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
			if (procInfo.pid == pid) {
				return procInfo.processName;
			}
		}
		return null;
	}

	/**
	 * 获取应用程序名称
	 * 
	 * @param context
	 * @return
	 * @date 2015年1月5日
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取应用程序版本名称信息
	 * 
	 * @param context
	 * @return
	 * @date 2015年1月5日
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取应用程序版本编号信息
	 * 
	 * @param context
	 * @return
	 * @date 2015年1月5日
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionCode;

		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 判断服务是否正在运行
	 * 
	 * @param context 上下文对象
	 * @param serviceName 服务名
	 * @return 是/否正在运行
	 */
	public static boolean isServiceRunning(Context context, String serviceName) {
		ActivityManager myAM = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningServices = (ArrayList<RunningServiceInfo>) myAM
				.getRunningServices(Integer.MAX_VALUE);
		for (int i = 0; i < runningServices.size(); i++) {
			if (runningServices.get(i).service.getClassName().toString().equals(serviceName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前应用程序是否处于后台运行
	 * 
	 * @param context 上下文对象
	 * @return 是/否运行在后台
	 */
	public static boolean isAppRunningBackground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (!tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (!topActivity.getPackageName().equals(context.getPackageName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取AndroidManifest.xml中的package
	 *
	 * @param context
	 * @return
	 */
	public static String getPackage(Context context) {
		String packageName = "";
		PackageInfo pi = getCurrentPackageInfo(context);
		if (null != pi) {
			packageName = pi.packageName;
		}
		return packageName;
	}

	/**
	 * 判断某个android程序是否安装
	 * 
	 * @param context 上下文对象
	 * @param packageName 程序包名
	 * @return 是/否安装
	 */
	public static boolean isPkgInstalled(Context context, String packageName) {
		try {
			PackageManager pm = context.getPackageManager();
			pm.getPackageInfo(packageName, 0);
		} catch (NameNotFoundException e) {
			return false;
		}
		return true;
	}

	/**
	 * 获取包信息
	 *
	 * @param context
	 * @return
	 */
	public static PackageInfo getCurrentPackageInfo(Context context) {
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			return pi;
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @Description: 通过包名打开某个程序
	 * @param context 上下文对象
	 * @param packageName 程序包名
	 * @return void 返回类型
	 */
	public static void openAppByPkgName(Context context, String packageName) {
		PackageInfo pi;
		try {
			pi = context.getPackageManager().getPackageInfo(packageName, 0);
			PackageManager pm = context.getPackageManager();
			Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
			resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			resolveIntent.setPackage(pi.packageName);

			List<ResolveInfo> apps = pm.queryIntentActivities(resolveIntent, 0);

			ResolveInfo ri = apps.iterator().next();
			if (ri != null) {
				String pkgName = ri.activityInfo.packageName;
				String className = ri.activityInfo.name;

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);

				ComponentName cn = new ComponentName(pkgName, className);

				intent.setComponent(cn);
				context.startActivity(intent);
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 退出客户端
	 * @param context 上下文
	 */
	public static void exit(Context context) {
		try {
			System.exit(0);
			Process.killProcess(Process.myPid());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * zhonglong 2011-01-29 增加根据包名获取版本号的方法
	 */
	public static String getPkgVersionName(Context context, String packageName) {
		String versionName = "";
		try {
			PackageManager pm = context.getApplicationContext().getPackageManager();
			versionName = pm.getPackageInfo(packageName, 0).versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	/** 隐藏软键盘 */
	public static void hideIme(Activity context, EditText editText) {
		if (context == null){
			return;
		}
		final View v = context.getWindow().peekDecorView();
		if (v != null && v.getWindowToken() != null) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
	}



	/**
	 * 在外部浏览器打开网页
	 *
	 * @param context
	 * @param url
	 */
	public static void openUrl(final Context context, String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://")) {
			url = "http://" + url;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		context.startActivity(intent);
	}

	/**
	 * 手机是否存在虚拟按键
	 */
	@SuppressLint("NewApi")
	public static boolean checkDeviceHasNavigationBar(Context context) {

		//通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
		boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
		boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

		if (!hasMenuKey && !hasBackKey) {
			// 做任何你需要做的,这个设备有一个导航栏
			return true;
		}
		return false;
	}

	/** 获取虚拟按键高度 */
	public static int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		//获取NavigationBar的高度
		int height = resources.getDimensionPixelSize(resourceId);
		return height;
	}

	/** 显隐虚拟按钮 */
	public static void toggleNavigationBar(Activity activity, boolean isShow) {

		// The UI options currently enabled are represented by a bitfield.
		// getSystemUiVisibility() gives us that bitfield.
		int uiOptions = activity.getWindow().getDecorView().getSystemUiVisibility();
		int newUiOptions = uiOptions;
		boolean isImmersiveModeEnabled = ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
//		if (isImmersiveModeEnabled) {
//			Log.i("lodz", "Turning immersive mode mode off. ");
//		} else {
//			Log.i("lodz", "Turning immersive mode mode on.");
//		}
		if (isImmersiveModeEnabled && !isShow){
			return;
		}

		if (!isImmersiveModeEnabled && isShow){
			return;
		}

		// Navigation bar hiding:  Backwards compatible to ICS.
		if (Build.VERSION.SDK_INT >= 14) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
		}

		// Status bar hiding: Backwards compatible to Jellybean
		if (Build.VERSION.SDK_INT >= 16) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
		}

		// Immersive mode: Backward compatible to KitKat.
		// Note that this flag doesn't do anything by itself, it only augments the behavior
		// of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
		// all three flags are being toggled together.
		// Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
		// Sticky immersive mode differs in that it makes the navigation and status bars
		// semi-transparent, and the UI flag does not get cleared when the user interacts with
		// the screen.
		if (Build.VERSION.SDK_INT >= 18) {
			newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		}

		activity.getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
	}

	/** 获取当前进程名字 */
	public static String getCurProcessName(Context context) {
		int pid = Process.myPid();
		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
			if (appProcess.pid == pid) {

				return appProcess.processName;
			}
		}
		return null;
	}
}
