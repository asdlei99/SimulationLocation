package com.zhuhua.simulationlocation.hook;

import android.util.Log;

import com.zhuhua.simulationlocation.data.Constant;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lin on 2017/7/22.
 */

public class HookTest implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        Log.e(HookUtils.TAG, loadPackageParam.packageName);
        LuckyMoneyHook.hook(loadPackageParam);
        if (!PkgConfig.packages.contains(loadPackageParam.packageName) && !PkgConfig.pkg_dingding.equals(loadPackageParam.packageName))
            return;
        XSharedPreferences preferences = new XSharedPreferences("com.zhuhua.simulationlocation", "share_location_data");
        if (PkgConfig.pkg_dingding.equals(loadPackageParam.packageName)) {
            double latitude = Double.parseDouble(preferences.getString(Constant.Lat, "34.752600"));
            double longitude = Double.parseDouble(preferences.getString(Constant.Lng, "113.662000"));
            int lac = preferences.getInt(Constant.lac, -1);
            int cid = preferences.getInt(Constant.cid, -1);
            HookUtils.HookAndChange(loadPackageParam, latitude, longitude, lac, cid);
        } else {
            double latitude = Double.parseDouble(preferences.getString(Constant.Lat, "34.752600"));
            double longitude = Double.parseDouble(preferences.getString(Constant.Lng, "113.662000"));
            HookUtils.HookAndChange(loadPackageParam, latitude, longitude);
        }
    }
}
