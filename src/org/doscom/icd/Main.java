package org.doscom.icd;

import java.io.IOException;
import java.util.List;

import org.doscom.icd.dao.Penyakit;
import org.doscom.icd.dao.PenyakitDao;
import org.doscom.icd.dao.PenyakitDaoImpl;
import org.doscom.icd.template.ListViewAdapter;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main extends ListActivity {
    
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase database;
	private PenyakitDao penyakitDao;
	private List<Penyakit> list;
	private ListViewAdapter adapter;
	
	private EditText editText;
	private ImageButton button;
	private ImageView imageView;
	
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
		penyakitDao = new PenyakitDaoImpl(database);
	}
	
	private void getData() {
		String cari = editText.getText().toString();
		list = penyakitDao.getPenyakits(cari);
		adapter = new ListViewAdapter(this, list, "EN");
		setListAdapter(adapter);
	}

	private void initView() {
		editText = (EditText) findViewById(R.id.entry);
		button = (ImageButton) findViewById(R.id.ok);
		imageView = (ImageView) findViewById(R.id.fkm);
		
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getData();
				imageView.setVisibility(View.GONE);
				getListView().setVisibility(View.VISIBLE);
			}
		});
	}
}