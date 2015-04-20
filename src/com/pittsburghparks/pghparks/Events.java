package com.pittsburghparks.pghparks;

import com.actionbarsherlock.app.SherlockFragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* The Events page loads the event page from the PPC's events page website*/
public class Events extends SherlockFragment {

	@SuppressLint("SetJavaScriptEnabled")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View mainView = (View) inflater.inflate(R.layout.donate, container, false);
	    WebView webView = (WebView) mainView.findViewById(R.id.web);
	    
	    webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   
        webView.getSettings().setAllowFileAccess(true); 
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("http://www.pittsburghparks.org/calendar");
	    
	    return mainView;
	}
	
	public class MyWebViewClient extends WebViewClient {        
        /* (non-Java doc)
         * @see android.webkit.WebViewClient#shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String)
         */

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".mp4")) 
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "video/*");

                view.getContext().startActivity(intent);
                return true;
            } 
            else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
	}

}