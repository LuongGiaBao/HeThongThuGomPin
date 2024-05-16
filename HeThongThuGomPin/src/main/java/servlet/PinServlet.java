package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import model.PinModel;
import entity.GioHang;
import entity.Pin;





@WebServlet("/PinServlet")
public class PinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	private PinModel dongHoUtil;
	
	@Resource(name="jdbc/PinTaiChe")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();

		try {
			dongHoUtil = new PinModel(dataSource);
			
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String command = request.getParameter("command");
	        if (command == null) {
	            command = "TEST";
	        }
	        switch (command) {
	            case "TEST":
	            	dsPinVaTatCaPin(request, response);
//	                dsDongHo(request, response);
//	                dsTatCaDongHo(request, response);
	                break;
//	            case "MUANGAY":
//	                //loadChiTiet(request, response); 
//	                AddItemsToCart(request, response);
//	                break;
	            case "LOADTHONGTIN":
	            	ShowViewThongTinDatLich(request, response);	               
	                break;
	                
	            case "LOADCHITIET":
	            	ShowViewItem(request, response);	               
	                break;
//	          
//	    		case "ADDTOCART":
//	    			AddItemsToCart(request, response);
//	    			//UpdateCart(request, response);
//	    			break;
	    			
				/*
				 * case "UPDATECART": // UpdateCart(request, response); UpdateCart2(request,
				 * response); break;
				 * 
				 * case "VIEWCART": ViewCart(request, response); break;
				 */
	    			
	            case "TAIKHOAN":
	                taiKhoan(request, response); 
	                
	                break;
	            case "TAIKHOANCHITIET":
	                taiKhoan(request, response);
	                break;
	            case "TINTUC":
	                tinTuc(request, response);
	                break;
	                
	            case "THONGTIN":
	                thongTin(request, response);
	                break;
	            case "THONGBAO":
	                thongBao(request, response);
	                break;
	            case "DIACHIGIAO":
	                diaChi(request, response);
	                break;
	            case "TATCAPIN":
	                tatCaPin(request, response);
	                break;
	                
	            case "SEARCH":
				    searchPin(request, response);
				    break;
				/*
				 * case "DELETE": xoaGioHang(request, response); break;
				 */
	                
	                
	            default:
	            	dsPinVaTatCaPin(request, response);               
	                break;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//DANH SÁCH ĐỒNG HỒ
	private void dsPinVaTatCaPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    List<Pin> dsTatCaPin = dongHoUtil.getALLPin();
	    

	    request.setAttribute("PIN", dsTatCaPin);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/trangchu.jsp");
	    dispatcher.forward(request, response);
	    
	}
	
	//SHOW CHI TIẾT ĐỒNG HỒ
	public void ShowViewItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		int maPin = Integer.parseInt(request.getParameter("maPin"));
		Pin pin = dongHoUtil.getPinByid(maPin);
		request.setAttribute("PIN", pin);
		request.getRequestDispatcher("/view/chitietpin.jsp").forward(request, response);
	}
	
	//SHOW CHI TIẾT ĐỒNG HỒ
		public void ShowViewThongTinDatLich(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
			int maPin = Integer.parseInt(request.getParameter("maPin"));
			Pin pin = dongHoUtil.getPinByid(maPin);
			request.setAttribute("PIN", pin);
			request.getRequestDispatcher("/view/datlich.jsp").forward(request, response);
		}
	
