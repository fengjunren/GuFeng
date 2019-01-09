package cn.explo.gufeng.utils;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import cn.explo.gufeng.base.ActivityShareData;

public class ActivityDataBus{
    public static <T extends ActivityShareData> T getData(Context context, Class<T> tClass) {
        return getData(checkContext(context),tClass);
    }

    public static <T extends ActivityShareData> T getData(Activity context, Class<T> tClass) {
        return getData(checkContext(context),tClass);
    }
    public static <T extends ActivityShareData>
 T getData(FragmentActivity context, Class<T> tClass) {
        return ViewModelProviders.of(context).get(tClass);
    }

    private static FragmentActivity checkContext(Context context) {
        if(context instanceof FragmentActivity) return (FragmentActivity) context;
        throw new IllegalContextException();
    }

//    public static class ActivityShareData extends BaseViewModel {}

    public static class IllegalContextException extends RuntimeException {
        public IllegalContextException() {
            super("ActivityDataBus 需要FragmentActivity作为上下文！");
        }
    }
}