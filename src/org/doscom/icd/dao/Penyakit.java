package org.doscom.icd.dao;

public class Penyakit {
	
	String kode;
	String kategoriUtamaEn;
	String kategoriUtamaId;
	String kategoriId;
	String kategoriEn;
	String penyakitId;
	String penyakitEn;
	
	public Penyakit(String kode, String kategoriUtamaEn,
			String kategoriUtamaId, String kategoriId, String kategoriEn,
			String penyakitId, String penyakitEn) {
		super();
		this.kode = kode;
		this.kategoriUtamaEn = kategoriUtamaEn;
		this.kategoriUtamaId = kategoriUtamaId;
		this.kategoriId = kategoriId;
		this.kategoriEn = kategoriEn;
		this.penyakitId = penyakitId;
		this.penyakitEn = penyakitEn;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getKategoriUtamaEn() {
		return kategoriUtamaEn;
	}

	public void setKategoriUtamaEn(String kategoriUtamaEn) {
		this.kategoriUtamaEn = kategoriUtamaEn;
	}

	public String getKategoriUtamaId() {
		return kategoriUtamaId;
	}

	public void setKategoriUtamaId(String kategoriUtamaId) {
		this.kategoriUtamaId = kategoriUtamaId;
	}

	public String getKategoriId() {
		return kategoriId;
	}

	public void setKategoriId(String kategoriId) {
		this.kategoriId = kategoriId;
	}

	public String getKategoriEn() {
		return kategoriEn;
	}

	public void setKategoriEn(String kategoriEn) {
		this.kategoriEn = kategoriEn;
	}

	public String getPenyakitId() {
		return penyakitId;
	}

	public void setPenyakitId(String penyakitId) {
		this.penyakitId = penyakitId;
	}

	public String getPenyakitEn() {
		return penyakitEn;
	}

	public void setPenyakitEn(String penyakitEn) {
		this.penyakitEn = penyakitEn;
	}

}
