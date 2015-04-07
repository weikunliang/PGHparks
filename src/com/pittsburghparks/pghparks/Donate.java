package com.pittsburghparks.pghparks;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Donate extends SherlockFragment {
	
	@SuppressWarnings("deprecation")
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	    View mainView = (View) inflater.inflate(R.layout.donate, container, false);
	    WebView webView = (WebView) mainView.findViewById(R.id.web);
	    
	    webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setPluginsEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false); 
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);   
        webView.getSettings().setAllowFileAccess(true); 
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("https://www.pittsburghparks.org/donate_mobile");
	    
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