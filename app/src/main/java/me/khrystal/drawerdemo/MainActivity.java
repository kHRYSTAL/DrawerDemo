package me.khrystal.drawerdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements SlidingDrawer.OnDrawerOpenListener, SlidingDrawer.OnDrawerCloseListener, SlidingDrawer.OnDrawerScrollListener, OnClickListener {

	@Bind(R.id.slidingdrawer)
	WrapSlidingDrawer mDrawer;

	@Bind(R.id.handle)
	LinearLayout mDrawerHandle;

	@Bind(R.id.login_login)
	TextView loginButton;

	@Bind(R.id.login_register)
	TextView registerButton;

	@Bind(R.id.indicator_login)
	View indicatorLogin;

	@Bind(R.id.indicator_register)
	View indicatorRegister;

	@Bind(R.id.divider)
	View divider;

	public int loginToggle;
	public int registerToggle;
	public boolean isOpenLogin;
	public boolean isOpenRegister;
	private int currentFragmentIndex=-1;
	private SparseArray<Fragment> fragments = new SparseArray<Fragment>(2);




	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		EventBus.getDefault().register(this);
		mDrawer.setOnDrawerOpenListener(this);
		mDrawer.setOnDrawerCloseListener(this);
		mDrawer.setOnDrawerScrollListener(this);
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		switchFragment(0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
		EventBus.getDefault().unregister(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.login_login:
				checkDrawerOpen(R.id.login_login);
				loginButton.setTextColor(Color.parseColor("#999999"));
				registerButton.setTextColor(Color.parseColor("#333333"));
				indicatorLogin.setVisibility(View.VISIBLE);
				indicatorRegister.setVisibility(View.INVISIBLE);
				switchFragment(0);
				break;
			case R.id.login_register:
				checkDrawerOpen(R.id.login_register);
				loginButton.setTextColor(Color.parseColor("#333333"));
				registerButton.setTextColor(Color.parseColor("#999999"));
				indicatorLogin.setVisibility(View.INVISIBLE);
				indicatorRegister.setVisibility(View.VISIBLE);
				switchFragment(1);
				break;
		}
	}


	/**
	 * 如果是同一个按钮重复点击 执行Drawer开关
	 * 否则不执行
	 * 奇数为开 偶数为关
	 * @param resId
	 */
	private void checkDrawerOpen(int resId) {
		switch (resId){
			case R.id.login_login:
				loginToggle++;
				if (loginToggle%2==1){
					if (registerToggle%2==1){
						registerToggle--;
					}else {
						mDrawer.animateToggle();
					}
				}else {
					mDrawer.animateClose();
				}
				break;
			case R.id.login_register:
				registerToggle++;
				if (registerToggle%2==1){
					if (loginToggle%2==1){
						loginToggle--;
					}else {
						mDrawer.animateToggle();
					}
				}else {
					mDrawer.animateClose();
				}
				break;
		}
	}

	private void switchFragment(int id) {
		if (currentFragmentIndex == id) {
			return;
		}
		Fragment oldFragment = fragments.get(currentFragmentIndex);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (oldFragment != null)
			ft.hide(oldFragment);

		currentFragmentIndex = id;
		Fragment newFragment = fragments.get(currentFragmentIndex);
		if (newFragment == null) {
			switch (currentFragmentIndex) {
				case 0:
					newFragment = new BlankFragment1();
					break;
				case 1:
					newFragment = new BlankFragment2();
					break;
			}
			fragments.put(currentFragmentIndex,newFragment);
		}

		if (newFragment != null) {
			if (!newFragment.isAdded()) {
				ft.add(R.id.content, newFragment, String.valueOf(currentFragmentIndex));
			}
			ft.show(newFragment);
		}
		ft.commitAllowingStateLoss();
	}

	public void onEventMainThread(Intent intent) {
		if (intent.getAction().equals("action_switch")){
			switch(currentFragmentIndex){
				case 0:
					registerButton.performClick();
					break;
				case 1:
					loginButton.performClick();
					break;
			}
		}
	}

	/**
	 * implements method
	 */
	@Override
	public void onDrawerOpened() {

	}

	@Override
	public void onDrawerClosed() {
		loginButton.setTextColor(Color.parseColor("#333333"));
		registerButton.setTextColor(Color.parseColor("#333333"));
		indicatorLogin.setVisibility(View.INVISIBLE);
		indicatorRegister.setVisibility(View.INVISIBLE);

	}

	@Override
	public void onScrollStarted() {

	}

	@Override
	public void onScrollEnded() {

	}


}
