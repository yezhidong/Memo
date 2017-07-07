package com.android.yzd.mima.module.push;


import android.content.Context;
import android.text.TextUtils;

import com.android.yzd.mima.module.push.bean.MiPushMessageInfo;
import com.android.yzd.mima.utils.JsonUtils;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

/**
 * <p>Title:        MiPushMessageReceiver
 * <p>Description:
 * <p>@author:      yzd
 * <p>Create Time:  2017/7/5 下午7:51
 */
public class MiPushMessageReceiver extends PushMessageReceiver {

    private PushMsgHandlerHelper mPushMsgHelper;

    /**
     * 接收服务器的透传消息
     *
     * @param context
     * @param message
     */
    @Override
    public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
        handMsgFromPush(context, message.getContent());
    }



    /**
     * 处理接收到的推送数据
     *
     * @param context
     * @param message
     */
    public void handMsgFromPush(Context context, String message) {
        if (!TextUtils.isEmpty(message)) {

            MiPushMessageInfo pushMessageInfo = (MiPushMessageInfo) JsonUtils.fromJson(message, MiPushMessageInfo.class);
            if (pushMessageInfo != null) {
                if (mPushMsgHelper == null) {
                    mPushMsgHelper = PushMsgHandlerHelper.getInstance(context);
                }
                mPushMsgHelper.showNotification(pushMessageInfo);
            }
        }
    }

}
