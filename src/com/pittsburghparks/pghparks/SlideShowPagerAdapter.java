package com.pittsburghparks.pghparks;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SlideShowPagerAdapter extends PagerAdapter {

	Context mContext;
	LayoutInflater mLayoutInflater;
	ImageLoader imageLoader = ImageLoader.getInstance();

	private String[] mResources;
	
	public SlideShowPagerAdapter(Context context, String[] images) {
		mContext = context;
		mResources = images;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mResources.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

		ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheInMemory()
		.cacheOnDisc()
		.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
		.defaultDisplayImageOptions(defaultOptions)
		.build();
		imageLoader.init(config);
		String mainImageUrl = mResources[position];
		imageLoader.displayImage(mainImageUrl, imageView);
		
		//imageView.setImageResource(mResources[position]);

		container.addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}

}
