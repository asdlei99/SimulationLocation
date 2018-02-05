package com.zhuhua.simulationlocation.adatper;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.zhuhua.simulationlocation.R;
import com.zhuhua.simulationlocation.interfacelistener.LocationAdapterListener;
import com.zhuhua.simulationlocation.model.LocationXYModel;

import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class LocationAdapter implements ListAdapter {

    List<LocationXYModel> locationXYModelList;
    Context mContext;
    LocationAdapterListener locationAdapterListener;
    public LocationAdapter(Context mContext)
    {
        this.mContext=mContext;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }
    public void setData(List<LocationXYModel> locationXYModelList)
    {
        this.locationXYModelList=locationXYModelList;
    }

    @Override
    public int getCount() {
        return locationXYModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationXYModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LocationXYModel model=locationXYModelList.get(position);

        HolderView holderView;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.adapter_locationitem,null);
            holderView=new HolderView();
            holderView.bt_select=(Button) convertView.findViewById(R.id.button_select);
            holderView.tv_location=(TextView) convertView.findViewById(R.id.textView_location);
            holderView.tv_title=(TextView) convertView.findViewById(R.id.textView_title);
            convertView.setTag(holderView);

        }
        else
        {
            holderView=(HolderView)convertView.getTag();
        }
        if(model!=null)
        {
            holderView.tv_title.setText(model.getTitle());
            holderView.tv_location.setText(model.getCurLatlng().latitude+","+model.getCurLatlng().longitude);
            holderView.bt_select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(locationAdapterListener!=null)
                    locationAdapterListener.onSelecte(model.getTitle(),model.getCurLatlng().latitude+"",model.getCurLatlng().longitude+"");
                }
            });
        }


        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    class HolderView{
        TextView tv_title;
        TextView tv_location;
        Button bt_select;
    }
    public void setLocationAdapterListener(LocationAdapterListener listener)
    {
        this.locationAdapterListener=listener;
    }

}
