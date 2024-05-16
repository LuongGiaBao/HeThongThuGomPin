<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lag="en">
<head>
  <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="icon" href="img/logo-mini.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="css/style_trangchu.css" type="text/css"/>
    <link rel="stylesheet" href="css/reset.css" />
    
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="fontawesome/css/all.css" />
    
   
    
    
    <script src="js/jquery.js"></script>
    <script src="js/style.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Trang tin tức</title>
</head>

<body>


  <script type="text/javascript">
        $(document).ready(function() {
            $('.list-menu li').hover(function() {
                    $(this).find(".sub-menu").stop().fadeIn(500);
                },
                function() {
                    $(this).find(".sub-menu").stop().fadeOut(300);
                });
        });
        
        function searchPin() {
            var searchInput = document.getElementById('searchInput').value;
            window.location.href = 'PinServlet?command=SEARCH&tenPin=' + searchInput;
        }
        
        function filterByBrand() {
            var selectedBrandId = document.getElementById('brandFilter').value;
            window.location.href = 'CRUDServlet?command=LIST_BY_BRAND&thuongHieuId=' + selectedBrandId;
        }
    </script>
    
    
    
<input type="hidden" name="command" value="TEST">

            
 <div id="main">
    <!-- ============ HEADER ===============-->
    <div id="header">
        
        <div class="banner">
            <div class="login">
              
                <div class="sub-login">
                    <p><a href="tel:0354985272"><i class="fas fa-phone-alt"></i> HOTLINE: 0354985272</a></p>
                    <ul>
                    
                    <!-- Ẩn nút TK sau khi đăng nhập -->
                    <c:if test ="${sessionScope.acc == null}">
                         	<li>
                            <p><a href="PinServlet?command=TAIKHOAN"><i class="fas fa-user"></i>Tài khoản</a></p>
							</li>
                        </c:if>
                       
                    </ul>
                    <p><a href="#"><i class="fas fa-map-marker-alt" ></i>Hệ thống cửa hàng</a></p>
                    <a href="?command=VIEWCART" class="cart-link">
					  <i class="fas fa-shopping-cart" style="margin-left: 1000px;"></i>
					  <span class="cart-counter">1</span>
					  <span class="cart-title">Giỏ hàng</span>
					</a>
                    
                     </div>
            </div>
            <div class="sub-banner">
                <a href="PinServlet"><img class="logo" style="color: red; width: 60%; height:84px;  " src="imgPin/logo.jpeg" alt="" /></a>
                
                <form action>
                    <input type="search" id="searchInput" placeholder="Bạn đang tìm dòng pin nào..." />
                    
                </form>
                <i class="fas fa-search" onclick="searchPin()" ></i>
            </div>
        </div>
    </div>   
</div> 

