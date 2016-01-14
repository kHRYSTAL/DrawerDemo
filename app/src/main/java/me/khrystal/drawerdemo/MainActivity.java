package me.khrystal.drawerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private WrapSlidingDrawer mDrawer;
	private Boolean flag=false;
	private LinearLayout linearLayout;
	private TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		linearLayout=(LinearLayout)findViewById(R.id.handle);
		mDrawer=(WrapSlidingDrawer)findViewById(R.id.slidingdrawer);
		tv=(TextView)findViewById(R.id.tv);

		mDrawer.setOnDrawerOpenListener(new WrapSlidingDrawer.OnDrawerOpenListener()
		{
			@Override
			public void onDrawerOpened() {
				flag=true;
			}

		});

		mDrawer.setOnDrawerCloseListener(new WrapSlidingDrawer.OnDrawerCloseListener(){

			@Override
			public void onDrawerClosed() {
				flag=false;
			}

		});

		mDrawer.setOnDrawerScrollListener(new WrapSlidingDrawer.OnDrawerScrollListener(){

			@Override
			public void onScrollEnded() {
				tv.setText("结束拖动");
			}

			@Override
			public void onScrollStarted() {
				tv.setText("开始拖动");
			}

		});

	}


}
