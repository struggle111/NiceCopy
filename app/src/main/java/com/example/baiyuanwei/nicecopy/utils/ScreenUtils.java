package com.example.baiyuanwei.nicecopy.utils;

/**
 * Created by bluef on 14-2-15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ScreenUtils {
    private static final String TAG = "ScreenUtils";
    private static final Map<Float, Integer> DP_TO_PX_CACHE = new HashMap<>(50);
    private static int SCREEN_WIDTH_PX_CACHE = -1;
    private static int SCREEN_HEIGHT_PX_CACHE = -1;
    private static int SCREEN_WIDTH_DP_CACHE = -1;
    private static int SCREEN_HEIGHT_DP_CACHE = -1;
    private static float SCREEN_ONE_DP_TO_PX = -1;
    private static float SCREEN_ONE_PX_TO_DP = -1;
    private static int STATUS_BAR_HEIGHT = -1;

    public static int dp2px(Context context, float dp) {
        if (context == null) {
            return 0;
        }

        if (DP_TO_PX_CACHE.containsKey(dp)) {
            return DP_TO_PX_CACHE.get(dp);
        }

        if (SCREEN_ONE_DP_TO_PX < 0) {
            SCREEN_ONE_DP_TO_PX = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, context.getResources().getDisplayMetrics());
        }

        int ret = (int) (SCREEN_ONE_DP_TO_PX * dp);

        DP_TO_PX_CACHE.put(dp, ret);

        return ret;
    }

    public static int px2dp(Context context, float px) {
        if (SCREEN_ONE_PX_TO_DP < 0) {
            Resources resources = Resources.getSystem();
            DisplayMetrics metrics = resources.getDisplayMetrics();
            SCREEN_ONE_PX_TO_DP = (1 / (metrics.densityDpi / 160f));
        }

        return (int) (SCREEN_ONE_PX_TO_DP * px);
    }

    public static int getScreenWidthPx(Context context) {
        return getScreenWidthPx();
    }

    public static int getScreenWidthPx() {
        if (SCREEN_WIDTH_PX_CACHE < 0) {
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();

            SCREEN_WIDTH_PX_CACHE = metrics.widthPixels;

//            //fix class cast exception
//            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            Display display = windowManager.getDefaultDisplay();
//            SCREEN_WIDTH_PX_CACHE = display.getWidth();
        }

        return SCREEN_WIDTH_PX_CACHE;
    }

    public static int getScreenHeightPx(Context context) {
        return getScreenHeightPx();
    }

    public static int getScreenHeightPx() {
        if (SCREEN_HEIGHT_PX_CACHE < 0) {
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            SCREEN_HEIGHT_PX_CACHE = metrics.heightPixels;

//            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            SCREEN_HEIGHT_PX_CACHE = display.getHeight();
        }

        return SCREEN_HEIGHT_PX_CACHE;
    }

    public static Point getScreenDimensionsInDp(Context context) {
        /*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			Configuration configuration = context.getResources().getConfiguration();
			return new Point(configuration.screenWidthDp, configuration.screenHeightDp);
		} else {
		*/

        if (SCREEN_WIDTH_DP_CACHE < 0 || SCREEN_HEIGHT_DP_CACHE < 0) {
            // APIs prior to v13 gave the screen dimensions in pixels. We convert them to DIPs before returning them.
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);

            SCREEN_WIDTH_DP_CACHE = px2dp(context, displayMetrics.widthPixels);
            SCREEN_HEIGHT_DP_CACHE = px2dp(context, displayMetrics.heightPixels);
        }

        return new Point(SCREEN_WIDTH_DP_CACHE, SCREEN_HEIGHT_DP_CACHE);
    }

    public static void hideSoftKeyboard(Activity activity) {
        try {
            WeakReference<Activity> activityWeakReference = new WeakReference<>(activity);

            InputMethodManager inputMethodManager = (InputMethodManager) activityWeakReference.get().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(activityWeakReference.get().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float getTextDisplayWidth(String text, int textSize) {
        Paint textPaint = new Paint();
        textPaint.setTextSize(textSize);

        return textPaint.measureText(text);
    }

//    /**
//     * use {@link android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE } for auto brightness,
//     * use {@link android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL } for full brightness,
//     * use {@link android.view.WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_OFF } for low brightness
//     */
//    public static void adjustScreenBrightness(Activity context, float brightness) {
//        try {
//            WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//            lp.screenBrightness = brightness;
//            context.getWindow().setAttributes(lp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * get text font size
     *
     * @param context
     * @param name
     * @return
     */
    public static int getTextWidth(Context context, String name) {
        return getTextWidth(context, name, 12);//default font size is 12sp
    }

    /**
     * get text font size
     *
     * @param context
     * @param name
     * @param fontSize
     * @return
     */
    public static int getTextWidth(Context context, String name, float fontSize) {
        Paint textPaint = new Paint();
        textPaint.setTextSize(ScreenUtils.dp2px(context, fontSize));
        Rect textSize = new Rect();
        textPaint.getTextBounds(name, 0, name.length(), textSize);

        return textSize.width();
    }

    public static int getTextHeight(Context context, float fontSize) {
        final float fontScale = Resources.getSystem().getDisplayMetrics().scaledDensity;
        return (int) (fontSize * fontScale + 0.5f);
    }

    public static int getStatusBarHeight(Context context) {
        if (STATUS_BAR_HEIGHT == -1) {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                STATUS_BAR_HEIGHT = context.getResources().getDimensionPixelSize(resourceId);
            }
        }

        return STATUS_BAR_HEIGHT;
    }

    /**
     * 获取屏幕尺寸，单位为英寸
     * 计算屏幕尺寸应该使用精确密度：xdpi ydpi来计算
     * 使用归一化密度：densitydpi是错误的，它是固定值，
     * 120 160 240 320 480,根据dp计算像素才使用它
     *
     * @param context
     * @return
     */
    public static double getScreenSizeInInch(Activity context) {
        if (Build.VERSION.SDK_INT >= 17) {
            Point point = new Point();
            context.getWindowManager().getDefaultDisplay().getRealSize(point);

            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            double x = Math.pow(point.x / dm.xdpi, 2);
            double y = Math.pow(point.y / dm.ydpi, 2);
            double screenInches = Math.sqrt(x + y);
//            System.out.println("qqqqqqq=="+screenInches);
            return screenInches;
        } else {
            DisplayMetrics dm = new DisplayMetrics();
            context.getWindowManager().getDefaultDisplay().getMetrics(dm);
            double heightInInch = (double) dm.heightPixels / (double) (dm.densityDpi);
            double widthInInch = (double) dm.widthPixels / (double) (dm.densityDpi);
            double screenSizeInInch = Math.sqrt(heightInInch * heightInInch
                    + widthInInch * widthInInch);
//            System.out.println("qqqqqq==" + dm.heightPixels + "," + dm.ydpi + "," + dm.density + "," + dm.widthPixels + "," + dm.xdpi + "," + dm.densityDpi);
//            System.out.println("qqqqqq==高：" + heightInInch + " 宽：" + widthInInch + " 尺寸:" + screenSizeInInch
//                    + " 单位（英寸)");
            return screenSizeInInch;
        }
    }


    public static int getRealScreenHeight(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    public static int getContentScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static int getNavigationBarHeight(Context context) {
        int totalHeight = getRealScreenHeight(context);

        int contentHeight = getContentScreenHeight(context);

        return totalHeight - contentHeight;
    }

}