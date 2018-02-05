package com.zhuhua.simulationlocation.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zhuhua.simulationlocation.R;


/**
 * @author zhuhua
 * @ClassName: BaseActivity
 * @Description:Acitivity基类
 * @date 2018年2月5日 上午11:54:43
 */
public abstract class BaseDialog extends Dialog {
	protected View mView;
	protected Context mContext;

	public BaseDialog(Context context) {
		this(context, R.style.clashDialog);
	}

	public BaseDialog(Context context, int theme) {
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
	 * 
	 * @param inflater
	 * @param container
	 */
	protected abstract int getContentViewId();

	/**
	 * 初始化所有控件
	 */
	protected abstract void InitView();

	/**
	 * 设置事件
	 */
	protected abstract void IniListener();

	/**
	 * 初始化所有数据
	 */
	protected abstract void InitData();

}
