package servlet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


import model.PinModel;
import model.ThuongHieuModel;
import entity.ChiNhanh;
import entity.KhachHang;
import entity.Pin;
import entity.ThongTinDatLich;
import entity.ThuongHieu;

/**
 * Servlet implementation class CRUDServlet
 */
@WebServlet("/CRUDServlet")
public class CRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/PinTaiChe")
	private DataSource dataSource;
	private PinModel pinModel;
	private ThuongHieuModel thuongHieuModel;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			pinModel = new PinModel(dataSource);
			thuongHieuModel = new ThuongHieuModel(dataSource);
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServletException();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listBrand(request, response);
			String command = request.getParameter("command");
			if (command == null) {
				command = "LIST";
			}
			switch (command) 
			{		
				case "LIST":    listPin(request, response);
					break;		
				
				case "ADDPIN":     addPin(request, response);
					break;	
//					
				case "LOADPIN":    loadPin(request, response);
					break;	
					
				case "UPDATEPIN":	updatePin(request, response);
				break;
				
				case "DELETEPIN":	deletePin(request, response);
					break;
					
				/*
				 * case "LIST_BY_PRICE_ASCENDING": listByPriceAscending(request, response);
				 * break;
				 * 
				 * case "LIST_BY_PRICE_DESCENDING": listByPriceDescending(request, response);
				 * break;
				 */
				
				case "SEARCH":
				    searchPin(request, response);
				    break;
						
				/*
				 * case "LIST_BY_BRAND": listDongHoByBrand(request, response); break;
				 */
				   
				default:
					listPin(request, response);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServletException();
		}
	}

	private void updatePin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  int id = Integer.parseInt(request.getParameter("maPin"));
		  String tenPin = request.getParameter("tenPin");
		  int soLuong = Integer.parseInt(request.getParameter("SoLuong"));
	      float giaThu = Float.parseFloat(request.getParameter("giaThu"));
	      String moTa = request.getParameter("moTa");
	      int thuongHieuId = Integer.parseInt(request.getParameter("thuongHieuId"));
	      String hinhAnh = request.getParameter("hinhAnh");
	    
	     
	    Pin pin = new Pin(id, tenPin, giaThu, soLuong, moTa, thuongHieuId, hinhAnh);
		pinModel.updatePin(pin);
		listPin(request, response);
		// TODO Auto-generated method stub
		
	}

	private void deletePin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maPin");
		pinModel.deleteThongTinDatLich(id);
		listPin(request, response);
		
	}

	private void loadPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maPin");
		Pin pin = pinModel.getPin(id);
		request.setAttribute("PIN", pin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-pin-form.jsp");
		dispatcher.forward(request, response);		
		
	}

	private void addPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // int maPin = Integer.parseInt(request.getParameter("maPin"));
		  String tenPin = request.getParameter("tenPin");
		  float giaThu = Float.parseFloat(request.getParameter("giaThu"));
		  int soLuong = Integer.parseInt(request.getParameter("SoLuong"));
	     
	      String moTa = request.getParameter("moTa");
	      int thuongHieuId = Integer.parseInt(request.getParameter("thuongHieuId"));
	      String hinhAnh = request.getParameter("hinhAnh");
	     Pin pin = new Pin(tenPin, giaThu, soLuong, moTa, hinhAnh);
		  pinModel.addThongTinPin(pin);
		  listPin(request, response);
		// TODO Auto-generated method stub
		
	}

	private void listBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands();
		request.setAttribute("LIST_BRANDS", listBrands);
	}

	/*
	 * private void listDongHoByBrand(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { // TODO
	 * Auto-generated method stub String thuongHieuIdStr =
	 * request.getParameter("thuongHieuId"); int thuongHieuId = (thuongHieuIdStr !=
	 * null && !thuongHieuIdStr.isEmpty()) ? Integer.parseInt(thuongHieuIdStr) : 0;
	 * 
	 * List<DongHo> listDongHoByBrand = pinModel.getDongHoByBrand(thuongHieuId);
	 * List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands();
	 * 
	 * request.setAttribute("LIST_DONGHO", listDongHoByBrand);
	 * request.setAttribute("LIST_BRANDS", listBrands); RequestDispatcher dispatcher
	 * = request.getRequestDispatcher("list-dongho.jsp");
	 * dispatcher.forward(request, response); }
	 */

	private void searchPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tenPin = request.getParameter("tenDH");
	    List<Pin> listPin = pinModel.searchPinByName(tenPin);
	    request.setAttribute("LIST_PIN", listPin);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
	    dispatcher.forward(request, response);
	}

	private void deleteDongHo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maDH");
		pinModel.deleteThongTinDatLich(id);
		listPin(request, response);
	}


	private void loadDongHo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maPin");
		Pin pin = pinModel.getPin(id);
		request.setAttribute("DATLICH", pin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-pin-form.jsp");
		dispatcher.forward(request, response);		
	}

//	private void addThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String tenKH = request.getParameter("tenKH");
//		String soDienThoai = request.getParameter("soDienThoai");
//		String diaChi = request.getParameter("diaChi");
//		String thoiGian = request.getParameter("thoiGian");
//		String ngayHen = request.getParameter("ngayHen");
//		String diaChiChiNhanh = request.getParameter("diaChiChiNhanh");
//		String ghiChu = request.getParameter("ghiChu");	
//		
//		
//		ThongTinDatLich datLich = new ThongTinDatLich(new KhachHang(tenKH, soDienThoai, diaChi), thoiGian, ngayHen, new ChiNhanh(diaChiChiNhanh), null, ghiChu);
//		pinModel.addThongTinDatLich(datLich);
//		listDongHo(request, response);
//	}

	private void listPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Pin> listPin = pinModel.getALLPin();
		request.setAttribute("LIST_PIN", listPin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
		dispatcher.forward(request, response);
	}
	
	/*
	 * private void listByPriceAscending(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { // TODO
	 * Auto-generated method stub List<DongHo> listDongHo =
	 * pinModel.getALLDongHoSortedByPriceAscending();
	 * request.setAttribute("LIST_PIN", listDongHo); RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("list-pin.jsp"); dispatcher.forward(request,
	 * response); }
	 * 
	 * private void listByPriceDescending(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { // TODO
	 * Auto-generated method stub List<DongHo> listDongHo =
	 * pinModel.getALLDongHoSortedByPriceDescending();
	 * request.setAttribute("LIST_PIN", listDongHo); RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("list-pin.jsp"); dispatcher.forward(request,
	 * response); }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
