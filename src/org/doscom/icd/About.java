package org.doscom.icd;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class About extends Activity {

	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		initView();
	}

	private void initView() {
		webView = (WebView) findViewById(R.id.about_text);
		webView.loadUrl("file:///android_asset/about.html");
	}
}
