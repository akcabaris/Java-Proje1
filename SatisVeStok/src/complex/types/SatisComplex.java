package complex.types;

public class SatisComplex {

	private int id;
	private int adet;
	private String personelAdi;
	private String urunAdi;
	private String tarih;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonelAdi() {
		return personelAdi;
	}

	public void setPersonelAdi(String personelAdi) {
		this.personelAdi = personelAdi;
	}

	public String getUrunAdi() {
		return urunAdi;
	}

	public void setUrunAdi(String urunAdi) {
		this.urunAdi = urunAdi;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public int getAdet() {
		return adet;
	}

	public void setAdet(int adet) {
		this.adet = adet;
	}

	public Object[] getVeriler() {
		Object[] veriler = { id, personelAdi, urunAdi, adet, tarih};
		return veriler;
	}

	@Override
	public String toString() {
		return personelAdi + " " + urunAdi;
	}

}
