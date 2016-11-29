package com.livingwater.comment.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.livingwater.comment.R;
import com.livingwater.comment.common.AppManager;


public abstract class BaseSingleFragmentActivity extends FragmentActivity {
	private Context mContext;
	private boolean mFullScreen = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContext(this);
		if (true == isFullScreen()) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);

		// 修改状态栏颜色，4.4+生效
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus();
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.status_bar_bg);//通知栏所需颜色

		setContentView(R.layout.activity_fragment);
		
		// load fragment
		loadFragment();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 结束Activity从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	protected abstract Fragment createFragment();
	
	/**
	 * return true, fragment was loaded in real-time.Otherwise,fragment was loaded non-real time.
	 * @return
	 */
	protected abstract boolean isRealTimeLoadFragment();

	public Context getContext() {
		return mContext;
	}

	public void setContext(Context context) {
		mContext = context;
	}

	public boolean isFullScreen() {
		return mFullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		mFullScreen = fullScreen;
	}

	private void loadFragment() {
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if (false == isRealTimeLoadFragment()) {
			if (null == fragment) {
				fragment = createFragment();
				fm.beginTransaction().add(R.id.fragment_container, fragment)
						.commit();
			}
		} else {
			fragment = createFragment();
			fm.beginTransaction().replace(R.id.fragment_container, fragment)
					.commit();
		}
	}
	@TargetApi(19)
	protected void setTranslucentStatus() {
		Window window = getWindow();
		// Translucent status bar
		window.setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// Translucent navigation bar
//        window.setFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	}
}
