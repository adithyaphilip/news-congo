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
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class NewspaperEditorActivity extends Activity implements OnClickListener{
	RelativeLayout mrl;
	ScrollView sc;
	LinearLayout scrl;
	RelativeLayout topban;
	int w,h;
	DisplayMetrics metrics;
	String nlist[];
	String dlist[];
	String mlist[];
	final int delreqcode = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;
		
		metrics = getResources().getDisplayMetrics();
				
		mrl = new RelativeLayout(this); 
		setContentView(mrl);
		mrl.setBackgroundResource(R.drawable.bg);
		mrl.setId(1);
		
		topban = new RelativeLayout(this);
		mrl.addView(topban);
		RelativeLayout.LayoutParams tlp = (LayoutParams) topban.getLayoutParams();
		tlp.height = SizeManager.getDip(44, metrics);
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
		
		ImageView titleimg = new ImageView(this);
		topban.addView(titleimg);
		RelativeLayout.LayoutParams timglp = (LayoutParams) titleimg.getLayoutParams();
		timglp.addRule(RelativeLayout.CENTER_VERTICAL);
		timglp.addRule(RelativeLayout.RIGHT_OF,3);
		timglp.addRule(RelativeLayout.LEFT_OF,6);
		titleimg.setAdjustViewBounds(true);
		titleimg.setScaleType(ScaleType.CENTER_INSIDE);
		//titleimg.setImageResource
		titleimg.setId(4);
		
		ImageView add = new ImageView(this);
		topban.addView(add);
		RelativeLayout.LayoutParams adlp = (LayoutParams) add.getLayoutParams();
		adlp.addRule(RelativeLayout.CENTER_VERTICAL);
		adlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		adlp.height = SizeManager.getDip(38, metrics);
		adlp.width = adlp.height;
		add.setAdjustViewBounds(true);
		add.setImageResource(R.drawable.addnews);
		add.setOnClickListener(this);
		add.setId(5);
		
		ImageView rf = new ImageView(this);
		topban.addView(rf);
		RelativeLayout.LayoutParams rflp = (LayoutParams) rf.getLayoutParams();
		rflp.addRule(RelativeLayout.CENTER_VERTICAL);
		rflp.addRule(RelativeLayout.LEFT_OF,5);
		rflp.height=SizeManager.getDip(38, metrics);
		rflp.width = rflp.height;
		rf.setAdjustViewBounds(true);
		rf.setImageResource(R.drawable.refesh);
		rf.setOnClickListener(this);
		rf.setId(6);
		
		sc = new ScrollView(this);
		mrl.addView(sc);
		RelativeLayout.LayoutParams sclp = (LayoutParams) sc.getLayoutParams();
		sclp.addRule(RelativeLayout.BELOW,2);
		sclp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		sclp.width = w;
		
		scrl = new LinearLayout(this);
		sc.addView(scrl);
		scrl.setOrientation(LinearLayout.VERTICAL);
		
		generateNewspaperList();
	}
	public void generateNewspaperList()
	{
		scrl.removeAllViews();
		ImageView div1 = new ImageView(this);
		scrl.addView(div1);
		LinearLayout.LayoutParams d1lp = (LinearLayout.LayoutParams)div1.getLayoutParams();
		d1lp.width = w;
		d1lp.topMargin=SizeManager.getDip(22, metrics);
		div1.setBackgroundResource(R.drawable.divider);
		nlist = NewsManager.getInitialNewsList();
		for(int i =0;i<nlist.length;i++)
		{
			Log.d("loopgen",""+nlist.length);
			scrl.addView(getNewspaperLayout(nlist[i],i));
			ImageView div = new ImageView(this);
			scrl.addView(div);
			LinearLayout.LayoutParams dlp = (LinearLayout.LayoutParams)div.getLayoutParams();
			dlp.width = w;
			div.setBackgroundResource(R.drawable.divider);
		}
	}
	public RelativeLayout getNewspaperLayout(String nString, int index)
	{
		
		RelativeLayout temp = new RelativeLayout(this);
		temp.setBackgroundResource(R.drawable.nlistbgxml);
		temp.setId(9*1000+index);
		
		ImageView bullet = new ImageView(this);
		temp.addView(bullet);
		RelativeLayout.LayoutParams blp = (LayoutParams) bullet.getLayoutParams();
		blp.leftMargin=SizeManager.getDip(20, metrics);
		blp.rightMargin=SizeManager.getDip(20, metrics);
		blp.height=SizeManager.getDip(10, metrics);
		blp.width=SizeManager.getDip(10, metrics);
		blp.addRule(RelativeLayout.CENTER_VERTICAL);
		bullet.setImageResource(R.drawable.plain);
		bullet.setId(8000+index);
		
		final TextView tv = new TextView(this);
		temp.addView(tv);	
		RelativeLayout.LayoutParams tvlp = (LayoutParams) tv.getLayoutParams();
		tvlp.addRule(RelativeLayout.CENTER_VERTICAL);
		tvlp.addRule(RelativeLayout.RIGHT_OF,bullet.getId());
		tvlp.addRule(RelativeLayout.LEFT_OF,7000+index);
		tvlp.setMargins(0, SizeManager.getDip(10, metrics), 0, SizeManager.getDip(10, metrics));
		tv.setText(nString);
		tv.setTextSize(22);
		

		final ImageView tsh = new ImageView(this);
		temp.addView(tsh);
		RelativeLayout.LayoutParams tshlp = (LayoutParams) tsh.getLayoutParams();
		tshlp.addRule(RelativeLayout.CENTER_VERTICAL);
		tshlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		tshlp.height=SizeManager.getDip(44, metrics);
		tshlp.setMargins(SizeManager.getDip(10, metrics), 0, SizeManager.getDip(10, metrics), 0);
		tsh.setId(6000+index);
		tsh.setImageResource(R.drawable.delete);
		tsh.setAdjustViewBounds(true);
		tsh.setScaleType(ScaleType.CENTER_INSIDE);
		
		OnClickListener dellstnr = new OnClickListener(){
			@Override
			public void onClick(View v)
			{
				onDeleteButtonPressed(tv);						 
			}
		};
		
		tsh.setOnClickListener(dellstnr);
		
		final ImageView fav = new ImageView(this);
		temp.addView(fav);
		RelativeLayout.LayoutParams favlp = (LayoutParams) fav.getLayoutParams();
		favlp.addRule(RelativeLayout.CENTER_VERTICAL);
		favlp.addRule(RelativeLayout.LEFT_OF,6000+index);
		favlp.height = SizeManager.getDip(44, metrics);
		favlp.width = SizeManager.getDip(50, metrics);
		favlp.setMargins(SizeManager.getDip(10, metrics), 0, SizeManager.getDip(10, metrics), 0);
		fav.setId(7000+index);
		setFstarBackground(fav,nString);
		fav.setAdjustViewBounds(true);
		fav.setScaleType(ScaleType.CENTER_INSIDE);

		OnClickListener favlstnr = new OnClickListener(){
			@Override
			public void onClick(View v)
			{
				onFstarButtonPressed(fav,tv);
			}
		};
		
		fav.setOnClickListener(favlstnr);
		
		LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		temp.setLayoutParams(tlp);	
		return temp;
	}
	public void setFstarBackground(ImageView fav,String name)
	{
		fav.setImageResource(R.drawable.unfav);
		if(NewsManager.isFavorite(name, this))
		{
			fav.setImageResource(R.drawable.fav);
		}
	}
	public void onFstarButtonPressed(ImageView fav, TextView tv)
	{
		String name = tv.getText().toString();
		if(NewsManager.isFavorite(name, this))
		{
			NewsManager.deleteFromFavList(name, this);
			setFstarBackground(fav, name);
		}
		else
		{
			NewsManager.addToFavList(name, this);
			setFstarBackground(fav, name);
		}
	}
	public void onDeleteButtonPressed(TextView tv)
	{
		String name = tv.getText().toString();
		onDeleteButtonPressed(name);
	}
	public void onDeleteButtonPressed(String name)
	{
		Intent data = new Intent(getBaseContext(),DeleteNewspaperActivity.class);
		data.putExtra("newspaper",name);
		startActivityForResult(data,delreqcode);	
	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case 3:
		{
			super.onBackPressed();
			break;
		}
		case 5:
		{
			break;
		}
		case 6:
		{
			generateNewspaperList();
			break;
		}
		}
	}
	@Override
	public void onActivityResult(int reqCode, int resCode, Intent data)
	{
		if(reqCode==delreqcode)
		{
			if(resCode==RESULT_OK)
			{
				if(data.getBooleanExtra("deleted", false))
				{
					generateNewspaperList();
				}
			}
		}
	}
}
