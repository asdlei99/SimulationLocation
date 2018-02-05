package com.zhuhua.simulationlocation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuhua.simulationlocation.R;
import com.zhuhua.simulationlocation.db.AppSharePreference;


/**
 * Created by zhuhua on 2018/2/2.
 */

public class LocationDialog extends Dialog implements View.OnClickListener {

    protected View mView;
    protected Context mContext;
    EditText et_title, et_location;
    TextView tv_title, tv_location;
    Button bt_location_save, bt_location_cancel;
    String lat = "0";
    String lng = "0";

    public LocationDialog(Context context) {
        this(context, R.style.clashDialog);
    }

    public LocationDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        mView = mLayoutInflater.inflate(getContentViewId(), null);
        this.setContentView(mView);
        InitView();
        IniListener();
        InitData();

    }

    /**
     * 初始化view变量,用于显示
     */
    protected int getContentViewId() {
        return R.layout.dialog_location;
    }

    /**
     * 初始化所有控件
     */
    protected void InitView() {
        et_title = (EditText) mView.findViewById(R.id.et_title);
        et_location = (EditText) mView.findViewById(R.id.et_location);

        tv_title = (TextView) mView.findViewById(R.id.tv_title);
        tv_location = (TextView) mView.findViewById(R.id.tv_location);

        bt_location_save = (Button) mView.findViewById(R.id.bt_location_save);
        bt_location_cancel = (Button) mView.findViewById(R.id.bt_location_cancel);

    }

    /**
     * 设置事件
     */
    protected void IniListener() {
        bt_location_save.setOnClickListener(this);
        bt_location_cancel.setOnClickListener(this);

    }

    /**
     * 初始化所有数据
     */
    protected void InitData() {
        et_location.setText(this.lat + "," + this.lng);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_location_save) {
            if (isExites()) {
                AppSharePreference.put(mContext, et_title.getText().toString(), et_location.getText().toString());
                Toast.makeText(mContext, "保存成功", Toast.LENGTH_SHORT);
                this.hide();

            } else {
                Toast.makeText(mContext, "不能为空", Toast.LENGTH_SHORT);
            }
        }
        if (v.getId() == R.id.bt_location_cancel) {
            this.hide();
        }
    }

    private boolean isExites() {
        if (TextUtils.isEmpty(et_title.getText().toString())) {
            return false;
        }
        if (TextUtils.isEmpty(et_location.getText().toString())) {
            return false;
        }
        return true;
    }

    public void setLocationData(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;


    }
}
