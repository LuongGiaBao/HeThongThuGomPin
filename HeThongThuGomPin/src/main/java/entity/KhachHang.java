package entity;

public class KhachHang {
	private int maKH;
	private String tenKH;
	private String soDT;
	private String diaChi;
	public KhachHang(int maKH, String tenKH, String soDT, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.soDT = soDT;
		this.diaChi = diaChi;
	}
	public KhachHang(String tenKH, String soDT, String diaChi) {
		super();
		this.tenKH = tenKH;
		this.soDT = soDT;
		this.diaChi = diaChi;
	}
	
	
	public KhachHang(int maKH) {
		super();
		this.maKH = maKH;
	}
	public KhachHang(String tenKH) {
		super();
		this.tenKH = tenKH;
	}
	public int getMaKH() {
		return maKH;
	}
	public void setMaKH(int maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", soDT=" + soDT + ", diaChi=" + diaChi + "]";
	}
	
	
	
	

}
