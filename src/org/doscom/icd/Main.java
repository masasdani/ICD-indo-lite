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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends ListActivity implements OnClickListener{
    
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
	private Button menu;
	private TextView textKosong;
	
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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Penyakit penyakit = list.get(position);
		Bundle bundle = new Bundle();
		bundle.putString("kode", penyakit.getKode());
		bundle.putString("kategori_utama_en", penyakit.getKategoriUtamaEn());
		bundle.putString("kategori_utama_id", penyakit.getKategoriUtamaId());
		bundle.putString("kategori_en", penyakit.getKategoriEn());
		bundle.putString("kategori_id", penyakit.getKategoriId());
		bundle.putString("penyakit_en", penyakit.getPenyakitEn());
		bundle.putString("penyakit_id", penyakit.getPenyakitId());
		Intent intent = new Intent(this, Detail.class);
		intent.putExtras(bundle);
		startActivity(intent);
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
		textKosong = (TextView) findViewById(R.id.textKosong);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.suggestion, suggestion);
		editText.setAdapter(arrayAdapter);
		
		button = (ImageButton) findViewById(R.id.ok);
		button.setOnClickListener(this);
		menu = (Button) findViewById(R.id.menu);
		menu.setOnClickListener(this);
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

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.menu:
			openOptionsMenu();
			break;
		
		case R.id.ok:
			getData();
			textKosong.setVisibility(View.GONE);
			getListView().setVisibility(View.VISIBLE);
			break;
			
		default:
			break;
		}
	}
}