package org.doscom.icd.dao;

import java.util.ArrayList;
import java.util.List;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PenyakitDaoImpl implements PenyakitDao{
	
	SQLiteDatabase database;
	
	public PenyakitDaoImpl(SQLiteDatabase database) {
		this.database=database;
	}

	@Override
	public List<Penyakit> getAll() {
		List<Penyakit> list=new ArrayList<Penyakit>();
		Cursor cursor=database.rawQuery("select * from penyakit", null);
		cursor.moveToFirst();
		for(;!cursor.isAfterLast();cursor.moveToNext()){
			Penyakit p= new Penyakit(cursor.getString(0),
					cursor.getString(1),
					cursor.getString(2),
					cursor.getString(3), 
					cursor.getString(4), 
					cursor.getString(5), 
					cursor.getString(6));
			list.add(p);
		}
		return list;
	}

	@Override
	public Penyakit getPenyakitById(String kode) {
		Cursor cursor=database.rawQuery("select * from penyakit", null);
		return new Penyakit(cursor.getString(0), 
				cursor.getString(1),
				cursor.getString(2),
				cursor.getString(3),
				cursor.getString(4),
				cursor.getString(5),
				cursor.getString(6));
	}

	@Override
	public List<Penyakit> getPenyakits(String cari) {
		List<Penyakit> list=new ArrayList<Penyakit>();
		String s = "%"+cari+"%";
		String[] args={s,s,s,s,s,s};
		Cursor cursor=database.rawQuery("select * from penyakit where " +
				"kategori_utama_en like ? " +
				"or kategori_utama_id like ? " +
				"or kategori_id like ? " +
				"or kategori_en like ? " +
				"or penyakit_en like ? " +
				"or penyakit_id like ? ", args);
		cursor.moveToFirst();
		for(;!cursor.isAfterLast();cursor.moveToNext()){
			Penyakit p= new Penyakit(cursor.getString(0),
					cursor.getString(1),
					cursor.getString(2), 
					cursor.getString(3), 
					cursor.getString(4), 
					cursor.getString(5), 
					cursor.getString(6));
			list.add(p);
		}
		return list;
	}

	@Override
	public String[] getSuggestion(String language) {
		Cursor cursor=database.rawQuery("select * from penyakit", null);
		cursor.moveToFirst();
		String[] data = new String[cursor.getCount()];
		int i = 0;
		while(cursor.moveToNext()){
			data[i] = language == "EN" ? cursor.getString(6) : cursor.getString(5);
			i++;
		}
		return data;
	}

}
