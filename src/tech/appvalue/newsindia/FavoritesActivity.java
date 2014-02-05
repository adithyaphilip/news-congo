package tech.appvalue.newsindia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

public class FavoritesActivity extends Activity implements OnClickListener{
	RelativeLayout mrl;
	int w,h;
	DisplayMetrics metrics;
	RelativeLayout topban;
	ScrollView sc;
	LinearLayout scrl;
	String fvlist[] = {"Aaj Tak"};
	String dlist[] = {"http://aajtak.intoday.in"};
	String mlist[];
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
		 
		TextView title = new TextView(this);
		mrl.addView(title);
		RelativeLayout.LayoutParams tilp = (LayoutParams) title.getLayoutParams();
		tilp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tilp.addRule(RelativeLayout.BELOW,2);
		tilp.setMargins(0, SizeManager.getDip(20, metrics), 0, SizeManager.getDip(20, metrics));
		title.setText("My favorites");
		title.setTextSize(22);
		title.setTypeface(null,Typeface.BOLD);
		title.setId(7);
		
		sc= new ScrollView(this);
		mrl.addView(sc);
		RelativeLayout.LayoutParams sclp = (LayoutParams) sc.getLayoutParams();
		sclp.addRule(RelativeLayout.BELOW,7);
		sclp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		sclp.width=w;
		sc.setId(8);
		
		scrl = new LinearLayout(this);
		sc.addView(scrl);
		scrl.setOrientation(LinearLayout.VERTICAL);
		scrl.setId(9);
		
		generateNewspaperList();
	}
	public void generateNewspaperList()
	{
		scrl.removeAllViews();
		ImageView div1 = new ImageView(this);
		scrl.addView(div1);
		LinearLayout.LayoutParams d1lp = (LinearLayout.LayoutParams)div1.getLayoutParams();
		d1lp.width = w;
		div1.setBackgroundResource(R.drawable.divider);		
		fvlist = NewsManager.getFavList(this);
		for(int i =0;i<fvlist.length;i++)
		{
			scrl.addView(getNewspaperLayout(fvlist[i],i));
			ImageView div = new ImageView(this);
			scrl.addView(div);
			LinearLayout.LayoutParams dlp = (LinearLayout.LayoutParams)div.getLayoutParams();
			dlp.width = w;
			div.setBackgroundResource(R.drawable.divider);
		}
	}
	public RelativeLayout getNewspaperLayout(String nString, int index)
	{
		OnClickListener tllstnr = new OnClickListener(){

			@Override
			public void onClick(View v) {
				int i = v.getId()%1000;
				Intent data = new Intent(getBaseContext(),NewsDisplayActivity.class);
				data.putExtra("name","Aaj Tak").putExtra("durl","http://aajtak.intoday.in");
				data.putExtra("name",fvlist[i]);
				startActivity(data);                           
			}			
		};
		RelativeLayout temp = new RelativeLayout(this);
		temp.setBackgroundResource(R.drawable.nlistbgxml);
		temp.setId(9*1000+index);
		temp.setOnClickListener(tllstnr);
		
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
		
		TextView tv = new TextView(this);
		temp.addView(tv);	
		RelativeLayout.LayoutParams tvlp = (LayoutParams) tv.getLayoutParams();
		tvlp.addRule(RelativeLayout.CENTER_VERTICAL);
		tvlp.addRule(RelativeLayout.RIGHT_OF,bullet.getId());
		tvlp.setMargins(0, SizeManager.getDip(10, metrics), 0, SizeManager.getDip(10, metrics));
		tv.setText(nString);
		tv.setTextSize(22);

		LinearLayout.LayoutParams tlp = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
		temp.setLayoutParams(tlp);		
		
		return temp;
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
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
}
