package tech.appvalue.newsindia;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	int w;
	int h;
	DisplayMetrics metrics;
	RelativeLayout mrl;
	RelativeLayout topban;
	RelativeLayout tb2;
	LinearLayout scrl;
	ScrollView sc;
	String nlist[];
	String dlist[];
	String mlist[];
	boolean favlist[];
	SortPopLayout sl;
	OnClickListener sllstnr = new OnClickListener(){
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
		
		mrl = new RelativeLayout(this); 
		setContentView(mrl);
		mrl.setBackgroundResource(R.drawable.bg);
		mrl.setId(1);
		
		topban = new RelativeLayout(this);
		mrl.addView(topban);
		RelativeLayout.LayoutParams tlp = (LayoutParams)topban.getLayoutParams();
		tlp.height = SizeManager.getDip(44, metrics);
		topban.setBackgroundResource(R.drawable.tab_bg);
		topban.setOnClickListener(this);
		topban.setId(2);
		
		ImageView timg = new ImageView(this);
		topban.addView(timg);
		RelativeLayout.LayoutParams timglp = (LayoutParams)timg.getLayoutParams();
		timglp.addRule(RelativeLayout.CENTER_VERTICAL);
		//timg.setImageResource(resId);
		timg.setAdjustViewBounds(true);
		timg.setScaleType(ScaleType.CENTER_INSIDE);
		//timglp.height = SizeManager.getDip(36, metrics);
		timg.setId(3);
		
		ImageView  fav = new ImageView (this);
		topban.addView(fav);
		RelativeLayout.LayoutParams favlp = (LayoutParams)fav.getLayoutParams();
		favlp.addRule(RelativeLayout.CENTER_VERTICAL);
		favlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		favlp.height=SizeManager.getDip(28, metrics);
		fav.setAdjustViewBounds(true);
		fav.setScaleType(ScaleType.CENTER_INSIDE);
		fav.setImageResource(R.drawable.fav_list);
		fav.setOnClickListener(this);
		fav.setId(4);
		
		ImageView edit = new ImageView (this);
		topban.addView(edit);
		RelativeLayout.LayoutParams elp = (LayoutParams) edit.getLayoutParams();
		elp.addRule(RelativeLayout.CENTER_VERTICAL);
		elp.addRule(RelativeLayout.LEFT_OF,4);
		elp.height=SizeManager.getDip(28, metrics);
		edit.setAdjustViewBounds(true);
		edit.setScaleType(ScaleType.CENTER_INSIDE);
		edit.setImageResource(R.drawable.edit);
		edit.setOnClickListener(this);
		edit.setId(5);
		
		ImageView  add = new ImageView (this);
		topban.addView(add);
		RelativeLayout.LayoutParams alp = (LayoutParams)add.getLayoutParams();
		alp.addRule(RelativeLayout.CENTER_VERTICAL);
		alp.addRule(RelativeLayout.LEFT_OF,5);
		alp.height=SizeManager.getDip(26, metrics);
		add.setAdjustViewBounds(true);
		add.setScaleType(ScaleType.CENTER_INSIDE);
		add.setImageResource(R.drawable.addnews);
		add.setOnClickListener(this);
		add.setId(6);
		
		ImageView  rf = new ImageView (this);
		topban.addView(rf);
		RelativeLayout.LayoutParams rflp = (LayoutParams)rf.getLayoutParams();
		rflp.addRule(RelativeLayout.CENTER_VERTICAL);
		rflp.addRule(RelativeLayout.LEFT_OF,6);	
		rflp.height=SizeManager.getDip(26, metrics);
		rf.setAdjustViewBounds(true);
		rf.setScaleType(ScaleType.CENTER_INSIDE);
		rf.setImageResource(R.drawable.refesh);
		rf.setOnClickListener(this);
		rf.setId(7);
		
		RelativeLayout tb2 = new RelativeLayout(this);
		mrl.addView(tb2);
		RelativeLayout.LayoutParams t2lp = (LayoutParams) tb2.getLayoutParams();
		t2lp.addRule(RelativeLayout.BELOW,2);
		t2lp.width =w;
		t2lp.height = SizeManager.getDip(60, metrics);		
		tb2.setOnClickListener(this);
		tb2.setId(8);
		
		ImageButton all = new ImageButton(this);
		tb2.addView(all);
		RelativeLayout.LayoutParams allp = (LayoutParams) all.getLayoutParams();
		allp.addRule(RelativeLayout.CENTER_VERTICAL);
		allp.width=SizeManager.getDip(100, metrics);
		all.setBackgroundResource(R.drawable.all);
		all.setAdjustViewBounds(true);
		all.setScaleType(ScaleType.CENTER_INSIDE);
		all.setOnClickListener(this);
		all.setId(9);
		
		TextView type = new TextView(this);
		tb2.addView(type);
		RelativeLayout.LayoutParams tylp  = (LayoutParams) type.getLayoutParams();
		tylp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		tylp.addRule(RelativeLayout.CENTER_VERTICAL);
		type.setText("");
		type.setTypeface(null,Typeface.BOLD);
		type.setId(10);
		
		ImageButton sort = new ImageButton(this);
		tb2.addView(sort);
		RelativeLayout.LayoutParams slp = (LayoutParams) sort.getLayoutParams();
		slp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		slp.addRule(RelativeLayout.CENTER_VERTICAL);
		slp.width=SizeManager.getDip(100, metrics);
		sort.setScaleType(ScaleType.CENTER_INSIDE);
		sort.setBackgroundResource(R.drawable.sort);
		sort.setOnClickListener(this);
		sort.setId(11);
		
		sc = new ScrollView(this);
		mrl.addView(sc);
		RelativeLayout.LayoutParams sclp = (LayoutParams) sc.getLayoutParams();
		sclp.width = w;
		sclp.addRule(RelativeLayout.BELOW,8);
		sclp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		sc.setId(12);
		
		scrl = new LinearLayout(this);
		sc.addView(scrl);
		scrl.setOrientation(LinearLayout.VERTICAL);
		scrl.setId(13);
		
		generateNewspaperList();
		
		sl= new SortPopLayout(getBaseContext(),sllstnr);
		mrl.addView(sl);
		sl.setX(w-sl.getLayoutParams().width);
		sl.setY(SizeManager.getDip(44, metrics)+SizeManager.getDip(50, metrics));
		sl.setVisibility(View.GONE);
		
		if(NewsManager.getFavListInputStream(this)==null)
		{
			setFirstTime();
			initialiseFiles();
			//TEST
			NewsManager.addNewspaper("Aaj Tak","http://aajtak.intoday.in","","Regional",this);
		}
	}
	public boolean isFirstTime()
	{
		boolean result=false;
		SharedPreferences sf = getSharedPreferences("used", 0);
		result = sf.getBoolean("used",false);
		return !result;
	}
	public void setFirstTime()
	{
		SharedPreferences sf = getSharedPreferences("used", 0);
		SharedPreferences.Editor ed = sf.edit();
		ed.putBoolean("used", true);
		ed.commit();
	}
	@Override
	public void onClick(View v)
	{
		switch(v.getId()){
		case 4://fav button
		{
			Intent i =new Intent(this,FavoritesActivity.class);
			startActivity(i);
			break;
		}
		case 5://edit button
		{
			Intent i = new Intent(this,NewspaperEditorActivity.class);
			startActivity(i);
			break;
		}
		case 6://add button
		{
			break;
		}
		case 7://rf button
		{
			generateNewspaperList();
			break;
		}
		case 11://sort button
		{
			if(sl.getVisibility()!=View.GONE)
				hideSortLayout();
			else
			showSortLayout();
			break;
		}
		default:
		{
			hideSortLayout();
			break;
		}
		}
	}
	public void generateNewspaperList()
	{
		scrl.removeAllViews();
		ImageView div1 = new ImageView(this);
		scrl.addView(div1);
		LinearLayout.LayoutParams d1lp = (LinearLayout.LayoutParams)div1.getLayoutParams();
		d1lp.width = w;
		div1.setBackgroundResource(R.drawable.divider);
		nlist = NewsManager.getInitialNewsList();
		for(int i =0;i<nlist.length;i++)
		{
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
		OnClickListener tllstnr = new OnClickListener(){

			@Override
			public void onClick(View v) {
				int i = v.getId()%1000;
				Intent data = new Intent(getBaseContext(),NewsDisplayActivity.class);
				data.putExtra("name","Aaj Tak").putExtra("durl","http://aajtak.intoday.in");
				//data.putExtra("name",nlist[i]).putExtra("durl",dlist[i]).putExtra("murl",mlist[i]).putExtra("fav",favlist[i]);
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
	public void showSortLayout()
	{
		
		sl.setVisibility(View.VISIBLE);
	}
	public void hideSortLayout()
	{

		sl.setVisibility(View.GONE);
	}
	public void initialiseFiles()
	{
		NewsManager.writeNewsList(" ", this);
		NewsManager.writeFavList(" ", this);
		NewsManager.writeDList(" ", this);
	}
}