<!-- ============END HEADER=============== -->
        <!-- ============MENU=============== -->
        

    <nav style="margin-left: 0px;">
        <ul class="list-menu" style="margin-left: 50px;">
        
        <!--  Hello Người dùng-->
        <c:if test="${not empty sessionScope.acc}">
						    <c:if test="${not empty sessionScope.acc.user}">
						        <li class="f">
						            <a  href="#" style="color: while;">Hello ${sessionScope.acc.user}</a>
						        </li>
						        
						     
						        <li class="f">
						            <a style="color: while;" href="LogoutControll">Logout</a>
						        </li>
						    </c:if>
						    
						    
						</c:if>
    
        
            <li class="f"><a style="color: black; margin-left: 150px;" href="PinServlet">Trang chủ</a></li>
            
            
         
            <li class="dmsp" ><a href="#">THƯƠNG HIỆU<i class="fas fa-caret-down"></i></a>
                <ul class="sub-menu">
               
                <c:forEach var="brand" items="${LIST_BRANDS}">
					<li> value="${brand.thuongHieuId}">${brand.name}</li>
				</c:forEach>
                    <li ><a href="#pincono" >Pin con ó</a></li>
                    <li><a href="#AAA">AAA</a></li>
                    <li><a href="#pinvuong">Pin vuông</a></li>
                </ul>
          </li>

            <li>
	            <a href="#">TIN TỨC<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="PinServlet?command=TINTUC">TIN XỬ LÝ PIN</a></li>
	               
	            </ul>
           </li>
           
           <li><a href="PinServlet?command=THONGTIN">THÔNG TIN</a></li>
           
           <c:if test ="${sessionScope.acc.isAdmin == 1}">
	           
	           <li>
	            <a href="#">QUẢN LÝ<i class="fas fa-caret-down"></i></a>
	             <ul class="sub-menu">
	                
	                <li><a href="CRUDServlet">QUẢN LÝ PIN</a></li>
	                <li><a href="CRUDDatLichControll">QUẢN LÝ ĐẶT LỊCH</a></li>
	               
	            </ul>
           </li>
           
          </c:if>
       </ul>
  </nav>
  
 
      <div class="slider">
  <img src="imgPin/anhpin2.jpg" style="height: 400px;">
 </div>
 <div id="wrapper">
      
    <div class="contaner">
        <div class="row">
            <div class="row ml-5">
                <a href="DongHoServlet"><h6>Trang chủ</h6></a>
                <h6 style="color: gray;"> > Tin tức thu-gom pin cũ</h6>
            </div>
        </div>
        
        <div class="row">
            <div class="col-md-9">
                <div class="border-bottom">
                    <h3 style = "font-family:courier,arial,helvetica;">Xử lý pin</h3>
                </div>
                <!-- tin tuc 1 -->
                <h2 style="text-align: center;">Bộ Tài nguyên và Môi trường hỗ trợ địa phương thu gom xử lý pin, chất thải rắn</h2>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console; text-align: center;">POSTED BY · NOVEMBER 29, 2023</p>
                <img src="imgPin/thu-gom-pin.jpg" width="85%" style="margin-left: 30px;">
            <!--   <a href="tintucchitiet.html"></a>
                
                <a href="tintucchitiet.html"></a> -->
                <p style="font-weight: bold; margin-left: 30px; color: black;">Bộ Tài nguyên và Môi trường cho biết, doanh nghiệp sản xuất, nhập khẩu pin sử dụng một lần có trách nhiệm đóng góp tài chính (1% doanh thu) hỗ trợ thu gom, xử lý chất thải rắn, trong đó có pin..</p>
                
                <div class="border-bottom">
                    <!--  <button type="submit" style="border-radius: 3px; margin-left: 30px;">Read more</button>-->
                    <a href="https://moitruong.net.vn/bo-tai-nguyen-va-moi-truong-ho-tro-dia-phuong-thu-gom-xu-ly-pin-chat-thai-ran-68376.html" style="border-radius: 3px; margin-left: 30px;" target="_blank">
    					<button type="button">Read more</button>
					</a>
					
                    
                </div>
                 <!-- tin tuc 2 -->
                <h2 style="text-align: center;">Thu gom và xử lý pin đã qua sử dụng theo quy định góp phần bảo vệ môi trường</h2>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console; text-align: center;">POSTED BY · FEBRUARY 19, 2021</p>
                <img src="imgPin/pin-thai.jpg" width="85%" style="margin-left: 30px;">
                <p style="font-weight: bold; margin-left: 30px;">Cần xử lý pin đã qua sử dụng đảm bảo an toàn, đúng tiêu chuẩn góp phần bảo vệ môi trường.</p>
                <div class="border-bottom">
                    <!--  <button type="submit" style="border-radius: 3px; margin-left: 30px;">Read more</button>-->
                    <a href="https://quanly.moitruongvadothi.vn/31/26996/Thu-gom-va-xu-ly-pin-da-qua-su-dung-theo-quy-dinh-gop-phan-bao-ve-moi-truong.aspx" style="border-radius: 3px; margin-left: 30px;" target="_blank">
    					<button type="button">Read more</button>
					</a>
					
                </div>
                 <!-- tin tuc 3 -->
                <h2 style="text-align: center;">Tác hại và cách xử lý pin hết sử dụng đơn giản nhất</h2>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console; text-align: center;">POSTED BY · NOVEMBER 29, 2023</p>
                <img src="imgPin/pin-rac.png" width="85%" style="margin-left: 30px;">
            <!--   <a href="tintucchitiet.html"></a>
                
                <a href="tintucchitiet.html"></a> -->
                <p style="font-weight: bold; margin-left: 30px; color: black;">Pin đã hết sử dụng vứt tràn lan tại bãi rác</p>
                
                <div class="border-bottom">
                    <!--  <button type="submit" style="border-radius: 3px; margin-left: 30px;">Read more</button>-->
                    <a href="https://tlctrading.vn/cach-xu-ly-pin-het-su-dung" style="border-radius: 3px; margin-left: 30px;" target="_blank">
    					<button type="button">Read more</button>
					</a>
					
                    
                </div>
                 <!-- tin tuc 4 -->
                 <h2 style="text-align: center;">Một viên pin bị vứt ra môi trường/không được xử lý triệt để sẽ làm ô nhiễm 1 mét khối đất và 500 lít nước trong vòng 50 năm</h2>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console; text-align: center;">POSTED BY · NOVEMBER 29, 2023</p>
                <img src="imgPin/pin-loi.jpg" width="85%" style="margin-left: 30px;">
            <!--   <a href="tintucchitiet.html"></a>
                
                <a href="tintucchitiet.html"></a> -->
                <!-- <p style="font-weight: bold; margin-left: 30px; color: black;">Bộ Tài nguyên và Môi trường cho biết, doanh nghiệp sản xuất, nhập khẩu pin sử dụng một lần có trách nhiệm đóng góp tài chính (1% doanh thu) hỗ trợ thu gom, xử lý chất thải rắn, trong đó có pin..</p> -->
                
                <div class="border-bottom">
                    <!--  <button type="submit" style="border-radius: 3px; margin-left: 30px;">Read more</button>-->
                    <a href="https://bvndtp.org.vn/mot-vien-pin-bi-vut-ra-moi-truong-khong-duoc-xu-ly-triet-de-se-lam-o-nhiem-1-met-khoi-dat-va-500-lit-nuoc-trong-vong-50-nam/" style="border-radius: 3px; margin-left: 30px;" target="_blank">
    					<button type="button">Read more</button>
					</a>
					
                    
                </div>
                 <!-- tin tuc 5 -->
               <!--  <h2 style="text-align: center;">Bộ Tài nguyên và Môi trường hỗ trợ địa phương thu gom xử lý pin, chất thải rắn</h2>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console; text-align: center;">POSTED BY · NOVEMBER 29, 2023</p>
                <img src="imgPin/thu-gom-pin.jpg" width="85%" style="margin-left: 30px;">
              <a href="tintucchitiet.html"></a>
                
                <a href="tintucchitiet.html"></a>
                <p style="font-weight: bold; margin-left: 30px; color: black;">Bộ Tài nguyên và Môi trường cho biết, doanh nghiệp sản xuất, nhập khẩu pin sử dụng một lần có trách nhiệm đóng góp tài chính (1% doanh thu) hỗ trợ thu gom, xử lý chất thải rắn, trong đó có pin..</p> -->
                
                <div class="border-bottom">
                    <!--  <button type="submit" style="border-radius: 3px; margin-left: 30px;">Read more</button>
                    <a href="https://moitruong.net.vn/bo-tai-nguyen-va-moi-truong-ho-tro-dia-phuong-thu-gom-xu-ly-pin-chat-thai-ran-68376.html" style="border-radius: 3px; margin-left: 30px;" target="_blank">
    					<button type="button">Read more</button>
					</a> -->
					
                    
                </div>
                
            </div>

            <div class="col-md-3">
                <div class="border-bottom">
                    <h3 style = "font-family:courier,arial,helvetica;">Recent posts</h3>
                </div>
                <p style="font-family:Garamond;">NOVEMBER 29, 2023</p>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console;">Thu gom và xử lý pin đã qua sử dụng theo quy định góp phần bảo vệ môi trường</p>
                <p style="font-family:Garamond;">FEBRUARY 19, 2021</p>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console;">Tác hại và cách xử lý pin hết sử dụng đơn giản nhất</p>
                <p style="font-family:Garamond;">NOVEMBER 13, 2020</p>
                <p style="font-family:Lucida Calligraphy,Comic Sans MS,Lucida Console;">Một viên pin bị vứt ra môi trường/không được xử lý triệt để sẽ làm ô nhiễm 1 mét khối đất và 500 lít nước trong vòng 50 năm</p>
                <p style="font-family:Garamond;">RSS FEED</p>


            </div>
        </div>
    </div>
     <!-- Footer -->
     <div class="footer">
        <div class="container">
             <div class="footer-main">
                <div class="footer-main__map">
                    <img src="imgDongHo/logodh11.jpg" width="99%" height="99%"  style="border:0;" allowfullscreen="" loading=""></img>
                </div>

                <div class="footer-list">
                    <div class="footer-wraper-1">
                        <p class="footer-list__title">
                                <strong> DBD ! </strong> 
                        </p>

                        <ul class="footer-address font-15">
                            <li class="footer-address__items">
                                <i class="footer-icon fas fa-map-marker-alt"></i><span class="footer-address__items-none">Address: HCM - 12 Nguyễn Văn Bảo - Quận Gò Vấp</span>
                            </li>
                             <li class="footer-address__items">
                                <i class="footer-icon fas fa-phone-alt"></i><a href="#" class="footer-address__items-link" tel: >0354.985.272</a>
                            </li>
                            <li class="footer-address__items">
                                <i class="footer-icon fas fa-envelope"></i><a href="#" class="footer-address__items-link">sinhviennguyenlechidai@gmail.com</a>
                            </li>
                        </ul>

                        <div class="footer-info">
                            <i class="footer-info__icon fab fa-facebook-f"></i>
                            <i class="footer-info__icon fab fa-youtube"></i>
                            <i class="footer-info__icon fab fa-instagram"></i>
                        </div>

                       <!--  <p class="footer-desc">LUXURY BRAND, thành lập vào năm 2023, chuyên cung cấp đồng hồ hàng hiệu chính hãng tại TPHCM. 
                        Chúng tôi mang đến các sản phẩm đồng hồ cao cấp, 
                        từ những mẫu đồng hồ thời trang đến những chiếc đồng hồ nam nữ tầm trung, 
                        bao gồm các thương hiệu nổi tiếng như Rolex, Patek Philippe, Omega và nhiều hãng danh tiếng khác. 
                        Đa dạng về kiểu dáng và mẫu mã, phù hợp cho phong cách lịch lãm của nam giới và sự sang trọng của phụ nữ.
						Chúng tôi, LUXURY BRAND, cam kết không bán hàng nhái, hàng xách tay không rõ nguồn gốc xuất xứ. 
						Tất cả các sản phẩm đều được dán tem barcode chống hàng giả BCA và đi kèm thẻ bảo hành chính hãng, 
						đảm bảo nguồn gốc và chất lượng. Khách hàng mua online sẽ được hưởng ưu đãi giảm giá 10% từ giá niêm yết 
						trên trang web.
                        </p> -->
                    </div>
                </div>   
            </div>
              
        </div>
            <div class="copyright">
                    <div class="copyright-main">
                      @2024 - <span class="copyright-main__color">DBD Team</span> 
                      <span class="copyright-mobile-1"> | </span> 
                      <span class="copyright-mobile-2">
                        WEB
                        <a href="DongHoServlet?command=THONGTIN"><span style="color: orange;">Nguyễn Lê Chí Đại - Lương Trần Gia Bảo - Nguyễn Công Danh</span></a>
                      </span>
                    </div>

                    <div class="top">
                        <a class="top-link" href="#">Trở lại đầu trang <i class="top-icon fas fa-chevron-up"></i></a>
                    </div>
            </div>  

        </div>
    </div>
</body>
</html>