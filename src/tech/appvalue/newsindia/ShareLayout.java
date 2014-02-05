package tech.appvalue.newsindia;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ShareLayout extends LinearLayout{

	Context context;
	DisplayMetrics metrics;
	public ShareLayout(Context c, OnClickListener lstn) {
		super(c);
		Log.d("sharelayout","creating");
		this.context=c;
		metrics = context.getResources().getDisplayMetrics();
		RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams((int) Math.floor(SizeManager.getDip(138, metrics)),SizeManager.getDip(310, metrics));
		this.setOrientation(LinearLayout.VERTICAL);
		
		this.setLayoutParams(rlp);
		this.setBackgroundResource(R.drawable.share_bg);
		addCategories();
	}
	public void addCategories() 
	{
		ImageButton eng = new ImageButton(context);
		eng.setBackgroundResource(0);
		eng.setImageResource(R.drawable.emailshxml);
		//eng.setImageResource(R.drawable.en);
		LinearLayout.LayoutParams englp = new LinearLayout.LayoutParams((int) Math.floor(SizeManager.getDip(50, metrics)*198/47.0),SizeManager.getDip(50, metrics));
		
		eng.setLayoutParams(englp);
		englp.topMargin=SizeManager.getDip(10, metrics);
		eng.setAdjustViewBounds(true);
		eng.setScaleType(ScaleType.CENTER_INSIDE);
		englp.gravity=Gravity.CENTER_HORIZONTAL;
		this.addView(eng);
		
		LinearLayout.LayoutParams clp = new LinearLayout.LayoutParams((int) Math.floor(SizeManager.getDip(50, metrics)*198/47.0),SizeManager.getDip(50, metrics));
		clp.gravity=Gravity.CENTER;
		//clp.setMargins(SizeManager.getDip(0, metrics),0,SizeManager.getDip(0, metrics),0);
		
		ImageButton reg = new ImageButton(context);
		reg.setBackgroundResource(0);
		reg.setImageResource(R.drawable.fbshxml);
		reg.setLayoutParams(clp);
		reg.setAdjustViewBounds(true);
		reg.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(reg);
		
		ImageButton sport = new ImageButton(context);
		sport.setBackgroundResource(0);
		sport.setImageResource(R.drawable.twitshxml);
		sport.setLayoutParams(clp);
		sport.setAdjustViewBounds(true);
		sport.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(sport);
		
		ImageButton bus = new ImageButton(context);
		bus.setImageResource(R.drawable.smsshxml);
		bus.setBackgroundResource(0);
		bus.setLayoutParams(clp);
		bus.setAdjustViewBounds(true);
		bus.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(bus);
		
		ImageButton ent = new ImageButton(context);
		ent.setImageResource(R.drawable.whatsappshxml);
		ent.setBackgroundResource(0);		
		ent.setLayoutParams(clp);
		ent.setAdjustViewBounds(true);
		ent.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(ent);
		
		ImageButton added = new ImageButton(context);
		added.setImageResource(R.drawable.connectshxml);
		added.setBackgroundResource(0);
		added.setLayoutParams(clp);
		added.setAdjustViewBounds(true);
		added.setScaleType(ScaleType.CENTER_INSIDE);
		this.addView(added);
	}
}
