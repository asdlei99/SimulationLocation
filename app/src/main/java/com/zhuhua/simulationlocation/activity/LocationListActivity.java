package com.zhuhua.simulationlocation.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.zhuhua.simulationlocation.R;
import com.zhuhua.simulationlocation.adatper.LocationAdapter;
import com.zhuhua.simulationlocation.base.BaseActivity;
import com.zhuhua.simulationlocation.data.Constant;
import com.zhuhua.simulationlocation.db.AppSharePreference;
import com.zhuhua.simulationlocation.interfacelistener.LocationAdapterListener;
import com.zhuhua.simulationlocation.model.LocationXYModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/2.
 */

public class LocationListActivity  extends BaseActivity implements View.OnClickListener {

    List<LocationXYModel> locationXYModelList;
    ListView lv_location;
    LocationAdapter locationAdapter;
    @Override
    protected Object getContentViewId() {
        return R.layout.activity_locationlist;
    }

    @Override
    protected void IniView() {
        toolbar.setTitle("获取定位列表");// 标题的文字需在setSupportActionBar之前，不然会无效
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv_location=(ListView)findViewById(R.id.lv_location);


    }

    @Override
    protected void IniLister() {
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    protected void IniData() {
        locationXYModelList=new ArrayList<LocationXYModel>();
        Map<String,?> DataList= AppSharePreference.getAll(mContext);
        Log.d("location",this.toString()+"======================InitData===DataList.Size:"+DataList.size());
        for(String key:DataList.keySet())
        {
            String xy=(String)DataList.get(key);

            Log.d("location","title="+key+";content="+xy);
            String[] strList=xy.split(",");

            String lat=strList[0];
            String lng=strList[1];

            locationXYModelList.add(new LocationXYModel(key,lat,lng));

        }

        locationAdapter=new LocationAdapter(mContext);
        locationAdapter.setData(locationXYModelList);
        locationAdapter.setLocationAdapterListener(new LocationAdapterListener() {
            @Override
            public void onSelecte(String title,String lat, String lng) {

                Intent result_intent=new Intent();
                result_intent.putExtra(Constant.Title,title);
                result_intent.putExtra(Constant.Lat,lat);
                result_intent.putExtra(Constant.Lng,lng);
                setResult(RESULT_OK,result_intent);
                thisFinish();
            }
        });
        lv_location.setAdapter(locationAdapter);

    }

    @Override
    protected void thisFinish() {



        this.finish();


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case -1:
                thisFinish();
                break;
        }
    }
}
