package entity;

public class ChiNhanh {
	
	//properties
	private int maChiNhanh;
	private String diaChiChiNhanh;
	
	//constructors
	public ChiNhanh(int maChiNhanh, String diaChiChiNhanh) {
		super();
		this.maChiNhanh = maChiNhanh;
		this.diaChiChiNhanh = diaChiChiNhanh;
	}
	
	public ChiNhanh(int maChiNhanh) {
		super();
		this.maChiNhanh = maChiNhanh;
	}




	public ChiNhanh(String diaChiChiNhanh) {
		super();
		this.diaChiChiNhanh = diaChiChiNhanh;
	}




	public int getMaChiNhanh() {
		return maChiNhanh;
	}

	public void setMaChiNhanh(int maChiNhanh) {
		this.maChiNhanh = maChiNhanh;
	}
	
	public String getDiaChiChiNhanh() {
			return diaChiChiNhanh;
		}
	
	
	public void setDiaChiChiNhanh(String diaChiChiNhanh) {
			this.diaChiChiNhanh = diaChiChiNhanh;
		}
	
		
	//toString


	@Override
	public String toString() {
		return "ChiNhanh [maChiNhanh=" + maChiNhanh + ", diaChiChiNhanh=" + diaChiChiNhanh + "]";
	}


	


	
}
