package com.animationdemo.util;

import android.app.Activity;

import com.animationdemo.activity.MainActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Stack;

public class ActivityManager {
    private Stack<WeakReference<Activity>> mActivityStack;
    private static ActivityManager mAppManager;
    private static Object o = new Object();

    private ActivityManager() {
    }

    /**
     * 单一实例
     */
    public static ActivityManager getInstance() {
        synchronized (o) {
            if (mAppManager == null) {
                mAppManager = new ActivityManager();
            }
        }
        return mAppManager;
    }

    /**
     * @return 返回Activity的个数
     */
    public int getCount() {
        return mActivityStack.size();

    }

    /**
     * @return 返回Activity的管理站
     */
    public Stack<WeakReference<Activity>> getActivityStack() {
        return mActivityStack;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        WeakReference<Activity> a = new WeakReference<Activity>(activity);
        synchronized (o) {
            if (mActivityStack == null) {
                mActivityStack = new Stack<WeakReference<Activity>>();
            }
            mActivityStack.add(a);
        }
        removeNoValidActivity();
    }

    // 移除无效对象,防止数组持续增大
    private void removeNoValidActivity() {
        synchronized (o) {
            if (mActivityStack != null && mActivityStack.size() > 0) {
                ArrayList<WeakReference<Activity>> l = new ArrayList<WeakReference<Activity>>();
                for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                    if (null == mActivityStack.get(i) || mActivityStack.get(i).get() == null) {

                        l.add(mActivityStack.get(i));
                    }
                }
                if (l.size() > 0) mActivityStack.removeAll(l);
            }
        }
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public WeakReference<Activity> getTopActivity() {
        WeakReference<Activity> activity;
        synchronized (o) {
            activity = mActivityStack.lastElement();
        }
        return activity;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        WeakReference<Activity> activity;
        synchronized (o) {
            activity = mActivityStack.lastElement();
        }
        killActivity(activity);

    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(WeakReference<Activity> activity) {
        synchronized (o) {
            if (activity != null && activity.get() != null && !activity.get().isFinishing()) {
                mActivityStack.remove(activity);
                Activity a = activity.get();
                a.finish();
                activity.clear();
                a = null;
                activity = null;
            }
        }
    }

    public boolean hasActivity(Class<?> cls) {
        WeakReference<Activity> desActivity = null;
        if (mActivityStack == null) return false;
        synchronized (o) {
            for (WeakReference<Activity> activity : mActivityStack) {

                if (activity == null || activity.get() == null || activity.get().isFinishing()) {
                    continue;
                }

                if (activity.get().getClass().equals(cls)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        WeakReference<Activity> desActivity = null;
        if (mActivityStack == null) return;
        synchronized (o) {
            for (WeakReference<Activity> activity : mActivityStack) {

                if (activity == null || activity.get() == null || activity.get().isFinishing()) {
                    continue;
                }

                if (activity.get().getClass().equals(cls)) {
                    desActivity = activity;
                    break;
                }
            }

            if (desActivity != null) killActivity(desActivity);

        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        synchronized (o) {
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i) && mActivityStack.get(i).get() != null && !mActivityStack.get(i).get().isFinishing()) {
                    Activity a = mActivityStack.get(i).get();
                    mActivityStack.get(i).clear();
                    a.finish();
                    a = null;

                }
            }
            mActivityStack.clear();
        }
    }

    // 杀掉除了详情和首页以外的activity
    public void killActivitysExculdeSpecActivitys(ArrayList<Class<?>> clsList) {

        //ArrayList<Class<?>> clsLi synchronized (o) {st = new ArrayList<Class<?>>();
//		clsList.add(ShopIndex.class);
//		clsList.add(ComDetailActivity.class);
        synchronized (o) {
            int clsSize = clsList.size();
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i) && mActivityStack.get(i).get() != null && !mActivityStack.get(i).get().isFinishing()) {
                    Activity a = mActivityStack.get(i).get();

                    boolean isContinue = true;
                    for (int j = 0; j < clsSize; j++) {
                        if (a.getClass().equals(clsList.get(j))) {
                            isContinue = false;
                            break;
                        }
                    }
                    if (!isContinue) continue;

                    mActivityStack.get(i).clear();
                    a.finish();
                    a = null;

                }
            }
        }
        removeNoValidActivity();
        //mActivityStack.clear();
    }

    // 杀掉除了详情和首页以外的activity
    public void killActivitysExculdeHomeActivity() {

        //ArrayList<Class<?>> clsLi synchronized (o) {st = new ArrayList<Class<?>>();
//		clsList.add(ShopIndex.class);
//		clsList.add(ComDetailActivity.class);
        ArrayList<Class<?>> clsList = new ArrayList<Class<?>>();
        clsList.add(MainActivity.class);
        synchronized (o) {
            int clsSize = clsList.size();
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i) && mActivityStack.get(i).get() != null && !mActivityStack.get(i).get().isFinishing()) {
                    Activity a = mActivityStack.get(i).get();

                    boolean isContinue = true;
                    for (int j = 0; j < clsSize; j++) {
                        if (a.getClass().equals(clsList.get(j))) {
                            isContinue = false;
                            break;
                        }
                    }
                    if (!isContinue) continue;

                    mActivityStack.get(i).clear();
                    a.finish();
                    a = null;

                }
            }
        }
        removeNoValidActivity();
        //mActivityStack.clear();
    }


    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            killAllActivity();
//            ActivityManager activityMgr = (ActivityManager) context
//                    .getSystemService(Context.ACTIVITY_SERVICE);
//            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
