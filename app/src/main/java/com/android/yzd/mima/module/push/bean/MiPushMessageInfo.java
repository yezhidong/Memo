package com.android.yzd.mima.module.push.bean;

import java.io.Serializable;

/**
 * <p>Title:        MiPushMessageInfo
 * <p>Description:  小米推送消息内容
 * <p>@author:      yzd
 * <p>Copyright:    Copyright (c) 2016
 * <p>Company:      @咪咕动漫
 * <p>Create Time:  17/6/23 下午3:22
 * <p>@author:
 * <p>Update Time:
 * <p>Updater:
 * <p>Update Comments:
 */
public class MiPushMessageInfo implements Serializable {

    public String jobkey;//任务Id
    public String noticeTitle;//标题
    public String noticeDesc;//描述
    public int noticeType;//推送类型
    public String userId;//用户id（推送类型为私信时用到）

    public String linkType;// 跳转类型
    public String linkUrl;// 跳转的url

}
