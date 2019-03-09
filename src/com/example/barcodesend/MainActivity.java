package com.example.barcodesend;
import android.os.Bundle;
import android.app.Activity;
import android.barcode.ScanUtil;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
	Button button1;
	EditText edit,editText2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button1 = (Button) findViewById(R.id.button1);
		edit = (EditText) findViewById(R.id.editText1);
		editText2= (EditText) findViewById(R.id.editText2);
		button1.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button1) {
			ScanUtil.sendBarcode(this, "213414");
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == 220||keyCode == 221) {
			ScanUtil.sendBarcode(this, "213414");
		    
		}
		return super.onKeyDown(keyCode, event);
	}
}
