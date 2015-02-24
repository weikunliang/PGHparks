package com.pittsburghparks.pghparks;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class TextViewBoldFont extends TextView {
	public TextViewBoldFont(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/edmondsans-bold.ttf");
		setTypeface(tf);
	}


}
