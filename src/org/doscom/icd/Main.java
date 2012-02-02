package org.doscom.icd;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Main extends Activity {
    
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;
	private Cursor cursor;
	
	private String config="icd.conf";
	
	private EditText editText;
	private ImageButton button;
	
	private String LangKategoriUtama;
	private String LangKategori;
	private String LangPenyakit;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        databaseHelper = new DatabaseHelper(this);
		try {
			databaseHelper.createDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		database = databaseHelper.openDatabase();
	}
	
	private void getData() {
		String cari = editText.getText().toString();
		cursor = database.rawQuery("select * from Penyakit where ", null);
		if(cursor== null){
			Log.d(getPackageName(), "null");
		}else{
			if(cursor.moveToFirst()){
				
			}
		}
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.entry);
		button = (ImageButton) findViewById(R.id.ok);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
			}
		});
	}
}