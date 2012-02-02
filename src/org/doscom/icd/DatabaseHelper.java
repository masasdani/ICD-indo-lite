package org.doscom.icd;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static String db_path = "/data/data/org.doscom.icd/databases/";
	private static String db_name = "icd.db";
	private SQLiteDatabase db;
	private final Context context;

	public DatabaseHelper(Context context) {
		super(context, db_name, null, 1);
		this.context = context;
	}
	
	public void createDatabase() throws IOException{
		boolean exist = checkDatabase();
		if(exist){
			
		}else{
			this.getReadableDatabase();
			try{
				copyDatabase();
			}catch (IOException e) {
				throw new Error("error coppying database");
			}
		}
		
	}

	private void copyDatabase() throws IOException{
		InputStream inputStream = context.getAssets().open(db_name);
		String outDb = db_path + db_name;
		OutputStream outputStream = new FileOutputStream(outDb);
		
		byte[] buffer = new byte[1024];
		int lenght;
		while ((lenght = inputStream.read(buffer)) > 0){
			outputStream.write(buffer, 0 , lenght);
		}
		
		outputStream.flush();
		outputStream.close();
		inputStream.close();
	}

	private boolean checkDatabase() {
		SQLiteDatabase db = null;
		try{
			String dbPath = db_path + db_name;
			db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch (SQLiteException e) {
			
		}
		if(db!=null){
			db.close();
		}
		Log.d("mexez", db!=null ? "true" : "false");
		return db!=null ? true : false;
	}
	
	public SQLiteDatabase openDatabase() throws SQLException{
		String dbPath = db_path + db_name;
		this.db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
		return this.db;
	}
	
	@Override
	public synchronized void close() {
		if(this.db!=null) this.db.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}	

}
