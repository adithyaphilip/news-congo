package tech.appvalue.newsindia;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public class SizeManager {
public static int getDip(int dip, DisplayMetrics metrics)
{
	int x = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, metrics);
	return x;
}
}
