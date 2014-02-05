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
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class DeleteNewspaperActivity extends Activity implements OnClickListener{

	RelativeLayout mrl;
	TextView message;
	String name;
	int w,h;
	DisplayMetrics metrics;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		w = size.x;
		h = size.y;
		
		metrics = getResources().getDisplayMetrics();
		
		mrl = new RelativeLayout(this);
		setContentView(mrl);
		mrl.setBackgroundResource(R.drawable.delete_bg);
		//RelativeLayout.LayoutParams mrlp = new RelativeLayout.LayoutParams(w,RelativeLayout.LayoutParams.WRAP_CONTENT);
		//mrl.setLayoutParams(mrlp);
		
		TextView title = new TextView(this);
		title.setText("Delete Newspaper");
		title.setTypeface(null,Typeface.BOLD);
		title.setTextSize(22);
		title.setTextColor(getResources().getColor(R.color.black));
		title.setId(1);
		mrl.addView(title);
		RelativeLayout.LayoutParams tlp = (LayoutParams) title.getLayoutParams();
		tlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		ImageView div = new ImageView(this);
		div.setBackgroundResource(R.drawable.divider);
		div.setId(2);
		mrl.addView(div);
		RelativeLayout.LayoutParams dlp = (LayoutParams) div.getLayoutParams();
		dlp.height = 1;
		dlp.width = w;
		dlp.addRule(RelativeLayout.BELOW,1);
		
		Intent data = getIntent();
		name = data.getStringExtra("newspaper");
		
		Intent ri = new Intent();
		setResult(RESULT_OK,ri);
		
		message = new TextView(this);
		message.setId(3);
		mrl.addView(message);
		RelativeLayout.LayoutParams mlp = (LayoutParams) message.getLayoutParams();
		mlp.addRule(RelativeLayout.BELOW,2);
		mlp.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mlp.setMargins(SizeManager.getDip(10, metrics), SizeManager.getDip(10, metrics), SizeManager.getDip(10, metrics), SizeManager.getDip(10, metrics));		
		setDisplayMessage();
		
		ImageView nobtn = new ImageView(this);
		nobtn.setBackgroundResource(R.drawable.no);
		nobtn.setId(4);
		mrl.addView(nobtn);
		RelativeLayout.LayoutParams nlp = (LayoutParams) nobtn.getLayoutParams();
		nlp.setMargins(SizeManager.getDip(5, metrics), SizeManager.getDip(10, metrics), 0, SizeManager.getDip(10, metrics));
		nlp.addRule(RelativeLayout.BELOW,3);
		nobtn.setOnClickListener(this);
		
		ImageView ybtn = new ImageView(this);
		ybtn.setBackgroundResource(R.drawable.yes);
		ybtn.setId(5);
		mrl.addView(ybtn);
		RelativeLayout.LayoutParams ylp = (LayoutParams) ybtn.getLayoutParams();
		ylp.setMargins(0, SizeManager.getDip(10, metrics), SizeManager.getDip(5, metrics), SizeManager.getDip(10, metrics));
		ylp.rightMargin=SizeManager.getDip(1000, metrics);
		ylp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		ylp.addRule(RelativeLayout.BELOW,3);
		ybtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case 4://no
		{
			this.onBackPressed();
			break;
		}
		case 5://yes
		{
			deleteNewspaper();
			break;
		}
		}
	}
	public void deleteNewspaper()
	{
		NewsManager.deleteNewspaper(name, this);
		Intent ri = new Intent();
		ri.putExtra("deleted", true);
		setResult(RESULT_OK,ri);
		super.onBackPressed();
	}
	public void setDisplayMessage()
	{
		String text = "Are you sure you want do delete  \'"+name+"\'?";
		if(NewsManager.isFavorite(name, this));
		{
			Log.d("favliststring",NewsManager.getFavListString(this));
			//text = text+"\nIt will be deleted from the Favorites list too.";
		}
		message.setText(text);
		message.setTextColor(getResources().getColor(R.color.black));
	}
}
