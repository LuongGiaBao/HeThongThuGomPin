package entity;

public class Pin {
	private int maPin;
	private String tenPin;
	private float giaThu;
	private int soLuong = 1;
	private String moTa;
	private int thuongHieuId;
	private String hinhAnh;
	public Pin(int maPin, String tenPin, float giaThu, int soLuong, String moTa, int thuongHieuId, String hinhAnh) {
		super();
		this.maPin = maPin;
		this.tenPin = tenPin;
		this.giaThu = giaThu;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.thuongHieuId = thuongHieuId;
		this.hinhAnh = hinhAnh;
	}
	public Pin(String tenPin, float giaThu, int soLuong, String moTa, int thuongHieuId, String hinhAnh) {
		super();
		this.tenPin = tenPin;
		this.giaThu = giaThu;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.thuongHieuId = thuongHieuId;
		this.hinhAnh = hinhAnh;
	}
	
	public Pin(int maPin) {
		super();
		this.maPin = maPin;
	}
	public Pin(String tenPin) {
		super();
		this.tenPin = tenPin;
	}
	public Pin(String tenPin, float giaThu, int soLuong, String moTa, String hinhAnh) {
		super();
		this.tenPin = tenPin;
		this.giaThu = giaThu;
		this.soLuong = soLuong;
		this.moTa = moTa;
		this.hinhAnh = hinhAnh;
	}
	public int getMaPin() {
		return maPin;
	}
	public void setMaPin(int maPin) {
		this.maPin = maPin;
	}
	public String getTenPin() {
		return tenPin;
	}
	public void setTenPin(String tenPin) {
		this.tenPin = tenPin;
	}
	public float getGiaThu() {
		return giaThu;
	}
	public void setGiaThu(float giaThu) {
		this.giaThu = giaThu;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public int getThuongHieuId() {
		return thuongHieuId;
	}
	public void setThuongHieuId(int thuongHieuId) {
		this.thuongHieuId = thuongHieuId;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public String toString() {
		return "Pin [maPin=" + maPin + ", tenPin=" + tenPin + ", giaThu=" + giaThu + ", soLuong=" + soLuong + ", moTa="
				+ moTa + ", thuongHieuId=" + thuongHieuId + ", hinhAnh=" + hinhAnh + "]";
	}
	
	
	

}
