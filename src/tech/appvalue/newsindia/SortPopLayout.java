package tech.appvalue.newsindia;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SortPopLayout extends LinearLayout{

	Context context;
	DisplayMetrics metrics;
	public SortPopLayout(Context c, OnClickListener lstn) {
		super(c);
		this.context=c;
		metrics = context.getResources().getDisplayMetrics();
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams((int) Math.floor(SizeManager.getDip(170, metrics)),SizeManager.getDip(336, metrics));
		this.setOrientation(LinearLayout.VERTICAL);
		//need to enter height in rlp
		
		this.setLayoutParams(rlp);
		this.setBackgroundResource(R.drawable.sort_bg);
		addCategories();
	}
	public void addCategories() 
	{
		ImageButton eng = new ImageButton(context);
		eng.setBackgroundResource(R.drawable.sortbtnxml);
		eng.setImageResource(R.drawable.en);
		LinearLayout.LayoutParams englp = new LinearLayout.LayoutParams((int) Math.floor(SizeManager.getDip(160, metrics)),SizeManager.getDip(50, metrics));
		englp.setMargins(SizeManager.getDip(5, metrics),0,SizeManager.getDip(5, metrics),0);
		eng.setLayoutParams(englp);
		englp.topMargin=SizeManager.getDip(36, metrics);
		eng.setAdjustViewBounds(true);
		eng.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(eng);
		
		LinearLayout.LayoutParams clp = new LinearLayout.LayoutParams((int) Math.floor(SizeManager.getDip(160, metrics)),SizeManager.getDip(50, metrics));
		clp.setMargins(SizeManager.getDip(5, metrics),0,SizeManager.getDip(5, metrics),0);
		
		ImageButton reg = new ImageButton(context);
		reg.setBackgroundResource(R.drawable.sortbtnxml);
		reg.setImageResource(R.drawable.re);
		reg.setLayoutParams(clp);
		reg.setAdjustViewBounds(true);
		reg.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(reg);
		
		ImageButton sport = new ImageButton(context);
		sport.setBackgroundResource(R.drawable.sortbtnxml);
		sport.setImageResource(R.drawable.sp);
		sport.setLayoutParams(clp);
		sport.setAdjustViewBounds(true);
		sport.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(sport);
		
		ImageButton bus = new ImageButton(context);
		bus.setBackgroundResource(R.drawable.sortbtnxml);
		bus.setImageResource(R.drawable.bu);
		bus.setLayoutParams(clp);
		bus.setAdjustViewBounds(true);
		bus.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(bus);
		
		ImageButton ent = new ImageButton(context);
		ent.setBackgroundResource(R.drawable.sortbtnxml);
		ent.setImageResource(R.drawable.entertainment);		
		ent.setLayoutParams(clp);
		ent.setAdjustViewBounds(true);
		ent.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(ent);
		
		ImageButton added = new ImageButton(context);
		added.setBackgroundResource(R.drawable.sortbtnxml);
		added.setImageResource(R.drawable.my);
		added.setLayoutParams(clp);
		added.setAdjustViewBounds(true);
		added.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(added);
	}
}
