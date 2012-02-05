package org.doscom.icd.template;

import java.util.List;

import org.doscom.icd.R;
import org.doscom.icd.dao.Penyakit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private Context context;
	private List<Penyakit> list; 
	private String language;
	
	public ListViewAdapter(Context context, List<Penyakit> list, String language) {
		this.context=context;
		this.list=list;
		this.language= language;
	}
	
	@Override
	public int getCount() {
		return this.list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		String kode = list.get(position).getKode();
		String kategoriUtama = this.language == "EN" ? list.get(position).getKategoriUtamaEn() : list.get(position).getKategoriUtamaId();
		String kategori = this.language == "EN" ? list.get(position).getKategoriEn() : list.get(position).getKategoriId();
		String penyakit = this.language == "EN" ? list.get(position).getPenyakitEn() : list.get(position).getPenyakitId();
		
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item, null);
			viewHolder = new ViewHolder();
			viewHolder.kode = (TextView) convertView.findViewById(R.id.kode);
			viewHolder.kategori_utama = (TextView) convertView.findViewById(R.id.kategori_utama);
			viewHolder.kategori = (TextView) convertView.findViewById(R.id.kategori);
			viewHolder.penyakit = (TextView) convertView.findViewById(R.id.penyakit);
			
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.kode.setText(kode);
		viewHolder.kategori_utama.setText(kategoriUtama);
		viewHolder.kategori.setText(kategori);
		viewHolder.penyakit.setText(penyakit);
		
		return convertView;
	}
	
	public class ViewHolder{
		TextView kode;
		TextView kategori_utama;
		TextView kategori;
		TextView penyakit;
	}

}
