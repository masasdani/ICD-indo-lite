package org.doscom.icd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends Activity implements OnClickListener{
	
	private TextView kode;
	private TextView kategori_utama_en;
	private TextView kategori_utama_id;
	private TextView kategori_en;
	private TextView kategori_id;
	private TextView penyakit_en;
	private TextView penyakit_id;
	private Button menus;
	private Bundle bundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail);
		
		bundle = getIntent().getExtras();
		initView();
	}

	private void initView() {
		menus = (Button) findViewById(R.id.menu);
		menus.setOnClickListener(this);
		kode = (TextView) findViewById(R.id.kode);
		kategori_utama_en = (TextView) findViewById(R.id.kategori_utama_en);
		kategori_utama_id = (TextView) findViewById(R.id.kategori_utama_id);
		kategori_en = (TextView) findViewById(R.id.kategori_en);
		kategori_id = (TextView) findViewById(R.id.kategori_id);
		penyakit_en = (TextView) findViewById(R.id.penyakit_en);
		penyakit_id = (TextView) findViewById(R.id.penyakit_id);
		
		kode.setText(bundle.getString("kode"));
		kategori_utama_en.setText(bundle.getString("kategori_utama_en"));
		kategori_utama_id.setText(bundle.getString("kategori_utama_id"));
		kategori_en.setText(bundle.getString("kategori_en"));
		kategori_id.setText(bundle.getString("kategori_id"));
		penyakit_en.setText(bundle.getString("penyakit_en"));
		penyakit_id.setText(bundle.getString("penyakit_id"));
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.menu:
			finish();
			break;

		default:
			break;
		}
	}
}
