package tech.appvalue.newsindia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class NewsDisplayActivity extends Activity implements OnClickListener{

	String name;
	String durl;
	String murl;
	boolean fav;
	RelativeLayout mrl;
	RelativeLayout topban;
	DisplayMetrics metrics;
	int h;
	int w;
	WebView wv;
	ImageView fstar;
	ShareLayout shlayout;
	OnClickListener shlstnr = new OnClickListener(){
		@Override
		public void onClick(View v)
		{
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;
		
		metrics = getResources().getDisplayMetrics();
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		durl = i.getStringExtra("durl");
		murl = i.getStringExtra("murl");
		fav = i.getBooleanExtra("fav", false);
		mrl = new RelativeLayout(this);
		setContentView(mrl);
		mrl.setBackgroundResource(R.drawable.bg);
		mrl.setId(1);
		
		topban = new RelativeLayout(this);
		mrl.addView(topban);
		RelativeLayout.LayoutParams tlp = (LayoutParams) topban.getLayoutParams();
		tlp.height = SizeManager.getDip(42, metrics);
		tlp.width = w;
		topban.setBackgroundResource(R.drawable.tab_bg);
		topban.setId(2);
		
		ImageView bimg = new ImageView(this);
		topban.addView(bimg);
		bimg.setAdjustViewBounds(true);		
		bimg.setBackgroundResource(R.drawable.arrow);
		bimg.setScaleType(ScaleType.CENTER_INSIDE);
		RelativeLayout.LayoutParams bimglp = (LayoutParams)bimg.getLayoutParams();
		bimglp.addRule(RelativeLayout.CENTER_VERTICAL);
		bimglp.height = SizeManager.getDip(40, metrics);
		bimg.setOnClickListener(this);
		bimg.setId(3);
		
		TextView title = new TextView(this);
		topban.addView(title);
		RelativeLayout.LayoutParams tilp = (LayoutParams) title.getLayoutParams();
		tilp.addRule(RelativeLayout.RIGHT_OF,3);
		tilp.addRule(RelativeLayout.LEFT_OF,7);
		tilp.addRule(RelativeLayout.CENTER_VERTICAL);
		title.setTypeface(null,Typeface.BOLD);
		title.setTextSize(16);
		title.setText(name);
		title.setId(4);
		
		fstar = new ImageView(this);
		topban.addView(fstar);
		RelativeLayout.LayoutParams flp = (LayoutParams) fstar.getLayoutParams();
		flp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		flp.addRule(RelativeLayout.CENTER_VERTICAL);
		flp.height=SizeManager.getDip(40, metrics);
		fstar.setScaleType(ScaleType.CENTER_INSIDE);
		setFstarBackground();
		fstar.setOnClickListener(this);
		fstar.setId(5);
		
		ImageView share = new ImageView(this);
		topban.addView(share);
		RelativeLayout.LayoutParams shlp = (LayoutParams) share.getLayoutParams();
		shlp.addRule(RelativeLayout.LEFT_OF,5);
		shlp.addRule(RelativeLayout.CENTER_VERTICAL);
		shlp.height=SizeManager.getDip(40, metrics);
		share.setAdjustViewBounds(true);
		share.setScaleType(ScaleType.CENTER_INSIDE);
		share.setBackgroundResource(R.drawable.share);
		share.setOnClickListener(this);
		share.setId(6);
		
		ImageView rf = new ImageView(this);
		topban.addView(rf);
		RelativeLayout.LayoutParams rflp = (LayoutParams) rf.getLayoutParams();
		rflp.addRule(RelativeLayout.CENTER_VERTICAL);
		rflp.addRule(RelativeLayout.LEFT_OF,6);
		rflp.height = SizeManager.getDip(40, metrics);
		rf.setScaleType(ScaleType.CENTER_INSIDE);
		rf.setBackgroundResource(R.drawable.refesh);
		rf.setOnClickListener(this);
		rf.setId(7);
		
		wv = new WebView(this);
		mrl.addView(wv);
		RelativeLayout.LayoutParams wvlp = (LayoutParams) wv.getLayoutParams();
		wvlp.addRule(RelativeLayout.BELOW,2);
		wvlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		wvlp.width = w;
		wv.setWebViewClient(new WebViewClient());
		WebSettings ws = wv.getSettings();
		//ws.setJavaScriptEnabled(true);
		loadUrl();
		
		shlayout = new ShareLayout(this,shlstnr);
		mrl.addView(shlayout);
		shlayout.setX(w-SizeManager.getDip(10, metrics)-SizeManager.getDip(170, metrics));
		shlayout.setY(SizeManager.getDip(44, metrics));
		shlayout.setVisibility(View.GONE);
	}
	public void setFstarBackground()
	{
		fstar.setBackgroundResource(R.drawable.unfav);
		if(NewsManager.isFavorite(name, this))
		{
			fstar.setBackgroundResource(R.drawable.fav);
		}
	}
	public void loadUrl()
	{
		if(murl!=null)
		wv.loadUrl(murl);
		else
			wv.loadUrl(durl);
	}
	public void refresh()
	{
		wv.loadUrl(wv.getUrl());
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case 3://back button
		{
			super.onBackPressed();
			break;
		}
		case 5://fstar button pressed
		{
			if(NewsManager.isFavorite(name, this))
			{
				NewsManager.deleteFromFavList(name, this);
				setFstarBackground();
			}
			else
			{
				NewsManager.addToFavList(name, this);
				setFstarBackground();
			}
			break;
		}
		case 6://share button pressed
		{	
			Log.d("sharebutton","pressed");		
			if(shlayout.getVisibility()==View.GONE)
			shlayout.setVisibility(View.VISIBLE);	
			else
				shlayout.setVisibility(View.GONE);
			break;
		}
		case 7://refresh button pressed
		{
			refresh();
			break;
		}
		default:
		{
			shlayout.setVisibility(View.GONE);
		}
		}
	}

}
