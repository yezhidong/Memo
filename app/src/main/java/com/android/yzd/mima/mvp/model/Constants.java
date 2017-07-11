package com.android.yzd.mima.mvp.model;

/**
 * Created by yezhidong on 2016/1/26.
 */
public class Constants {

    public static final String AD_AppId = "1106256606";
    public static final String SplashPosID = "6030226460238558";


    public static int REALM_VERSION = 1;

    public static int VIEW_MODE = 0;
    public static int CREATE_MODE = 1;

    public static int CREATE_GESTURE = 1;
    public static int UPDATE_GESTURE = 2;

    public static String COUNT = "COUNT";

    public final static class EVEN_BUS {
        public static int CHANGE_THEME = 2;
        public static int CHANGE_PASS_WORD_SHOW = 3;
    }

    public final static class SETTING {
        public static String OPEN_GESTURE = "OPEN_GESTURE";
        public static String OPEN_PASS_WORD_SHOW = "OPEN_PASS_WORD_SHOW";
        public static String OPEN_AD = "OPEN_AD";
    }
}
