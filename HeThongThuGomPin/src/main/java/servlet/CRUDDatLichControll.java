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
@WebServlet("/CRUDDatLichControll")
public class CRUDDatLichControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/PinTaiChe")
	private DataSource dataSource;
	private PinModel pinModel;
	private ThuongHieuModel thuongHieuModel;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CRUDDatLichControll() {
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
				case "LIST":    listThongTinDatLich(request, response);
					break;		
				
				case "ADD":     addThongTinDatLich(request, response);
					break;	
					
				case "LOADDATLICH":    loadDatLich(request, response);
					break;	
						
				case "UPDATE":	updateDatLich(request, response);
					break;	
					
				case "DELETE":	deleteThongTinDatLich(request, response);
					break;
					
//				case "LIST_BY_PRICE_ASCENDING": listByPriceAscending(request, response);
//					break;
//					
//				case "LIST_BY_PRICE_DESCENDING": listByPriceDescending(request, response);
//				break;
				
				case "SEARCH":
				    searchDongHo(request, response);
				    break;
						
//				case "LIST_BY_BRAND":
//				    listDongHoByBrand(request, response);
//				    break;
				   
				default:
					listThongTinDatLich(request, response);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			throw new ServletException();
		}
	}

	private void listBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands();
		request.setAttribute("LIST_BRANDS", listBrands);
	}

//	private void listDongHoByBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String thuongHieuIdStr = request.getParameter("thuongHieuId");
//	    int thuongHieuId = (thuongHieuIdStr != null && !thuongHieuIdStr.isEmpty()) ? Integer.parseInt(thuongHieuIdStr) : 0;
//
//	    List<DongHo> listDongHoByBrand = pinModel.getDongHoByBrand(thuongHieuId);
//	    List<ThuongHieu> listBrands = thuongHieuModel.getAllBrands(); 
//
//	    request.setAttribute("LIST_DONGHO", listDongHoByBrand);
//	    request.setAttribute("LIST_BRANDS", listBrands);
//	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-dongho.jsp");
//	    dispatcher.forward(request, response);
//	}

	private void searchDongHo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tenPin = request.getParameter("tenDH");
	    List<Pin> listPin = pinModel.searchPinByName(tenPin);
	    request.setAttribute("LIST_PIN", listPin);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
	    dispatcher.forward(request, response);
	}

	private void deleteThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maDatLich");
		pinModel.deleteThongTinDatLich(id);
		listThongTinDatLich(request, response);
	}

	private void updateDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("maDatLich"));
		  String tenKH = request.getParameter("tenKH");
	      String soDT = request.getParameter("soDT");
	      String diaChi = request.getParameter("diaChi");
	      String thoiGian = request.getParameter("thoiGian");
	      String ngayHen = request.getParameter("ngayHen");
	      
	      String  maChiNhanhString = request.getParameter("maChiNhanh");
	      int maChiNhanh = Integer.parseInt(maChiNhanhString);
	      
	      String maPinString = request.getParameter("maPin");
	      int maPin = Integer.parseInt(maPinString);
	      
	      String moTa = request.getParameter("moTa");
	      
		ThongTinDatLich datLich = new ThongTinDatLich(id, tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), moTa);
		pinModel.updateThongTinDatLich(datLich);
		listThongTinDatLich(request, response);
	}

	private void loadDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("maDatLich");
		ThongTinDatLich datLich  = pinModel.getDatLich(id);
		request.setAttribute("DATLICH", datLich);
		RequestDispatcher dispatcher = request.getRequestDispatcher("update-pin-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      // Lấy dữ liệu từ form
	      String tenKH = request.getParameter("tenKH");
	      String soDT = request.getParameter("soDT");
	      String diaChi = request.getParameter("diaChi");
	      String thoiGian = request.getParameter("thoiGian");
	      String ngayHen = request.getParameter("ngayHen");
	      
	      String  maChiNhanhString = request.getParameter("maChiNhanh");
	      int maChiNhanh = Integer.parseInt(maChiNhanhString);
	      
	      String maPinString = request.getParameter("maPin");
	      int maPin = Integer.parseInt(maPinString);
	      
	      String moTa = request.getParameter("moTa");
	      
	      // Tạo đối tượng ThongTinDatLich và gọi hàm addThongTinDatLich
	      ThongTinDatLich thongTinDatLich = new ThongTinDatLich(tenKH, soDT, diaChi, thoiGian, ngayHen, new ChiNhanh(maChiNhanh), new Pin(maPin), moTa);
	      pinModel.addThongTinDatLich(thongTinDatLich);
	      listThongTinDatLich(request, response);
	  }

	private void listThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<ThongTinDatLich> listDatLich = pinModel.getALLDatLich();
		request.setAttribute("LIST_DAT_LICH", listDatLich);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list-dat-lich.jsp");
		dispatcher.forward(request, response);
	}
	
//	private void listByPriceAscending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		List<DongHo> listDongHo = pinModel.getALLDongHoSortedByPriceAscending();
//		request.setAttribute("LIST_PIN", listDongHo);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
//		dispatcher.forward(request, response);
//	}
	
//	private void listByPriceDescending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		List<DongHo> listDongHo = pinModel.getALLDongHoSortedByPriceDescending();
//		request.setAttribute("LIST_PIN", listDongHo);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("list-pin.jsp");
//		dispatcher.forward(request, response);
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
