package com.android.yzd.memo.otto;

import com.squareup.otto.Bus;

/**
 * Created by Administrator on 2016/1/20.
 */
public class OttoBus extends Bus{

    private static OttoBus instances;
    private OttoBus() {}
    public static OttoBus getInstances() {
        synchronized (OttoBus.class) {
            if (instances == null) {
                instances = new OttoBus();
            }
        }
        return instances;
    }
}
