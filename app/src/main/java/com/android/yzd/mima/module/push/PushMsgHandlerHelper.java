package com.android.yzd.mima.module.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.android.yzd.mima.R;
import com.android.yzd.mima.constant.Constants;
import com.android.yzd.mima.module.push.bean.MiPushMessageInfo;
import com.android.yzd.mima.mvp.ui.activity.IndexActivity;
import com.android.yzd.mima.mvp.ui.activity.WebViewActivity;


/**
 * <p>Title:        PushMsgHandlerHelper
 * <p>Description:
 * <p>@author:      yzd
 * <p>Create Time:  2017/7/5 下午9:29
 */
public class PushMsgHandlerHelper {

    private static PushMsgHandlerHelper instance = null;

    private static Context mContext = null;

    /**
     * 通知管理器显示到达推送通知
     */
    private NotificationManager mNotifMan = null;

    private PushMsgHandlerHelper() {
        if (this.mNotifMan == null) {
            this.mNotifMan = (NotificationManager) mContext
                    .getSystemService(mContext.NOTIFICATION_SERVICE);
        }
    }


    public static PushMsgHandlerHelper getInstance(Context context) {
        mContext = context;

        if (instance == null) {
            instance = new PushMsgHandlerHelper();
        }

        return instance;
    }


    /**
     * desc:显示到通知栏
     * <p/>
     * author: jinyuef
     * date: 2016/05/08
     *
     * @param pushMessageInfo
     */
    public void showNotification(MiPushMessageInfo pushMessageInfo) {
        if (pushMessageInfo.noticeType < 0) {
            return;
        }

        Notification n = new NotificationCompat.Builder(mContext)
                .setContentTitle(TextUtils.isEmpty(pushMessageInfo.noticeTitle) ? mContext.getString(R.string.app_name) : pushMessageInfo.noticeTitle)
                .setContentText(pushMessageInfo.noticeDesc)
                .setTicker(pushMessageInfo.noticeDesc)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.app_icon2))
                .setSmallIcon(R.mipmap.app_icon2)
                .setWhen(System.currentTimeMillis()).build();

//        RemoteViews contentView = new RemoteViews(mContext.getPackageName(),
//                R.layout.notification_push_view);
//        contentView.setImageViewResource(R.id.content_view_image,
//                R.drawable.icon);
//        contentView.setTextViewText(R.id.notification_push_title,
//                TextUtils.isEmpty(pushMessageInfo.noticeTitle) ? mContext.getResources().getString(R.string.app_name) : pushMessageInfo.noticeTitle);//标题
//        contentView.setTextViewText(R.id.notification_push_time,
//                DateUtils.getNowTimeToString("HH:mm"));//时间
//        contentView.setTextViewText(R.id.notification_push_content,
//                pushMessageInfo.noticeDesc);//内容
//
//        n.contentView = contentView;

        // 根据 PushMessageInfo.linkType 决定通知栏跳转的目的
        Intent intent = null;

        if (pushMessageInfo.linkType.equals(Constants.PUSH.LINK_BROSWER)) {//浏览器打开wap地址
            // 浏览器打开wap地址
            if (!TextUtils.isEmpty(pushMessageInfo.linkUrl)) {
                intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(Constants.WEBVIEW.url, pushMessageInfo.linkUrl);
            }
        } else {
            intent = new Intent(mContext, IndexActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Constants.INTENT.from_notification, true);
            intent.putExtra(Constants.INTENT.push_message_info, pushMessageInfo);
        }

        PendingIntent pi = PendingIntent.getActivity(mContext, n.hashCode(),
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        n.contentIntent = pi;
        int noticeId = n.hashCode();

        mNotifMan.notify(noticeId, n);
    }


}
