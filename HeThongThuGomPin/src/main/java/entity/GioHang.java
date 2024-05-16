package entity;


public class GioHang {

	//properties
	private Pin pin;
	private ThongTinDatLich thongTinDatLich;
	private int soLuong;
	public GioHang(Pin pin, ThongTinDatLich thongTinDatLich, int soLuong) {
		super();
		this.pin = pin;
		this.thongTinDatLich = thongTinDatLich;
		this.soLuong = soLuong;
	}
	public GioHang(ThongTinDatLich thongTinDatLich, int soLuong) {
		super();
		this.thongTinDatLich = thongTinDatLich;
		this.soLuong = soLuong;
	}
	public Pin getPin() {
		return pin;
	}
	public void setPin(Pin pin) {
		this.pin = pin;
	}
	public ThongTinDatLich getThongTinDatLich() {
		return thongTinDatLich;
	}
	public void setThongTinDatLich(ThongTinDatLich thongTinDatLich) {
		this.thongTinDatLich = thongTinDatLich;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "GioHang [pin=" + pin + ", thongTinDatLich=" + thongTinDatLich + ", soLuong=" + soLuong + "]";
	}

	
	

	
	
	
}
