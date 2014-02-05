package tech.appvalue.newsindia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {
	RelativeLayout mrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mrl = new RelativeLayout(this);
		setContentView(mrl);
		
		ImageView iv = new ImageView(this);
		mrl.addView(iv);
		RelativeLayout.LayoutParams ivlp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
		iv.setLayoutParams(ivlp);
		iv.setImageResource(R.drawable.main);
		iv.setScaleType(ScaleType.CENTER_CROP);
		Handler mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg)
			{
				Intent i = new Intent(getBaseContext(),MainActivity.class);
				startActivity(i);
				onBackPressed();
			}
		};
		mHandler.sendMessageDelayed(mHandler.obtainMessage(), 1000);
		}

}
