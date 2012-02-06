package org.doscom.icd;

import java.io.IOException;
import java.util.List;

import org.doscom.icd.dao.Penyakit;
import org.doscom.icd.dao.PenyakitDao;
import org.doscom.icd.dao.PenyakitDaoImpl;
import org.doscom.icd.template.ListViewAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends ListActivity {
    
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;
	private PenyakitDao penyakitDao;
	private List<Penyakit> list;
	private ListViewAdapter adapter;
	private String[] suggestion;
	private SharedPreferences sharedPreferences;
	private String language;
	
	private AutoCompleteTextView editText;
	private ImageButton button;
	private ImageView imageView;
	
	private final String PARAMETER = "language";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sharedPreferences = getSharedPreferences("icd.conf", MODE_PRIVATE);
        if(!sharedPreferences.contains(PARAMETER)){
			Editor editor = sharedPreferences.edit();
			editor.putString(PARAMETER, "ID");
			editor.commit();
		}
        language = sharedPreferences.getString(PARAMETER, null);
        
        databaseHelper = new DatabaseHelper(this);
		try {
			databaseHelper.createDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		database = databaseHelper.openDatabase();
		penyakitDao = new PenyakitDaoImpl(database);
		suggestion = penyakitDao.getSuggestion(language);
		initView();
	}
	
	private void getData() {
		String cari = editText.getText().toString();
		language = sharedPreferences.getString(PARAMETER, null);
		list = penyakitDao.getPenyakits(cari);
		if(list.size()==0){
			Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT);
		}
		adapter = new ListViewAdapter(this, list, language);
		setListAdapter(adapter);
	}

	private void initView() {
		editText =  (AutoCompleteTextView) findViewById(R.id.entry);
		button = (ImageButton) findViewById(R.id.ok);
		imageView = (ImageView) findViewById(R.id.fkm);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.suggestion, suggestion);
		editText.setAdapter(arrayAdapter);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
				imageView.setVisibility(View.GONE);
				getListView().setVisibility(View.VISIBLE);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_item, menu);
		if(language.equals("ID")){
			menu.findItem(R.id.lang_in).setChecked(true);
		}else{
			menu.findItem(R.id.lang_en).setChecked(true);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		case R.id.lang_in : 
			item.setChecked(true);
			if(item.isChecked())
			{
				Editor editor = sharedPreferences.edit();
				editor.remove(PARAMETER);
				editor.putString(PARAMETER, "ID");
				editor.commit();
				language = sharedPreferences.getString(PARAMETER, null);
				Log.d("pref", language);
			}
			return true;
		case R.id.lang_en : 
			item.setChecked(true);
			if(item.isChecked())
			{
				Editor editor = sharedPreferences.edit();
				editor.remove(PARAMETER);
				editor.putString(PARAMETER, "EN");
				editor.commit();
				language = sharedPreferences.getString(PARAMETER, null);
				Log.d("pref", language);
			}
			return true;
		default: return true;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		database.close();
	}
}