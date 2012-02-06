package org.doscom.icd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	
	final Splash screen = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		SplashThread splashThread = new SplashThread();
		splashThread.start();
	}
	
	class SplashThread extends Thread{
		@Override
		public void run() {
			try{
				synchronized (this) {
					wait(3000);
				}
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
			finish();
			Intent intent = new Intent();
			intent.setClass(screen, Main.class);
			startActivity(intent);
		}
	}

}
