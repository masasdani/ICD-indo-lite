package org.doscom.icd.dao;

import java.util.List;

public interface PenyakitDao {
	
	public List<Penyakit> getAll();
	public Penyakit getPenyakitById(String kode);
	public List<Penyakit> getPenyakits(String cari);
	public String[] getSuggestion(String language);

}
