package com.zhuhua.simulationlocation.model;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2018/2/2.
 */

public class LocationXYModel {
    String title;
    LatLng curLatlng;

    public LocationXYModel(String title, String lat, String lng)
    {
        this.title=title;
        curLatlng=new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getCurLatlng() {
        return curLatlng;
    }

    public void setCurLatlng(LatLng curLatlng) {
        this.curLatlng = curLatlng;
    }
}