//	// THÊM VÀO GIỎ HÀNG
//    private void AddItemsToCart(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int maDH = Integer.parseInt(request.getParameter("maDH"));
//        DongHo dongho = dongHoUtil.getDongHoByid(maDH);
//        int soLuong = Integer.parseInt(request.getParameter("soLuong")); // Lấy số lượng từ request
//        GioHang gioHang = new GioHang(dongho, soLuong);
//       
//        HttpSession session = request.getSession();
//        List<GioHang> dsDongHos = (List<GioHang>) session.getAttribute("cart");
//        if (dsDongHos == null) {
//            dsDongHos = new ArrayList<>();
//        }
//        
//
//        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa, nếu có thì cập nhật số lượng
//        boolean isUpdated = false;
//        for (GioHang existingGioHang : dsDongHos) {
//            if (existingGioHang.getDongho().getMaDH() == maDH) {
//                existingGioHang.setSoLuong(existingGioHang.getSoLuong() + soLuong);
//                
//                //cập nhật lại số lượng
//                dongho.setSoLuong(dongho.getSoLuong() - (existingGioHang.getSoLuong() + soLuong));
//                isUpdated = true;
//                break;
//            }
//        }
//
//        // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
//        if (!isUpdated) {
//            dsDongHos.add(gioHang);
//            
//            //cập nhật lại số lượng
//            dongho.setSoLuong(dongho.getSoLuong() - gioHang.getSoLuong());
//        }
//        session.setAttribute("cart", dsDongHos);
//        dsPinVaTatCaPin(request, response);
//    
//    }
		/*
		 * //CẬP NHẬT public void UpdateCart(HttpServletRequest request,
		 * HttpServletResponse response) throws ServletException, IOException { int
		 * maPin = Integer.parseInt(request.getParameter("maPin")); int soLuong =
		 * Integer.parseInt(request.getParameter("soLuong"));
		 * 
		 * HttpSession session = request.getSession(); List<GioHang> dsPins =
		 * (List<GioHang>) session.getAttribute("cart");
		 * 
		 * for (GioHang gioHang : dsPins) {
		 * 
		 * if (gioHang.getPin().getMaPin() == maPin) { gioHang.setSoLuong(soLuong);
		 * 
		 * 
		 * break; }
		 * 
		 * }
		 * 
		 * session.setAttribute("cart", dsDongHos); dsPinVaTatCaPin(request, response);
		 * 
		 * }
		 */
	/*
	 * //CẬP NHẬT NẾU SỐ LƯỢNG 0 THÌ XÓA public void UpdateCart2(HttpServletRequest
	 * request, HttpServletResponse response) throws ServletException, IOException {
	 * int maDH = Integer.parseInt(request.getParameter("maDH")); int soLuong =
	 * Integer.parseInt(request.getParameter("soLuong"));
	 * 
	 * HttpSession session = request.getSession(); List<GioHang> dsDongHos =
	 * (List<GioHang>) session.getAttribute("cart");
	 * 
	 * // Kiểm tra nếu số lượng là 0, thì xóa sản phẩm khỏi giỏ hàng if (soLuong ==
	 * 0) { for (Iterator<GioHang> iterator = dsDongHos.iterator();
	 * iterator.hasNext();) { GioHang gioHang = iterator.next(); if
	 * (gioHang.getDongho().getMaDH() == maDH) { iterator.remove(); break; } } }
	 * else { // Cập nhật số lượng nếu số lượng khác 0 for (GioHang gioHang :
	 * dsDongHos) { if (gioHang.getDongho().getMaDH() == maDH) {
	 * gioHang.setSoLuong(soLuong);
	 * 
	 * break; } } }
	 * 
	 * session.setAttribute("cart", dsDongHos); dsPinVaTatCaPin(request, response);
	 * } // XÓA SẢN PHẨM KHỎI GIỎ HÀNG public void xoaGioHang(HttpServletRequest
	 * request, HttpServletResponse response) throws ServletException, IOException {
	 * int maDH = Integer.parseInt(request.getParameter("maDH"));
	 * 
	 * HttpSession session = request.getSession(); List<GioHang> dsDongHos =
	 * (List<GioHang>) session.getAttribute("cart");
	 * 
	 * Iterator<GioHang> iterator = dsDongHos.iterator(); while (iterator.hasNext())
	 * { GioHang gioHang = iterator.next(); if (gioHang.getDongho().getMaDH() ==
	 * maDH) { iterator.remove(); break; } }
	 * 
	 * session.setAttribute("cart", dsDongHos); dsPinVaTatCaPin(request, response);
	 * }
	 * 
	 * //VIEW GIỎ HÀNG public void ViewCart(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * HttpSession session = request.getSession(); List<DongHo> dsDongHos =
	 * (List<DongHo>) session.getAttribute("cart"); String message;
	 * System.out.println(dsDongHos); if (dsDongHos == null) { message =
	 * "Cart is null"; }
	 * request.getRequestDispatcher("/view/cart.jsp").forward(request, response); }
	 */
	
	
	//TÀI KHOẢN
	private void taiKhoan(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);		
		}
	
	
	// TIN TỨC
	private void tinTuc(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/tintuc.jsp");
			dispatcher.forward(request, response);		
		}
	
	//THÔNG TIN
	private void thongTin(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thongtinlienhe.jsp");
			dispatcher.forward(request, response);		
		}
	
	//THÔNG BÁO
		private void thongBao(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/thongbao.jsp");
				dispatcher.forward(request, response);		
			}
		
	//ĐỊA CHỈ GIAO HÀNG
		private void diaChi(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {
						
				RequestDispatcher dispatcher = request.getRequestDispatcher("/view/diachigiaohang.jsp");
				dispatcher.forward(request, response);		
			}
		
		//TẤT CA DỒNG HỒ
		private void tatCaPin(HttpServletRequest request, HttpServletResponse response) 
				throws Exception {		
			 List<Pin> dsTatCaPin = dongHoUtil.getALLPin();
			 request.setAttribute("PIN", dsTatCaPin);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/view/tatcapin.jsp");
					dispatcher.forward(request, response);		
					}
		
		//TÌM ĐỒNG HỒ
		private void searchPin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String tenPin = request.getParameter("tenPin");
		    List<Pin> listDongHo = dongHoUtil.searchPinByName(tenPin);
		    request.setAttribute("PIN", listDongHo);
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/view/timsanpham.jsp");
		    dispatcher.forward(request, response);
		}

	
	
	
	
	
	
}
