package jp.idumo.java.android;

import android.app.Activity;
import android.os.Bundle;

public class TextView extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.widget.TextView view = new android.widget.TextView(this);
		setContentView(view);
		view.setText("aaa");
	}
	
}
